package ighorafi.tpproduct.controller;

import ighorafi.tpproduct.exception.InvalidPriceException;
import ighorafi.tpproduct.exception.ProductAlreadyExistsException;
import ighorafi.tpproduct.exception.ProductNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class ProductControllerAdvice {

    @ExceptionHandler({InvalidPriceException.class, ProductAlreadyExistsException.class, ProductNotFoundException.class})
    public ResponseEntity<ApiError> handleException(RuntimeException e) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class ApiError {
        private HttpStatus status;
        private String message;
    }
}
