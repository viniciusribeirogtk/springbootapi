package br.org.viniciusribeirogtk.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import br.org.viniciusribeirogtk.api.customvalidations.Past;

@Entity
public class Pessoa extends AbstractEntity {

	@NotEmpty(message = "O campo nome da pessoa é obrigatório")
	private String nome;
	@NotEmpty(message = "O campo sobrenome da pessoa é obrigatório")
	private String sobrenome;
	@Past
	private LocalDate dataNascimento;
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="pessoa_id")
	@Valid
	private List<Endereco> endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

}
