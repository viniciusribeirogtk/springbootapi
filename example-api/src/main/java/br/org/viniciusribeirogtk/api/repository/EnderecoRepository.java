package br.org.viniciusribeirogtk.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.org.viniciusribeirogtk.api.model.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

}
