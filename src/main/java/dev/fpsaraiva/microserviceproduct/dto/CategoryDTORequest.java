package dev.fpsaraiva.microserviceproduct.dto;

import dev.fpsaraiva.microserviceproduct.entity.Category;

import javax.validation.constraints.NotBlank;

public class CategoryDTORequest {

    @NotBlank
    private String nome;

    public CategoryDTORequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Category toModel() {
        return new Category(nome);
    }
}
