package br.com.rafael.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateUserRequest {

    @NotBlank(message = "Necessario Informar Nome")   // Verifica se não é nula nem vazia
    private String name;
    @NotNull(message = "Necessario Informar Idade")    // Verifica apenas se não é nulla
    private Integer age;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }  
}
