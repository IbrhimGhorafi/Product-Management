package ighorafi.tpproduct.service;

import ighorafi.tpproduct.dto.ProductRequestDTO;
import ighorafi.tpproduct.dto.ProductResponseDTO;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;
    private final PriceValidator priceValidator;

    @Override
    public ProductResponseDTO addNewProduct(ProductRequestDTO productRequestDTO) {
        validatePrice(productRequestDTO.getPrice());

        checkProductExistsByCode(productRequestDTO.getCodeProduct());

        Category category = getCategoryByCode(productRequestDTO.getCodeCategory());
        Product newProduct = productMapper.dtoToEntity(productRequestDTO, category);
        productRepository.save(newProduct);

        return productMapper.entityToDTO(newProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(Long codeProduct, ProductRequestDTO productRequestDTO) {
        Product existingProduct = findProductByCode(codeProduct);
        ensureProductFound(existingProduct);

        validatePrice(productRequestDTO.getPrice());

        existingProduct.setLabel(productRequestDTO.getLabel());
        existingProduct.setPrice(productRequestDTO.getPrice());
        productRepository.save(existingProduct);

        return productMapper.entityToDTO(existingProduct);
    }

    @Override
    public ProductResponseDTO getProductByCode(Long codeProduct) {
        Product product = findProductByCode(codeProduct);
        ensureProductFound(product);

        return productMapper.entityToDTO(product);
    }


    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProductById(Long idProduct) {
        Product existingProduct = productRepository.findById(idProduct).orElse(null);
        ensureProductFound(existingProduct);

        productRepository.deleteByCode(idProduct);
    }

    private void validatePrice(Long price) {
        if (!priceValidator.isValidPrice(price)) {
            throw new InvalidPriceException("The price must be greater than 0");
        }
    }

    private void checkProductExistsByCode(Long codeProduct) {
        if (productRepository.findByCode(codeProduct) != null) {
            throw new ProductAlreadyExistsException("Product already exists");
        }
    }

    private Product findProductByCode(Long codeProduct) {
        return productRepository.findByCode(codeProduct);
    }

    private Category getCategoryByCode(Long codeCategory) {
        return categoryService.getCategoryByCode(codeCategory);
    }

    private void ensureProductFound(Product product) {
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }
    }
}
