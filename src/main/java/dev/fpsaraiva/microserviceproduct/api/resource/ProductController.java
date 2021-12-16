package dev.fpsaraiva.microserviceproduct.api.resource;

import dev.fpsaraiva.microserviceproduct.api.dto.request.ProductDTORequest;
import dev.fpsaraiva.microserviceproduct.api.dto.response.ProductDTOResponse;
import dev.fpsaraiva.microserviceproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //TODO: verificar dto response
    @GetMapping("/products")
    public ResponseEntity getProducts(Pageable page) {
        List<ProductDTOResponse> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/categories/{categoryId}")
    public ResponseEntity getProductByCategoryId(Pageable page, @PathVariable Long categoryId) {
        List<ProductDTOResponse> products = productService.getProductByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{productIdentifier}")
    public ResponseEntity findById(@PathVariable String productIdentifier) {
        ProductDTOResponse product = productService.findByProductIdentifier(productIdentifier);
        if(product == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(product);
    }

    //TODO: categoria pode ser salva mais de uma vez. ajustar esse comportamento.
    @PostMapping("/products")
    public ResponseEntity save(@Valid @RequestBody ProductDTORequest dto) {
        ProductDTOResponse newProduct = productService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        try {
            productService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            throw new RuntimeException();
        }

    }
}
