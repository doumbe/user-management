package fr.doumbe.spring.usermanagement.init;

import fr.doumbe.spring.usermanagement.dao.UserDAO;
import fr.doumbe.spring.usermanagement.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInit implements CommandLineRunner {

  private final UserDAO userDAO;

  public DataInit(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  /**
   *
   * @param args
   */
  @Override
  public void run(String... args) throws Exception {
    long count = userDAO.count();

    if (count == 0) {
      User user = new User();
      user.setLastName("TRAORE");
      user.setUsername("Doumbe");
      user.setBirthdate(LocalDate.of(2000, 3, 17));
      user.setCountry("France");
      user.setGenre("M");
      user.setPhoneNumber(+601403322L);
      userDAO.save(user);

      User user1 = new User();
      user1.setLastName("ZIDANE");
      user1.setUsername("Zinédine");
      user1.setBirthdate(LocalDate.of(1991, 6, 10));
      user1.setCountry("France");
      user1.setGenre("M");
      user1.setPhoneNumber(+620301425L);
      userDAO.save(user1);

      User user2 = new User();
      user2.setLastName("ARMAND");
      user2.setUsername("Bruno");
      user2.setBirthdate(LocalDate.of(1990, 3, 17));
      user2.setCountry("France");
      user2.setGenre("M");
      user2.setPhoneNumber(+710101414L);
      userDAO.save(user2);
    }
  }

}
