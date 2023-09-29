package com.gerenciamento.pessoa.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class MessageResponseDTO {
    private final String status;
    private final int code;
    private final String message;
}
