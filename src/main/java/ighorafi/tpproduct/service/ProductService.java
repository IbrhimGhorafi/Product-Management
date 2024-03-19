package ighorafi.tpproduct.service;

import ighorafi.tpproduct.dto.ProductDTO;
import ighorafi.tpproduct.entity.Product;

import java.util.List;

public interface ProductService {
    Product addNewProduct(ProductDTO product);
    Product updateProduct(Long codeProduct, ProductDTO product);
    Product getProductByCode(Long codeProduct);
    List<Product>getAllProduct();
    void deleteProductById(Long idProduct);
}
