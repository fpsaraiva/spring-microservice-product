package dev.fpsaraiva.microserviceproduct.dto;

import dev.fpsaraiva.microserviceproduct.entity.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDTOResponse {

    @NotBlank
    private String nome;

    @NotNull
    private Float preco;

    @NotBlank
    private String descricao;

    @NotBlank
    private String productIdentifier;

    @NotNull
    private CategoryDTOResponse category;

    public ProductDTOResponse(Product product) {
        this.nome = product.getNome();
        this.preco = product.getPreco();
        this.descricao = product.getDescricao();
        this.productIdentifier = product.getProductIdentifier();
        this.category = product.getCategory().toCategoryDTOResponse();
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

    public CategoryDTOResponse getCategory() {
        return category;
    }

    public static List<ProductDTOResponse> toList(List<Product> products) {
        return products.stream().map(product -> new ProductDTOResponse(product)).collect(Collectors.toList());
    }
}