package co.okizuys.bnp.developmentbooks.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
  private final Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);

  @ExceptionHandler(ShoppingCartCustomException.class)
  public ResponseEntity<Object> handleShoppingCartValidationException(
      ShoppingCartCustomException ex) {

    ApiError apiError =
        new ApiError(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getReason(),
            ex.getSubErrors());

    log.info(ExceptionUtils.getStackTrace(ex));

    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ApiError apiError =
        new ApiError(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "Validation failure",
            getSubErrors(ex));

    log.info(ExceptionUtils.getStackTrace(ex));

    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Object> handleAllUnhandledExceptions(RuntimeException ex) {

    ApiError apiError =
        new ApiError(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            ex.getMessage());

    log.error(ExceptionUtils.getStackTrace(ex));

    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private List<ApiSubError> getSubErrors(MethodArgumentNotValidException ex) {
    if (ex.getFieldError() == null) {
      return List.of();
    }
    return List.of(
        new ApiSubError(
            ex.getFieldError().getField(),
            ex.getFieldError().getRejectedValue(),
            ex.getFieldError().getDefaultMessage()));
  }
}
