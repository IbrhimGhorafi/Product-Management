package ighorafi.tpproduct.service;

import ighorafi.tpproduct.dto.ProductDTO;
import ighorafi.tpproduct.entity.Category;
import ighorafi.tpproduct.entity.Product;
import ighorafi.tpproduct.exception.InvalidPriceException;
import ighorafi.tpproduct.exception.ProductAlreadyExistsException;
import ighorafi.tpproduct.exception.ProductNotFoundException;
import ighorafi.tpproduct.repository.ProductRepository;
import ighorafi.tpproduct.utils.PriceValidator;
import ighorafi.tpproduct.utils.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryServiceImpl categoryServiceImpl;
    private final ProductMapper productMapper;
    private PriceValidator priceValidator;
    @Override
    public Product addNewProduct(ProductDTO product) {
        if (!priceValidator.isValidPrice(product.getPrice())) {
            throw new InvalidPriceException("The price must be greater than 0");
        }

        Product existingProduct = productRepository.findByCode(product.getCodeProduct());
        if (existingProduct != null) {
            throw new ProductAlreadyExistsException("Product already exists");
        }

        Category category = categoryServiceImpl.getCategoryByCode(product.getCodeCategory());
        Product newProduct = productMapper.dtoToEntity(product, category);
        productRepository.save(newProduct);
        return newProduct;
    }

    @Override
    public Product updateProduct(Long codeProduct, ProductDTO product) {
        Product existingProduct = productRepository.findByCode(codeProduct);
        if (existingProduct == null) {
            throw new ProductNotFoundException("Product not found");
        }

        if (!priceValidator.isValidPrice(product.getPrice())) {
            throw new InvalidPriceException("The price must be greater than 0");
        }

        existingProduct.setLabel(product.getLabel());
        existingProduct.setPrice(product.getPrice());
        productRepository.save(existingProduct);
        return existingProduct;
    }

    @Override
    public Product getProductByCode(Long codeProduct) {
        return productRepository.findByCode(codeProduct);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProductById(Long idProduct) {
        Product existingProduct = productRepository.findById(idProduct).orElse(null);
        if (existingProduct == null) {
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(idProduct);
    }

}
