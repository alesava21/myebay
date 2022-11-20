package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Categoria;

public interface CategoriaService {

	public List<Categoria> listAll();

	public Categoria cercaPerDescrizioneECodice(String descrizione, String codice);

	public void inserisciNuovo(Categoria categoriaInstance);

}
