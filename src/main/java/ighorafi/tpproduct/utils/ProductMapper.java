package ighorafi.tpproduct.utils;

import ighorafi.tpproduct.dto.ProductDTO;
import ighorafi.tpproduct.entity.Category;
import ighorafi.tpproduct.entity.Product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product dtoToEntity(ProductDTO productDTO, Category category){
        return Product.builder()
                .code(productDTO.getCodeProduct())
                .label(productDTO.getLabel())
                .category(category)
                .price(productDTO.getPrice())
                .build();
    }
}
