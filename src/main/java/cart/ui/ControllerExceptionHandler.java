package cart.ui;

import cart.dto.ApiErrorResponse;
import cart.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthenticationException(final AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiErrorResponse.from(e));
    }

    @ExceptionHandler({CartItemException.NotFoundException.class, OrderException.NotFound.class,
            ProductException.NotFoundException.class, PaymentException.NotFoundException.class})
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(final RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiErrorResponse.from(e));
    }

    @ExceptionHandler({CartItemException.IllegalMemberException.class, OrderException.IllegalMember.class})
    public ResponseEntity<ApiErrorResponse> handleAuthorizationException(final RuntimeException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiErrorResponse.from(e));
    }

    @ExceptionHandler({DiscountPolicyException.InvalidPolicyException.class, OrderException.PriceMismatchException.class})
    public ResponseEntity<ApiErrorResponse> handleInvalidPolicyException(final RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiErrorResponse.from(e));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(final RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorResponse.from(e));
    }

}
