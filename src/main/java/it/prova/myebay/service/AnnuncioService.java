package it.prova.myebay.service;

import java.util.List;

import org.hibernate.annotations.common.util.StringHelper;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;

public interface AnnuncioService {
	
	public List<Annuncio> listAll();

	public Annuncio caricaSingoloElemento(Long id);
	
	public Annuncio caricaSingoloElementoConCategorie(Long id);

	public void aggiorna(Annuncio annuncioInstance, String username);

	public void inserisciNuovo(Annuncio annuncioInstance, String username);

	public void rimuovi(Long idToDelete);
	
	public void acquista(Long id, Utente utenteInstance);
	
	public List<Annuncio> findByExample(Annuncio example);
	
	public List<Annuncio> findByExampleEager(Annuncio example);
	
	

}
