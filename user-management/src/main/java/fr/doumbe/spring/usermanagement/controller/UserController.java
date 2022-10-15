package fr.doumbe.spring.usermanagement.controller;

import java.util.List;
import java.util.Map;

import fr.doumbe.spring.usermanagement.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.doumbe.spring.usermanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api( description="API for the CRUD of users")

public class UserController {
  private final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Constructor
   *
   * @param userService
   */


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

}
