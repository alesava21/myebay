package it.prova.myebay.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.repository.annuncio.AnnuncioRepository;

@Service
public class AnnuncioServiceImpl implements AnnuncioService{

	@Autowired
	private AnnuncioRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Annuncio> listAll() {
		return (List<Annuncio>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Annuncio caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void aggiorna(Annuncio annuncioInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void inserisciNuovo(Annuncio annuncioInstance) {
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
	
	
}