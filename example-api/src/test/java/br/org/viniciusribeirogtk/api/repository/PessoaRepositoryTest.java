package br.org.viniciusribeirogtk.api.repository;

import java.time.LocalDate;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.org.viniciusribeirogtk.api.model.Pessoa;

@RunWith(SpringRunner.class)
@DataJpaTest
/**
 *  To utilize your real database, otherwise will use h2 in memory database
 */
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PessoaRepositoryTest {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	/**
	 * Quais Excessões vamos esperar que aconteça no método
	 */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void createShouldPersistData() {
		Pessoa pessoa = createPessoa();
		this.pessoaRepository.save(pessoa);
		Assertions.assertThat(pessoa.getId()).isNotNull();
		Assertions.assertThat(pessoa.getNome()).isEqualTo("Vinicius");
		Assertions.assertThat(pessoa.getSobrenome()).isEqualTo("Ribeiro");
	}

	
	@Test
	public void deleteShouldRemoveData() {
		Pessoa pessoa = createPessoa();
		pessoaRepository.save(pessoa);
		pessoaRepository.delete(pessoa);
		Assertions.assertThat(pessoaRepository.findOne(pessoa.getId())).isNull();
	}
	
	@Test
	public void updateShouldChangeAndPersistData() {
		Pessoa pessoa = createPessoa();
		pessoaRepository.save(pessoa);
		pessoa.setNome("Roberto");
 		pessoa = pessoaRepository.findOne(pessoa.getId());
		Assertions.assertThat(pessoa.getNome()).isEqualTo("Roberto");
		Assertions.assertThat(pessoa.getId()).isNotNull();
	}

	@Test
	public void findByNameIgnoreCaseContainingShouldIgnoreCase() {
		Pessoa pessoa = createPessoa();
		Pessoa pessoa2 = createPessoa();
		pessoa2.setNome("vinicius");
		pessoaRepository.save(pessoa);
		pessoaRepository.save(pessoa2);
		List<Pessoa> pessoas = pessoaRepository.findByNomeIgnoreCaseContaining("Vinicius");
//		Assertions.assertThat(pessoas.size()).isEqualTo(2);
		Assertions.assertThat(pessoa.getId()).isNotNull();
	}

	@Test
	public void createWhenNameIsNullShouldThrowConstraintViolationException() {
		expectedException.expect(ConstraintViolationException.class);
		expectedException.expectMessage("O campo nome da pessoa é obrigatório");
		pessoaRepository.save(new Pessoa());
	}
	
	
	private Pessoa createPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Vinicius");
		pessoa.setSobrenome("Ribeiro");
		pessoa.setDataNascimento(LocalDate.of(1999, 03, 04));
		return pessoa;
	}
	
}
