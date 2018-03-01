package br.org.viniciusribeirogtk.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.viniciusribeirogtk.api.model.Pessoa;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long>{

	@Query("from Pessoa p join fetch p.endereco e")
	public List<Pessoa> findAllPessoas();

	@Query("from Pessoa p join fetch p.endereco e where p.nome=:nome")
	public List<Pessoa> findByNomeIgnoreCaseContaining(@Param(value = "nome")String nome);
}
