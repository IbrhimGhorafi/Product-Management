package ighorafi.tpproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDTO {
    private Long codeProduct;
    private String label;
    private Long price;
    private Long codeCategory;
}
