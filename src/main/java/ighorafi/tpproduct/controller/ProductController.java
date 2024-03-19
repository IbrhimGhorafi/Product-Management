package ighorafi.tpproduct.controller;

import ighorafi.tpproduct.dto.ProductRequestDTO;
import ighorafi.tpproduct.dto.ProductResponseDTO;
import ighorafi.tpproduct.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addNewProduct(@RequestBody ProductRequestDTO product) {
        ProductResponseDTO newProduct = productService.addNewProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{codeProduct}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long codeProduct, @RequestBody ProductRequestDTO product) {
        ProductResponseDTO updatedProduct = productService.updateProduct(codeProduct, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/{codeProduct}")
    public ResponseEntity<ProductResponseDTO> getProductByCode(@PathVariable Long codeProduct) {
        ProductResponseDTO product = productService.getProductByCode(codeProduct);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{codeProduct}")
    public ResponseEntity<Void> deleteProductByCode(@PathVariable Long codeProduct) {
        productService.deleteProductById(codeProduct);
        return ResponseEntity.noContent().build();
    }

}
