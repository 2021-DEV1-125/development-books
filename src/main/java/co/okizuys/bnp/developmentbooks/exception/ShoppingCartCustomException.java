package co.okizuys.bnp.developmentbooks.exception;

import org.springframework.lang.Nullable;

import java.util.List;

public class ShoppingCartCustomException extends RuntimeException {

  private final String reason;
  private final Throwable cause;
  private final List<ApiSubError> subErrors;

  public ShoppingCartCustomException(@Nullable String reason, @Nullable List<ApiSubError> subErrors) {
    this.reason = reason;
    this.subErrors = subErrors;
    this.cause = null;
  }

  public String getReason() {
    return reason;
  }

  @Override
  public Throwable getCause() {
    return cause;
  }

  public List<ApiSubError> getSubErrors() {
    return subErrors;
  }
}
