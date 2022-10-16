package fr.doumbe.spring.usermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AddUserException extends RuntimeException {
  /**
   * Constructor with message
   *
   * @param message to display
   */
  public AddUserException(String message) {
    super("You don't have the rights: " + message);
  }

}
