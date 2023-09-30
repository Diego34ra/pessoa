package com.gerenciamento.pessoa.repository;

import com.gerenciamento.pessoa.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco,Integer> {

    List<Endereco> findByPessoa(Integer pessoa_id);
}
