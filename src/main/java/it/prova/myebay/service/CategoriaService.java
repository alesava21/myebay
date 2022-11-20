package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Utente;

public interface CategoriaService {
	
	public List<Categoria> listAllCategorie() ;

	public Categoria caricaSingoloCategoria(Long id);
	
	public void aggiorna(Categoria categoriaInstance);

	public void inserisciNuovo(Categoria categoriaInstance);

	public void rimuovi(Long idToDelete);

	public Categoria cercaPerDescrizioneECodice(String descrizione, String codice);


}
