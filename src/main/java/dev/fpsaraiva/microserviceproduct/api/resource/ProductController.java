package dev.fpsaraiva.microserviceproduct.api.resource;

import dev.fpsaraiva.microserviceproduct.api.dto.request.ProductDTORequest;
import dev.fpsaraiva.microserviceproduct.api.dto.response.ProductDTOResponse;
import dev.fpsaraiva.microserviceproduct.exception.BusinessException;
import dev.fpsaraiva.microserviceproduct.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    //TODO: categoria pode ser salva mais de uma vez. ajustar esse comportamento.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTORequest dto) {
        try {
            ProductDTOResponse newProduct = productService.save(dto);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (BusinessException exception) {
            return ResponseEntity.unprocessableEntity().body("Não é possível cadastrar");
        }
    }

    //TODO: verificar dto response
    @GetMapping
    public ResponseEntity getProducts(Pageable page) {
        List<ProductDTOResponse> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity getProductByCategoryId(Pageable page, @PathVariable Long categoryId) {
        List<ProductDTOResponse> products = productService.getProductByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productIdentifier}")
    public ResponseEntity findById(@PathVariable String productIdentifier) {
        ProductDTOResponse product = productService.findByProductIdentifier(productIdentifier);
        if(product == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        try {
            productService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            throw new RuntimeException();
        }

    }
}
