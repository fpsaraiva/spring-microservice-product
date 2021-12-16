package dev.fpsaraiva.microserviceproduct.api.dto.request;

import dev.fpsaraiva.microserviceproduct.model.entity.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDTORequest {

    @NotBlank
    private String nome;

    @NotNull
    private Float preco;

    @NotBlank
    private String descricao;

    @NotBlank
    private String productIdentifier;

    @NotNull
    private CategoryDTORequest category;

    public ProductDTORequest(String nome, Float preco, String descricao, String productIdentifier,
                             CategoryDTORequest category) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.productIdentifier = productIdentifier;
        this.category = category;
    }

    public String getNome() {
        return nome;
    }

    public Float getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public CategoryDTORequest getCategory() {
        return category;
    }

    public Product toModel() {
        return new Product(nome, preco, descricao, productIdentifier, category.toModel());
    }
}