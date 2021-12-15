package dev.fpsaraiva.microserviceproduct.dto;

import dev.fpsaraiva.microserviceproduct.entity.Category;
import dev.fpsaraiva.microserviceproduct.entity.Product;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    public static Page<ProductDTOResponse> toList(Page<Product> page) {
        return page.map(ProductDTOResponse::new);
    }
}