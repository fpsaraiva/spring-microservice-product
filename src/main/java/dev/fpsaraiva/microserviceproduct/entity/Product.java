package dev.fpsaraiva.microserviceproduct.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Float preco;
    private String descricao;
    private String productIdentifier;
    private LocalDateTime dataCadastro;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Deprecated
    public Product() {
    }

    public Product(String nome, Float preco, String descricao, String productIdentifier, Category category) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.productIdentifier = productIdentifier;
        this.category = category;
        this.dataCadastro = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Category getCategory() {
        return category;
    }
}
