package ighorafi.tpproduct.service;

import ighorafi.tpproduct.dto.ProductRequestDTO;
import ighorafi.tpproduct.dto.ProductResponseDTO;
import ighorafi.tpproduct.entity.Product;

import java.util.List;

public interface ProductService {
    ProductResponseDTO addNewProduct(ProductRequestDTO product);
    ProductResponseDTO updateProduct(Long codeProduct, ProductRequestDTO product);
    ProductResponseDTO getProductByCode(Long codeProduct);
    List<ProductResponseDTO>getAllProducts();
    void deleteProductById(Long idProduct);
}
