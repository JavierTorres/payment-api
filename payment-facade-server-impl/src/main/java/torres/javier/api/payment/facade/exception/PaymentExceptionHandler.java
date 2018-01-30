package torres.javier.api.payment.facade.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import torres.javier.api.payment.facade.model.ErrorResponse;

@ControllerAdvice
public class PaymentExceptionHandler {

  private static Logger logger = LoggerFactory.getLogger(PaymentExceptionHandler.class);

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleRequiredField(RequiredFieldException e) {
    return handleException(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
    return handleException(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException e) {
    return handleException(e, HttpStatus.NOT_FOUND);
  }

  private ResponseEntity<ErrorResponse> handleException(Exception e, HttpStatus httpStatus) {
    logger.warn(e.getMessage());
    return new ResponseEntity(new ErrorResponse().message(e.getMessage()), httpStatus);
  }
}
