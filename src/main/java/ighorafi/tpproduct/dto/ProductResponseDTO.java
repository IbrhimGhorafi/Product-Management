package ighorafi.tpproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long idProduct;
    private Long codeProduct;
    private String labelProduct;
    private Long price;
    private Long codeCategory;
    private String labelCategory;
}