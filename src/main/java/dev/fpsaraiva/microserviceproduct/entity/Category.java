package dev.fpsaraiva.microserviceproduct.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDateTime dataCadastro;

    @Deprecated
    public Category() {
    }

    public Category(String nome) {
        this.nome = nome;
        this.dataCadastro = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}
