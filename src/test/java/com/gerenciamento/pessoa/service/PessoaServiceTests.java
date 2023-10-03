package com.gerenciamento.pessoa.service;

import com.gerenciamento.pessoa.exception.ResourceNotFoundException;
import com.gerenciamento.pessoa.model.Endereco;
import com.gerenciamento.pessoa.model.Pessoa;
import com.gerenciamento.pessoa.repository.PessoaRepository;
import lombok.AllArgsConstructor;
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
public class PessoaServiceTests {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void createTest(){
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Teste");
        pessoa.setDataDeNascimento(Date.valueOf(LocalDate.now()));
        List<Endereco> enderecoList = new ArrayList<>();
        pessoa.setEnderecos(enderecoList);
        pessoaService.create(pessoa);

        int tamanho = pessoaRepository.findAll().size();
        String nome = pessoaRepository.findById(1).get().getNome();

        Assertions.assertEquals(1,tamanho);
        Assertions.assertEquals("Teste",nome);

    }

    @Test
    public void consultarPessoaTest() throws ResourceNotFoundException {
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Teste");
        pessoa.setDataDeNascimento(Date.valueOf(LocalDate.now()));
        List<Endereco> enderecoList = new ArrayList<>();
        pessoa.setEnderecos(enderecoList);
        pessoaRepository.save(pessoa);

        Pessoa pessoaConsulta = pessoaService.consultarPessoa(1);

        Assertions.assertEquals(1,pessoaConsulta.getId());
        Assertions.assertEquals("Teste",pessoaConsulta.getNome());

    }
}
