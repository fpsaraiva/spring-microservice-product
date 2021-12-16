package dev.fpsaraiva.microserviceproduct.api.dto.request;

import dev.fpsaraiva.microserviceproduct.model.entity.Category;

import javax.validation.constraints.NotBlank;
import java.util.Locale;

public class CategoryDTORequest {

    private Long id;

    private String nome;

    @Deprecated
    public CategoryDTORequest() {
    }

    public CategoryDTORequest(Long id) {
        this.id = id;
    }

    public CategoryDTORequest(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Category toModel() {
        return new Category(nome.toUpperCase());
    }
}
