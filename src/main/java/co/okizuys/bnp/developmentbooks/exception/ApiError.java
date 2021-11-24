package co.okizuys.bnp.developmentbooks.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

  private LocalDateTime timestamp;
  private int status;
  private String error;
  private String message;
  private List<ApiSubError> subErrors;

  public ApiError() {}

  public ApiError(
      LocalDateTime timestamp,
      int status,
      String error,
      String message,
      List<ApiSubError> subErrors) {
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.message = message;
    this.subErrors = subErrors;
  }

  public ApiError(LocalDateTime timestamp, int status, String error, String message) {
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.message = message;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<ApiSubError> getSubErrors() {
    return subErrors;
  }

  public void setSubErrors(List<ApiSubError> subErrors) {
    this.subErrors = subErrors;
  }
}
