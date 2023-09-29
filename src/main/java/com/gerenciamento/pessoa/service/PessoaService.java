package com.gerenciamento.pessoa.service;

import com.gerenciamento.pessoa.dtos.MessageResponseDTO;
import com.gerenciamento.pessoa.exception.ResourceNotFoundException;
import com.gerenciamento.pessoa.model.Pessoa;
import com.gerenciamento.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public MessageResponseDTO create(Pessoa pessoa){
        pessoaRepository.save(pessoa);
        return MessageResponseDTO
                .builder()
                .code(201)
                .status("Created")
                .message("Pessoa criada com sucesso")
                .build();
    }

    public List<Pessoa> findAll(){
        List<Pessoa> pessoaList = pessoaRepository.findAll();
        return pessoaList;
    }

    public Pessoa findById(Integer id) throws ResourceNotFoundException {
        return verificaSeExiste(id);
    }

    public Pessoa verificaSeExiste(Integer id) throws ResourceNotFoundException {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa com id "+id+" não foi encontrada."));
        return pessoa;
    }

    public MessageResponseDTO updatePessoa(Integer id, Pessoa pessoaUpdate) throws ResourceNotFoundException {
        Pessoa pessoa = verificaSeExiste(id);
        if(pessoaUpdate.getNome() != null){
            pessoa.setNome(pessoaUpdate.getNome());
        }
        if(pessoaUpdate.getDataDeNascimento() != null){
            pessoa.setDataDeNascimento(pessoaUpdate.getDataDeNascimento());
        }
        pessoaRepository.save(pessoa);
        return MessageResponseDTO
                .builder()
                .code(200)
                .status("Ok")
                .message("Pessoa Atualizada com sucesso")
                .build();
    }
}
