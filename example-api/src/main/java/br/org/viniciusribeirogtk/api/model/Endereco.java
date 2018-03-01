package br.org.viniciusribeirogtk.api.model;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Endereco extends AbstractEntity {

	@NotEmpty(message = "O logradouro do endereço é obrigatório")
	private String logradouro;
	@NotEmpty(message = "O complemento do endereço é obrigatório")
	private String complemento;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
