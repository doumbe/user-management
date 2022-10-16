package fr.doumbe.spring.usermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AddUserException extends RuntimeException {
  /**
   * Constructor with message
   *
   * @param message to display
   */
  public AddUserException(String message) {
    super(message);
  }

}
