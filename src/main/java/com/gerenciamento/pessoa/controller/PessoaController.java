package com.gerenciamento.pessoa.controller;

import com.gerenciamento.pessoa.dtos.MessageResponseDTO;
import com.gerenciamento.pessoa.exception.ResourceNotFoundException;
import com.gerenciamento.pessoa.model.Pessoa;
import com.gerenciamento.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("{id}")
    public ResponseEntity<Pessoa> consultarPessoa(@PathVariable Integer id) throws ResourceNotFoundException {
        var pessoa = pessoaService.consultarPessoa(id);
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> consultarPessoas(){
        var pessoas = pessoaService.consultarPessoas();
        return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> criarPessoa(@RequestBody Pessoa pessoa){
        var mensagem = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @PutMapping("{id}")
    public ResponseEntity<MessageResponseDTO> atualizaPessoa(@PathVariable Integer id, @RequestBody Pessoa pessoa) throws ResourceNotFoundException {
        var mensagem = pessoaService.atualizaPessoa(id,pessoa);
        return ResponseEntity.status(HttpStatus.OK).body(mensagem);
    }
}
