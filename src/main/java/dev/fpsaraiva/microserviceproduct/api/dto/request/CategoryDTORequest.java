package dev.fpsaraiva.microserviceproduct.api.dto.request;

import dev.fpsaraiva.microserviceproduct.model.entity.Category;

import javax.validation.constraints.NotBlank;

public class CategoryDTORequest {

    @NotBlank
    private String nome;

    @Deprecated
    public CategoryDTORequest() {
    }

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
