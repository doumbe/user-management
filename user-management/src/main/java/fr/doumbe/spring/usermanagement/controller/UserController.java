package fr.doumbe.spring.usermanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import fr.doumbe.spring.usermanagement.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.doumbe.spring.usermanagement.exception.AddUserException;
import fr.doumbe.spring.usermanagement.exception.SearchUserException;
import fr.doumbe.spring.usermanagement.rule.UserRule;
import fr.doumbe.spring.usermanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api( description="API for the CRUD of users")

public class UserController {
  private final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserService userService;
  private final UserRule userRule;
  /**
   * Constructor
   *
   * @param userService
   * @param userRule
   */
  public UserController(UserService userService, UserRule userRule) {
    this.userService = userService;

    this.userRule = userRule;
  }

  /**
   * used to find all users in database
   *
   * @return list of all users in database
   */
  @ApiOperation(value = "list of all users in database")
  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUser();
    return ResponseEntity.ok().body(users);
  }

  public Map<String, Object> getUsersByPage(@RequestParam(name = "page", defaultValue = "0")
  int page,
      @RequestParam(name = "size", defaultValue = "5")
      int size) {
    long time = System.currentTimeMillis();
    logger.info("### starting getUserByPage ... ###");

    Map<String, Object> result = userService.getUserByPage(page, size);
    time = System.currentTimeMillis() - time;
    logger.info("### Ending GetUsersByLastName ..., time : {} ###", time);
    return result;
  }

  /**
   * used to find user associated to username in database
   * @param username refers to the name of the user to find
   * @return ResponseEntity<User>
   */
  @ApiOperation(value = "used to find user associated to username in database")
  @GetMapping("/search")
  public ResponseEntity<User> getUser(@RequestParam("username") String username) {
    long time = System.currentTimeMillis();
    logger.info("### starting getUserByUsername ... ###");
    User user = userService.getUserByUsername(username);
    return getUserResponseEntity(time, user);
  }

  private ResponseEntity<User> getUserResponseEntity(long time, User user) {
    if (user != null) {
      time = System.currentTimeMillis() - time;
      logger.info("### Ending GetUsersByLastName ..., time : {} ###", time);
      return ResponseEntity.ok().body(user);
    }
    time = System.currentTimeMillis() - time;
    logger.info("### Ending GetUsersByLastName ..., time : {} ###", time);
    throw new SearchUserException("User not found");
  }

  /**
   * used to find user associated to username in database
   * @param id refers to the name of the user to find
   * @return ResponseEntity<User>
   */
  @ApiOperation(value = "used to find user id associated to username in database")
  @GetMapping("/{id}")
  public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") Long id) {
    long time = System.currentTimeMillis();
    logger.info("### starting getUserByUsername ... ###");
    Optional<User> user = userService.findUserById(id);
    if (user.isPresent()) {
      time = System.currentTimeMillis() - time;
      logger.info("### Ending GetUsersByLastName ..., time : {} ###", time);
      return ResponseEntity.ok().body(user);
    }
    time = System.currentTimeMillis() - time;
    logger.info("### Ending GetUsersByLastName ..., time : {} ###", time);
    throw new SearchUserException("User not found");
  }

  /**
   * used to add user in database
   * @param user
   * @return ResponseEntity<> with object containing userId, status, result
   */
  @ApiOperation(value = "used to add user in database")
  @PostMapping(value = "/addUser", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
    long time = System.currentTimeMillis();
    System.out.println(user);
    logger.info("### starting add user ... ###");
    if (!userRule.isFrenchCountry(user.getCountry())) {
      logger.info("### Pays not Autorized ###");
      //throw new AddUserException("Country Not Allowed");
      return new ResponseEntity<>(new AddUserException("Please enter the correct country").getMessage(),
          HttpStatus.UNAUTHORIZED);
    }
    if (!userRule.isMajor(user.getBirthdate())) {
      logger.info("### Age not Autorized ###");
      //throw new AddUserException("Age lower than 18");
      return new ResponseEntity<>(new AddUserException("Please enter the correct age").getMessage(),
          HttpStatus.UNAUTHORIZED);
    }
    User userSaved = userService.addUser(user);

    Map<String, Object> result = new HashMap<>();
    result.put("userId", userSaved.getId());
    result.put("status", HttpStatus.CREATED);
    result.put("result", "SUCCESS");

    time = System.currentTimeMillis() - time;
    logger.info("### Ending add user ..., time : {} ###", time);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @ApiOperation(value = "used to delete user by id in database")
  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public void deleteById(@PathVariable("id") Long id) {
    if (userService.findUserById(id).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    userService.deleteById(id);
  }

}
