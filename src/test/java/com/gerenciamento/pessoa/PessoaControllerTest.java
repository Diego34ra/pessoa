package com.gerenciamento.pessoa;

import com.gerenciamento.pessoa.controller.PessoaController;
import com.gerenciamento.pessoa.exception.ResourceNotFoundException;
import com.gerenciamento.pessoa.model.Pessoa;
import com.gerenciamento.pessoa.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService pessoaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarPessoa() throws ResourceNotFoundException {
        // Arrange (Preparação)
        int pessoaId = 1;
        Pessoa pessoaMock = new Pessoa(); // Crie uma instância de Pessoa para simular os dados retornados pelo serviço
        when(pessoaService.consultarPessoa(pessoaId)).thenReturn(pessoaMock);

        // Act (Ação)
        ResponseEntity<Pessoa> responseEntity = pessoaController.consultarPessoa(pessoaId);

        // Assert (Assertiva)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pessoaMock, responseEntity.getBody());
    }
}
