package ighorafi.tpproduct.utils;

import ighorafi.tpproduct.dto.ProductRequestDTO;
import ighorafi.tpproduct.dto.ProductResponseDTO;
import ighorafi.tpproduct.entity.Category;
import ighorafi.tpproduct.entity.Product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product dtoToEntity(ProductRequestDTO productRequestDTO, Category category){
        return Product.builder()
                .code(productRequestDTO.getCodeProduct())
                .label(productRequestDTO.getLabel())
                .category(category)
                .price(productRequestDTO.getPrice())
                .build();
    }

    public ProductResponseDTO entityToDTO(Product product) {
        return ProductResponseDTO.builder()
                .idProduct(product.getIdProduct())
                .codeProduct(product.getCode())
                .labelProduct(product.getLabel())
                .price(product.getPrice())
                .codeCategory(product.getCategory().getIdCategory())
                .labelCategory(product.getCategory().getLabel())
                .build();
    }
}
