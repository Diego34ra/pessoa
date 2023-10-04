package com.gerenciamento.pessoa.service;

import com.gerenciamento.pessoa.exception.ResourceNotFoundException;
import com.gerenciamento.pessoa.model.Endereco;
import com.gerenciamento.pessoa.model.Pessoa;
import com.gerenciamento.pessoa.repository.EnderecoRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnderecoServiceTests {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Test
    public void criarEnderecoTest() throws ResourceNotFoundException {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Teste");
        pessoa.setDataDeNascimento(Date.valueOf(LocalDate.now()));
        List<Endereco> enderecoList = new ArrayList<>();
        pessoa.setEnderecos(enderecoList);
        pessoaService.criarPessoa(pessoa);

        Endereco endereco = new Endereco();
        endereco.setCep("75209-970");
        endereco.setCidade("Curitiba");
        endereco.setLogradouro("Rua Joaquim Antônio Teixeira 114");
        enderecoService.criarEndereco(1,endereco);

        Endereco enderecoCriado = enderecoRepository.findById(1).get();
        String cep = enderecoCriado.getCep();
        String cidade = enderecoCriado.getCidade();

        Assertions.assertEquals("75209-970",cep);
        Assertions.assertEquals("Curitiba",cidade);
    }

    @Test
    public void consultarEnderecosTest() throws ResourceNotFoundException {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Teste");
        pessoa.setDataDeNascimento(Date.valueOf(LocalDate.now()));
        List<Endereco> enderecoList = new ArrayList<>();
        Endereco endereco = new Endereco();
        endereco.setCep("75209-970");
        endereco.setCidade("Curitiba");
        endereco.setLogradouro("Rua Joaquim Antônio Teixeira 114");
        enderecoList.add(endereco);
        pessoa.setEnderecos(enderecoList);
        pessoaService.criarPessoa(pessoa);

        List<Endereco> enderecoLists = enderecoService.consultarEnderecos(1);

        Assertions.assertEquals(1,enderecoLists.size());
    }

    @Test
    public void definirEnderecoPrincipal() throws ResourceNotFoundException {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Teste");
        pessoa.setDataDeNascimento(Date.valueOf(LocalDate.now()));
        List<Endereco> enderecoList = new ArrayList<>();
        pessoa.setEnderecos(enderecoList);
        pessoaService.criarPessoa(pessoa);

        Endereco endereco = new Endereco();
        endereco.setCep("75209-970");
        endereco.setCidade("Curitiba");
        endereco.setLogradouro("Rua Joaquim Antônio Teixeira 114");
        enderecoService.criarEndereco(1,endereco);

        enderecoService.definirEnderecoPrincipal(1,1);
        Endereco enderecoCriado = enderecoService.consultarEndereco(1);

        Boolean enderecoPrincipal = enderecoCriado.getEnderecoPrincipal();

        Assertions.assertEquals(true,enderecoPrincipal);
    }
}
