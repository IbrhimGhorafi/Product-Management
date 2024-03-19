package ighorafi.tpproduct.utils;

import org.springframework.stereotype.Component;

@Component
public class PriceValidator implements PriceValidation {

    private static final Long DEFAULT_MIN_PRICE = 1L;


    public boolean isValidPrice(Long price) {
        return price >= DEFAULT_MIN_PRICE;
    }


}
