package com.gerenciamento.pessoa.repository;

import com.gerenciamento.pessoa.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco,Integer> {
}
