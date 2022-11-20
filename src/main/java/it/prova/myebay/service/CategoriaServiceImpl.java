package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.repository.categoria.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional(readOnly = true)
	public Categoria cercaPerDescrizioneECodice(String descrizione, String codice) {
		return categoriaRepository.findByDescrizioneAndCodice(descrizione, codice);
	}

	@Transactional
	public void inserisciNuovo(Categoria categoriaInstance) {
		categoriaRepository.save(categoriaInstance);
	}

	@Transactional(readOnly = true)
	public List<Categoria> listAll() {
		return (List<Categoria>)categoriaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> listAllCategorie() {
		return (List<Categoria>) categoriaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria caricaSingoloCategoria(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Categoria categoriaInstance) {
		categoriaRepository.save(categoriaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToDelete) {
		categoriaRepository.deleteById(idToDelete);
	}

}
