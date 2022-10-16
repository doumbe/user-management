package fr.doumbe.spring.usermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SearchUserException extends RuntimeException {

  /**
   * Constructor with message
   *
   * @param message to display
   */
  public SearchUserException(String message) {
    super(message);
  }
}
