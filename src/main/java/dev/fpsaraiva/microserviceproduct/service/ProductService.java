package dev.fpsaraiva.microserviceproduct.service;

import dev.fpsaraiva.microserviceproduct.dto.ProductDTORequest;
import dev.fpsaraiva.microserviceproduct.dto.ProductDTOResponse;
import dev.fpsaraiva.microserviceproduct.entity.Product;
import dev.fpsaraiva.microserviceproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTOResponse> getAll() {
        List<Product> products = productRepository.findAll();
        return ProductDTOResponse.toList(products);
    }

    public List<ProductDTOResponse> getProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return ProductDTOResponse.toList(products);
    }

    public ProductDTOResponse findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if(product != null) {
            return new ProductDTOResponse(product);
        }
        return null;
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
