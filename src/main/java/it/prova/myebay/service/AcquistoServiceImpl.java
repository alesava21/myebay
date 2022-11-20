package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.repository.acquisto.AcquistoRepository;

@Service
public class AcquistoServiceImpl implements AcquistoService{

	@Autowired
	private AcquistoRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Acquisto> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Acquisto caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void aggiorna(Acquisto acquistoInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void inserisciNuovo(Acquisto acquistoInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void rimuovi(Long idToDelete) {
		// TODO Auto-generated method stub
		
	}

}