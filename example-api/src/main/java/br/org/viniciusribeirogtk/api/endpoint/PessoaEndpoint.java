package br.org.viniciusribeirogtk.api.endpoint;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.viniciusribeirogtk.api.exception.ResourceNotFoundException;
import br.org.viniciusribeirogtk.api.model.Pessoa;
import br.org.viniciusribeirogtk.api.repository.PessoaRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("pessoas")
public class PessoaEndpoint {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	@ApiOperation(value = "Return a list with all Persons", response = Pessoa[].class)
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(pessoaRepository.findAllPessoas(), HttpStatus.OK);
	}

	@PostMapping
	@Transactional(rollbackOn = Exception.class)
	@ApiOperation(value = "Save a Persons", response = Pessoa[].class)
	public ResponseEntity<?> savePessoa(@RequestBody Pessoa pessoa) {
		return new ResponseEntity<>(pessoaRepository.save(pessoa), HttpStatus.CREATED);
	}

	@PutMapping
	@Transactional(rollbackOn = Exception.class)
	@ApiOperation(value = "Update a Persons", response = Pessoa[].class)
	public ResponseEntity<?> updatePessoa(@RequestBody Pessoa pessoa) {
		verifyIfPessoaExists(pessoa.getId());
		return new ResponseEntity<>(pessoaRepository.save(pessoa), HttpStatus.OK);
	}

	@GetMapping(path = "/findByName/{nome}")
	@ApiOperation(value = "Find a Person by name", response = Pessoa[].class)
	public ResponseEntity<?> findStudentsByName(@PathVariable String nome) {
		return new ResponseEntity<>(pessoaRepository.findByNomeIgnoreCaseContaining(nome), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	@Transactional(rollbackOn = Exception.class)
	@ApiOperation(value = "Delete person by id", response = Pessoa[].class)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		verifyIfPessoaExists(id);
		pessoaRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private void verifyIfPessoaExists(Long id) {
		if (pessoaRepository.findOne(id) == null)
			throw new ResourceNotFoundException("Student not found for ID: " + id);
	}
}
