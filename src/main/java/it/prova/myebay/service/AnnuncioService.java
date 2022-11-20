package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioService {
	
	public List<Annuncio> listAllAnninci() ;

	public Annuncio caricaSingoloAnnuncio(Long id);
	
	public void aggiorna(Annuncio annuncioInstance);

	public void inserisciNuovo(Annuncio annuncioInstance);

	public void rimuovi(Long idToDelete);

}
