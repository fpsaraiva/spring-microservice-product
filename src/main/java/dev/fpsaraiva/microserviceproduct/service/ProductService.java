package dev.fpsaraiva.microserviceproduct.service;

import dev.fpsaraiva.microserviceproduct.dto.ProductDTORequest;
import dev.fpsaraiva.microserviceproduct.dto.ProductDTOResponse;
import dev.fpsaraiva.microserviceproduct.entity.Product;
import dev.fpsaraiva.microserviceproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //aqui, checar dto response
    public ResponseEntity<?> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 5) Pageable page) {
        Page<Product> products = productRepository.findAll(page);
        return ResponseEntity.ok(ProductDTOResponse.toList(products).getContent());
    }

    public ResponseEntity<?> getProductByCategoryId(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 5)
                                                                   Pageable page, Long categoryId) {
        Page<Product> products = productRepository.getProductByCategory(categoryId);
        return ResponseEntity.ok(ProductDTOResponse.toList(products).getContent());
    }

    public ResponseEntity<?> findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if(product != null) {
            return ResponseEntity.ok(new ProductDTOResponse(product));
        }
        return ResponseEntity.notFound().build();
    }

    public ProductDTOResponse save(ProductDTORequest productDTORequest) {
        Product product = productRepository.save(productDTORequest.toModel());
        return new ProductDTOResponse(product);
    }

    public void delete (Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            productRepository.delete(product.get());
        }
    }
}
