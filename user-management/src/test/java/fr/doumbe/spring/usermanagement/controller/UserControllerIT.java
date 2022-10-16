package fr.doumbe.spring.usermanagement.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserControllerIT {

  private static final String PATH = "/api";

  @Autowired
  private MockMvc mockMvc;

  /**
   * @throws Exception
   */
  @Test
  void testGetAllUsers() throws Exception {
    mockMvc.perform(
        get(PATH + "/users")
    ).andExpect(status().isOk());
  }

  /**
   * @throws Exception
   */
  @Test
  @DisplayName("get user success")
  void testGetUser() throws Exception {
    String username = "Doumbe";
    mockMvc.perform(
            get(PATH + "/search").queryParam("username", username)
        )
        .andExpect(status().isOk())
        .andExpect(content().json("{\n" +
            "    \"id\": 1,\n" +
            "    \"username\": \"Doumbe\",\n" +
            "    \"birthdate\": \"1990-03-17\",\n" +
            "    \"country\": \"france\",\n" +
            "    \"phoneNumber\": null,\n" +
            "    \"genre\": null\n" +
            "}"));
  }

  /**
   * @throws Exception
   */
  @Test
  @DisplayName("get user failed")
  void testGetUserFailed() throws Exception {

    mockMvc.perform(
            get(PATH + "/search").queryParam("username", "")
        )
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("add user success")
  void testAddUser() throws Exception {
    String body = "{ \n\"id\":2,\n" +
        " \"username\":\"test\",\n" +
        " \"birthdate\":\"1990-03-17\",\n" +
        " \"country\":\"france\",\n" +
        " \"phoneNumber\":null,\n" +
        " \"genre\":null\n" +
        "}";
    mockMvc.perform(
            post(PATH + "/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("add user failed user not major")
  void testAddUserFailedUserNotMajor() throws Exception {
    String body = "{ \n\"id\":3,\n" +
        " \"username\":\"test\",\n" +
        " \"birthdate\":\"2018-03-17\",\n" +
        " \"country\":\"france\",\n" +
        " \"phoneNumber\":null,\n" +
        " \"genre\":null\n" +
        "}";
    mockMvc.perform(
            post(PATH + "/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("add user failed user not in france")
  void testAddUserFailedUserNoTInFrance() throws Exception {
    String body = "{ \n\"id\":3,\n" +
        " \"username\":\"test\",\n" +
        " \"birthdate\":\"1990-03-17\",\n" +
        " \"country\":\"USA\",\n" +
        " \"phoneNumber\":null,\n" +
        " \"genre\":null\n" +
        "}";
    mockMvc.perform(
            post(PATH + "/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
        .andExpect(status().isBadRequest());
  }

}