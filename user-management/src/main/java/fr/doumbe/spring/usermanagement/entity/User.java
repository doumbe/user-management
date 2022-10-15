package fr.doumbe.spring.usermanagement.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "\"User\"")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Size(min = 2)
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Size(min = 2)
  @Column(name = "lastName", nullable = false, unique = true)
  private String lastName;

  @Column(name = "birthdate")
  private LocalDate birthdate;
  @Size(min = 2)
  @Column(name = "country", nullable = false)
  private String country;
  @Column(name = "phoneNumber")
  private Long phoneNumber;
  @Size(min = 1)
  @Column(name = "genre")
  private String genre;

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public void setPhoneNumber(String s, long setPhoneNumber) {
  }

}
