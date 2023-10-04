package com.gerenciamento.pessoa.service;

import com.gerenciamento.pessoa.dtos.MessageResponseDTO;
import com.gerenciamento.pessoa.exception.ResourceNotFoundException;
import com.gerenciamento.pessoa.model.Endereco;
import com.gerenciamento.pessoa.model.Pessoa;
import com.gerenciamento.pessoa.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaService pessoaService;



    public List<Endereco> consultarEnderecos(Integer pessoaId) throws ResourceNotFoundException {
        Pessoa pessoa = pessoaService.consultarPessoa(pessoaId);
        return pessoa.getEnderecos();
    }

    public MessageResponseDTO criarEndereco(Integer pessoaId, Endereco endereco) throws ResourceNotFoundException {
        Pessoa pessoa = pessoaService.consultarPessoa(pessoaId);
        endereco.setPessoa(pessoa);
        endereco.setEnderecoPrincipal(false);
        enderecoRepository.save(endereco);
        return MessageResponseDTO
                .builder()
                .code(201)
                .status("Created")
                .message("Endereço criado com sucesso.")
                .build();

    }

    public ResponseEntity<MessageResponseDTO> definirEnderecoPrincipal(Integer pessoaId, Integer enderecoId) throws ResourceNotFoundException {
        Pessoa pessoa = pessoaService.consultarPessoa(pessoaId);
        Endereco endereco = consultarEndereco(enderecoId);
        if(endereco.getPessoa().getId().equals(pessoa.getId())){
            pessoa.getEnderecos().forEach(e -> e.setEnderecoPrincipal(false)); // Desmarca todos os outros como não principais
            endereco.setEnderecoPrincipal(true); // Marca este como principal
            enderecoRepository.save(endereco);
            MessageResponseDTO mensagem = MessageResponseDTO.builder().code(200).status("Ok").message("Endereço definido como principal com sucesso.").build();
            return ResponseEntity.ok(mensagem);
        } else {
            MessageResponseDTO mensagem = MessageResponseDTO.builder().code(400).status("Bad Request").message("O endereço não pertence a esta pessoa.").build();
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    public Endereco consultarEndereco(Integer enderecoId) throws ResourceNotFoundException {
        return verificaSeExiste(enderecoId);
    }

    public Endereco verificaSeExiste(Integer enderecoId) throws ResourceNotFoundException {
        return enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco com id "+enderecoId+" não encontrado."));
    }
}
