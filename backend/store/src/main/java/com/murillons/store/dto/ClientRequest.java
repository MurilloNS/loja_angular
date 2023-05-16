package com.murillons.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequest {
    private Long id;
    private String name;
    @Email
    private String email;
    private String password;
    @CPF
    private String cpf;
}
