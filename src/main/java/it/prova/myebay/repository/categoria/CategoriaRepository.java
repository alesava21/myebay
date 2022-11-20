package it.prova.myebay.repository.categoria;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long>,JpaSpecificationExecutor<Categoria>{
	
	Categoria findByDescrizioneAndCodice(String descrizione, String codice);

}
