package fr.doumbe.spring.usermanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.doumbe.spring.usermanagement.dao.UserDAO;
import fr.doumbe.spring.usermanagement.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
  private final UserDAO userDAO;

  public UserService(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  /**
   *
   * @return list of user in database
   */
  @Transactional(readOnly = true)
  public List<User> getAllUser() {
    return userDAO.findAll();
  }

  /**
   *
   * @param username refers to user to find in database
   * @return
   */
  @Transactional(readOnly = true)
  public User getUserByUsername(String username) {
    return userDAO.findByUsername(username);
  }
  public Map<String, Object> getUserByPage(int page, int size) {
    Map<String, Object> result = new HashMap<>();
    PageRequest pageable = PageRequest.of(page, size);
    Page<User> userPage = userDAO.findAll(pageable);
    result.put("data", userPage.getContent());
    result.put("Total_of_pages", userPage.getTotalPages());
    result.put("Total_of_elements", userPage.getTotalElements());
    result.put("Current_page", userPage.getNumber());
    return result;
  }

}
