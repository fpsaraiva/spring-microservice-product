package dev.fpsaraiva.microserviceproduct.repository;

import dev.fpsaraiva.microserviceproduct.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
