package com.gerenciamento.pessoa.service;

import com.gerenciamento.pessoa.dtos.MessageResponseDTO;
import com.gerenciamento.pessoa.exception.ResourceNotFoundException;
import com.gerenciamento.pessoa.model.Endereco;
import com.gerenciamento.pessoa.model.Pessoa;
import com.gerenciamento.pessoa.repository.EnderecoRepository;
import com.gerenciamento.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public MessageResponseDTO create(Pessoa pessoa){
        pessoaRepository.save(pessoa);
        System.out.println(pessoa.getId());
        List<Endereco> enderecoList = getEndereco(pessoa.getEnderecos(),pessoa);
        enderecoRepository.saveAll(enderecoList);
        return MessageResponseDTO
                .builder()
                .code(201)
                .status("Created")
                .message("Pessoa criada com sucesso")
                .build();
    }

    public List<Pessoa> consultaPessoas(){
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

    public List<Endereco> getEndereco(List<Endereco> enderecoList, Pessoa pessoa){
        IntStream.range(0, enderecoList.size())
                .forEach(index -> {
                    Endereco endereco = enderecoList.get(index);
                    endereco.setEnderecoPrincipal(index == 0);
                    endereco.setPessoa(pessoa);
                });
        return enderecoList;
    }

}
