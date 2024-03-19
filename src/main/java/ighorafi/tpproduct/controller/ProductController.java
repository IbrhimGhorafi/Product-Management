package ighorafi.tpproduct.controller;

import ighorafi.tpproduct.dto.ProductDTO;
import ighorafi.tpproduct.entity.Product;
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
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDTO product) {
        Product newProduct = productService.addNewProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{codeProduct}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long codeProduct, @RequestBody ProductDTO product) {
        Product updatedProduct = productService.updateProduct(codeProduct, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/{codeProduct}")
    public ResponseEntity<Product> getProductByCode(@PathVariable Long codeProduct) {
        Product product = productService.getProductByCode(codeProduct);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{codeProduct}")
    public ResponseEntity<Void> deleteProductByCode(@PathVariable Long codeProduct) {
        Product product = productService.getProductByCode(codeProduct);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProductById(codeProduct);
        return ResponseEntity.noContent().build();
    }

}
