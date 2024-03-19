package ighorafi.tpproduct.utils;

public class PriceValidator {
    private PriceValidator() {}

    public static boolean isGreaterThanZero(Long price){
        return price>0;
    }
}
