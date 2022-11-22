package it.prova.myebay.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.exception.CreditoInsifficiente;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.repository.acquisto.AcquistoRepository;
import it.prova.myebay.repository.annuncio.AnnuncioRepository;
import it.prova.myebay.repository.utente.UtenteRepository;

@Service
public class AnnuncioServiceImpl implements AnnuncioService{

	@Autowired
	private AnnuncioRepository repository;
	
	@Autowired 
	private UtenteRepository utenteRepository;
	
	@Autowired
	private AcquistoRepository acquistoRepository;
	
	

	@Override
	@Transactional(readOnly = true)
	public List<Annuncio> listAll() {
		return (List<Annuncio>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Annuncio caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Annuncio annuncioInstance, String username) {
		
		if (username == null)
			throw new RuntimeException();
			
	Utente utenteInSessioneUtente =	utenteRepository.findByUsername(username).orElse(null);
		Annuncio annuncioRicaricato= repository.findById(annuncioInstance.getId()).orElse(null);
		annuncioRicaricato.setUtente(utenteInSessioneUtente);
		annuncioRicaricato.setCategorie(annuncioInstance.getCategorie());
		annuncioRicaricato.setPrezzo(annuncioInstance.getPrezzo());
		annuncioRicaricato.setTestoAnnuncio(annuncioInstance.getTestoAnnuncio());
		repository.save(annuncioRicaricato);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Annuncio annuncioInstance, String username) {	
		annuncioInstance.setData(new Date());
		annuncioInstance.setAperto(true);
		if (username != null) {
		Utente utente=utenteRepository.findByUsername(username).orElse(null);
			annuncioInstance.setUtente(utente);
		}
		repository.save(annuncioInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToDelete) {
		repository.deleteById(idToDelete);
		
	}

	@Override
	public List<Annuncio> findByExample(Annuncio example) {
		return repository.findByExample(example);
	}

	@Override
	public List<Annuncio> findByExampleEager(Annuncio example) {
		return repository.findByExampleEager(example);
	}

	@Override
	public Annuncio caricaSingoloElementoConCategorie(Long id) {
		return repository.findByIdConCategorie(id).orElse(null);
	}

	@Override
	@Transactional
	public void acquista(Long id, String username) {
		Annuncio annuncioDaAcquistare = repository.findById(id).orElse(null);
		
		if(username == null)
			throw new RuntimeException();
		Utente utente=utenteRepository.findByUsername(username).orElse(null);
		Utente utenteReloaded = utenteRepository.findById(utente.getId()).orElse(null);
		
		
		
		if(annuncioDaAcquistare == null)
			throw new RuntimeException("Annuncio non trovato.");
		
		if(utente.getCreditoResiduo() < annuncioDaAcquistare.getPrezzo())
			throw new CreditoInsifficiente("Credito residuo insufficiente per effettuare l'acquisto.");
		
		int creditoAggiornato = utente.getCreditoResiduo() - annuncioDaAcquistare.getPrezzo();
		
		utenteReloaded.setCreditoResiduo(creditoAggiornato);
		utenteRepository.save(utenteReloaded);
		
		annuncioDaAcquistare.setAperto(false);
		
		Acquisto nuovoAcquisto = new Acquisto(annuncioDaAcquistare.getTestoAnnuncio(), new Date(), annuncioDaAcquistare.getPrezzo(), utente);
		
		acquistoRepository.save(nuovoAcquisto);
		
		
	}
	
	
}