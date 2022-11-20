package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.repository.annuncio.AnnuncioRepository;

public class AnnuncioServiceImpl implements AnnuncioService{
	
	@Autowired
	private AnnuncioRepository annuncioRepository;

	@Override
	public List<Annuncio> listAllAnninci() {
		return (List<Annuncio>) annuncioRepository.findAll();
	}

	@Override
	public Annuncio caricaSingoloAnnuncio(Long id) {
		return annuncioRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Annuncio annuncioInstance) {
		annuncioRepository.save(annuncioInstance);
		
	}

	@Override
	public void inserisciNuovo(Annuncio annuncioInstance) {
		annuncioRepository.save(annuncioInstance);
		
	}

	@Override
	public void rimuovi(Long idToDelete) {
		annuncioRepository.deleteById(idToDelete);
		
	}

}
