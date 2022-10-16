package fr.doumbe.spring.usermanagement.rule;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(classes = UserRule.class)
public class UserRuleIT {

  private UserRule userRule;

  @BeforeEach
  void setup() {
    this.userRule = new UserRule();
  }

  @Test
  @DisplayName("is french country")
  void isFrenchCountryTest() {
    // yes
    Assertions.assertTrue(userRule.isFrenchCountry("France"));
    // no
    Assertions.assertFalse(userRule.isFrenchCountry("États-Unis"));
  }

  @Test
  @DisplayName("is user major")
  void isMajorTest() {
    // yes
    Assertions.assertTrue(userRule.isMajor(LocalDate.of(2000, 1, 4)));
    // no
    Assertions.assertFalse(userRule.isMajor(LocalDate.of(2021, 1, 4)));
  }

}
