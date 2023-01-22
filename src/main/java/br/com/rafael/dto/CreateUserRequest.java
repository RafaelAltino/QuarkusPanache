package br.com.rafael.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Necessario Informar Nome")   // Verifica se não é nula nem vazia
    private String name;
    @NotNull(message = "Necessario Informar Idade")    // Verifica apenas se não é nulla
    private Integer age;

}
