package dev.fpsaraiva.microserviceproduct.service;

import dev.fpsaraiva.microserviceproduct.api.dto.request.ProductDTORequest;
import dev.fpsaraiva.microserviceproduct.api.dto.response.ProductDTOResponse;
import dev.fpsaraiva.microserviceproduct.exception.BusinessException;
import dev.fpsaraiva.microserviceproduct.model.entity.Category;
import dev.fpsaraiva.microserviceproduct.model.entity.Product;
import dev.fpsaraiva.microserviceproduct.model.repository.CategoryRepository;
import dev.fpsaraiva.microserviceproduct.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTOResponse save(ProductDTORequest dto) {
        Optional<Category> existingCategory = categoryRepository.findById(dto.getCategory().getId());

        if(existingCategory.isPresent()) {
            String categoryName = existingCategory.get().getNome();
            dto.getCategory().setNome(categoryName);
            Product product = productRepository.save(dto.toModel());
        }

        Product product = productRepository.save(dto.toModel());
        return new ProductDTOResponse(product);
    }

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

    public void delete (Long productId) throws Exception {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            productRepository.delete(product.get());
        } else {
            throw new RuntimeException("teste");
        }
    }
}
