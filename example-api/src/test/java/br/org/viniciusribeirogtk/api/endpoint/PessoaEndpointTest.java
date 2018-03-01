package br.org.viniciusribeirogtk.api.endpoint;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.org.viniciusribeirogtk.api.model.Pessoa;
import br.org.viniciusribeirogtk.api.repository.PessoaRepository;

@RunWith(SpringRunner.class)
/**
 * For Integration Test 
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
/**
 * To MockMvc
 */
@AutoConfigureMockMvc
public class PessoaEndpointTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int port;
	
	/**
	 * To simulate database
	 */
	@MockBean
	private PessoaRepository pessoaRepository;
	
	/**
	 * Another way to perform rest tests
	 */
	@Autowired
	private MockMvc mockMvc;
	
	
	@Before
    public void setup() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Vinicius");
		pessoa.setSobrenome("Ribeiro");
		pessoa.setId(1L);
		BDDMockito.when(pessoaRepository.findOne(pessoa.getId())).thenReturn(pessoa);
		BDDMockito.when(pessoaRepository.findByNomeIgnoreCaseContaining(Mockito.anyString())).thenReturn(Arrays.asList(pessoa));
    }
	
	@Test
	public void savePessoa() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("/pessoas", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void findPessoaById() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("/pessoas/findByName/{nome}", String.class, "Vinicius");
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
		Assertions.assertThat(response.getBody()).isEqualTo("[{\"id\":1,\"nome\":\"Vinicius\",\"sobrenome\":\"Ribeiro\",\"dataNascimento\":null,\"endereco\":null}]");
		
	}

}
