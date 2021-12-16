package dev.fpsaraiva.microserviceproduct.model.repository;

import dev.fpsaraiva.microserviceproduct.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNome(String nome);
}
