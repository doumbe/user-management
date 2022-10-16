package fr.doumbe.spring.usermanagement.rule;

import java.time.LocalDate;

import fr.doumbe.spring.usermanagement.constants.Constants;

import org.springframework.stereotype.Component;

@Component
public class UserRule {

  /**
   * Check if some country is French or not
   *
   * @param country to checked
   * @return a boolean, true if is French country or false to other case
   */
  public boolean isFrenchCountry(String country) {

    return country.equalsIgnoreCase(Constants.COUNTRY_ALLOWED);
  }

  /**
   * Check if some date of birth correspond to a majority or not
   *
   * @param localDate, date of birth to checked
   * @return a boolean, true if is major or false to other case
   */
  public boolean isMajor(LocalDate localDate) {
    return LocalDate.now().getYear() - localDate.getYear() > Constants.AGE_ALLOWED ||
        (LocalDate.now().getYear() - localDate.getYear() == Constants.AGE_ALLOWED
            && LocalDate.now().getDayOfYear() > localDate.getDayOfYear());
  }

}
