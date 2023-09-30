package com.gerenciamento.pessoa.controller;

import com.gerenciamento.pessoa.dtos.MessageResponseDTO;
import com.gerenciamento.pessoa.exception.ResourceNotFoundException;
import com.gerenciamento.pessoa.model.Endereco;
import com.gerenciamento.pessoa.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pessoa/{pessoaId}/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> criarEndereco(@PathVariable Integer pessoaId, @RequestBody Endereco endereco) throws ResourceNotFoundException {
        var mensagem = enderecoService.criarEndereco(pessoaId,endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> consultarEndereco(@PathVariable Integer pessoaId) throws ResourceNotFoundException {
        List<Endereco> enderecoList = enderecoService.consultarEnderecos(pessoaId);
        return ResponseEntity.ok().body(enderecoList);
    }

    @PutMapping("/{enderecoId}/principal")
    public ResponseEntity<MessageResponseDTO> definirEnderecoPrincipal(@PathVariable Integer pessoaId, @PathVariable Integer enderecoId) throws ResourceNotFoundException {
        return enderecoService.definirEnderecoPrincipal(pessoaId,enderecoId);
    }

}
