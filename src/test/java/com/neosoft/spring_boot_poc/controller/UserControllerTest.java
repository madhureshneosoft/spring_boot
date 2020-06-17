package com.neosoft.spring_boot_poc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neosoft.spring_boot_poc.model.SortByBirthDate;
import com.neosoft.spring_boot_poc.model.SortByJoiningDate;
import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserServiceImpl userService;

    UserController userController;

    User user1;
    User user2;
    User user3;
    User user4;
    User user5;
    String user1JsonString;
    String user2JsonString;
    String user3JsonString;
    String user4JsonString;
    String user5JsonString;
    String url = "/api/user";

    @Before
    public void setup() throws JsonProcessingException {
        userController = new UserController(userService);

        user1 = new User(1,
                "test1",
                "test1",
                "9978607891",
                new Date(1997-1900,11,31),
                new Date(2020-1900,2,15),
                "test1@gmail.com",
                "address 1",
                true,
                380061);
        user2 = new User(2,
                "test2",
                "test2",
                "9978607892",
                new Date(1991-1900,5,29),
                new Date(2020-1900,1,5),
                "test2@gmail.com",
                "address 2",
                true,
                380063);
        user3 = new User(3,
                "test3",
                "test3",
                "9978607893",
                new Date(1993-1900,2,2),
                new Date(2020-1900,5,19),
                "test3@gmail.com",
                "address 3",
                false,
                280062);
        user4 = new User(4,
                "test4",
                "test4",
                "9978607894",
                new Date(1999-1900,6,11),
                new Date(2020-1900,3,3),
                "test4@gmail.com",
                "address 4",
                true,
                380061);
        user5 = new User(5,
                "test5",
                "test5",
                "9978607895",
                new Date(1998-1900,8,19),
                new Date(2020-1900,0,7),
                "test5.test@gmail.com",
                "address 5",
                false,
                380061);

        user1JsonString = objectMapper.writeValueAsString(user1);
        user2JsonString = objectMapper.writeValueAsString(user2);
        user3JsonString = objectMapper.writeValueAsString(user3);
        user4JsonString = objectMapper.writeValueAsString(user4);
        user5JsonString = objectMapper.writeValueAsString(user5);
    }

    @Test
    public void addNewUser() throws Exception {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        String userTempJson = "{\n" +
                "    \"firstName\" : \"test95\",\n" +
                "    \"lastName\" : \"test95\",\n" +
                "    \"mobileNumber\" : \"9978607899\",\n" +
                "    \"dateOfBirth\" : \"1998-09-19\",\n" +
                "    \"dateOfJoin\" : \"2020-01-07\",\n" +
                "    \"emailId\" : \"test95.test@gmail.com\",\n" +
                "    \"address\" : \"address 95\",\n" +
                "    \"active\" : true,\n" +
                "    \"pincode\" : 380091\n" +
                "}";

        User userTemp = gson.fromJson(userTempJson,User.class);

        doReturn(userTemp).when(userService).addUser(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(userTempJson)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        verify(userService).addUser(Mockito.any());
    }

    @Test
    public void addNewUserWrong() throws Exception {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        String userTempJson = "{\n" +
                "    \"firstName\" : \"test54\",\n" +
                "    \"lastName\" : \"test54\",\n" +
                "    \"mobileNumber\" : \"9978607854\",\n" +
                "    \"dateOfBirth\" : \"2020-09-19\",\n" +
                "    \"dateOfJoin\" : \"2020-01-07\",\n" +
                "    \"emailId\" : \"test54.test@gmail.com\",\n" +
                "    \"address\" : \"address 54\",\n" +
                "    \"active\" : true,\n" +
                "    \"pincode\" : 380091\n" +
                "}";

        User userTemp = gson.fromJson(userTempJson,User.class);

        doReturn(userTemp).when(userService).addUser(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(userTempJson)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InputMismatchException))
                .andDo(MockMvcResultHandlers.print());

        verify(userService,times(0)).addUser(Mockito.any());
    }

    @Test
    public void getAllActiveUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);

        when(userService.selectAllActiveUsers())
                .thenReturn(userList);

        String response = objectMapper.writeValueAsString(userList);

        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).selectAllActiveUsers();
    }

    @Test
    public void getSpecificUserByNumber() throws Exception {
        url += "/getUser/9978607891";
        List<User> users = new ArrayList<>();
        users.add(user1);
        when(userService.selectByMobileNumber("9978607891")).thenReturn(user1);

        String response = objectMapper.writeValueAsString(users);

        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).selectByMobileNumber("9978607891");
    }

    @Test
    public void getSpecificUserByEmail() throws Exception {
        url += "/getUser/test1.test@gmail.com";

        List<User> users = new ArrayList<>();
        users.add(user1);

        when(userService.selectByEmailId(anyString())).thenReturn(user1);

        String response = objectMapper.writeValueAsString(users);

        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).selectByEmailId(anyString());
    }

    @Test
    public void getSpecificUserByName() throws Exception {
        url += "/getUser/test1";

        List<User> users = new ArrayList<>();
        users.add(user1);

        when(userService.selectAllUsersByFirstName(anyString())).thenReturn(users);
        when(userService.selectAllUsersByLastName(anyString())).thenReturn(users);

        String response = objectMapper.writeValueAsString(users);

        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).selectAllUsersByFirstName(anyString());
        verify(userService).selectAllUsersByLastName(anyString());
    }

    @Test
    public void getAllUsersByBirthAndJoinDate() throws Exception {
        url += "/getUser/2020-01-07";

        List<User> users = new ArrayList<>();
        users.add(user5);

        when(userService.selectAllUsersByBirthDate(anyString())).thenReturn(users);
        when(userService.selectAllUsersByJoinDate(anyString())).thenReturn(users);

        String response = objectMapper.writeValueAsString(users);

        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).selectAllUsersByBirthDate(anyString());
        verify(userService).selectAllUsersByJoinDate(anyString());
    }

    @Test
    public void getUsersByPincode() throws Exception {
        url += "/getUser/380061";

        List<User> expectedResult = Arrays.asList(user1,user4);

        when(userService.selectAllUsersByPincode(anyInt())).thenReturn(Arrays.asList(user1,user4));

        String response = objectMapper.writeValueAsString(expectedResult);

        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).selectAllUsersByPincode(anyInt());
    }

    @Test
    public void editUser() throws Exception {
        url += "/1";

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        String userTempJson = "{\n" +
                "    \"id\" : 1,\n"+
                "    \"firstName\" : \"test5\",\n" +
                "    \"lastName\" : \"test5\",\n" +
                "    \"mobileNumber\" : \"9978607895\",\n" +
                "    \"dateOfBirth\" : \"1998-09-19\",\n" +
                "    \"dateOfJoin\" : \"2020-01-07\",\n" +
                "    \"emailId\" : \"test5.test@gmail.com\",\n" +
                "    \"address\" : \"address 5\",\n" +
                "    \"active\" : true,\n" +
                "    \"pincode\" : 380061\n" +
                "}";

        User userTemp = gson.fromJson(userTempJson,User.class);

        doReturn(userTemp).when(userService).editUser(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(userTempJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void softDeleteUser() throws Exception {
        url += "/softDelete/1";

        User expectedResult = user1;

        expectedResult.setActive(false);

        when(userService.selectUser(Mockito.anyInt())).thenReturn(user1);

        doReturn(user1).when(userService).editUser(Mockito.any());

        userController.softDeleteUser(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        assertThat(expectedResult).isEqualTo(user1);

        verify(userService,times(2)).selectUser(Mockito.anyInt());
    }

    @Test
    public void hardDeleteUser() throws Exception {
        url += "/hardDelete/5";

        doNothing().when(userService).deleteUser(Mockito.anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).deleteUser(Mockito.anyInt());
    }

    @Test
    public void sortByJoinDate() throws Exception {
        url += "/sortByJoinDate";

        List<User> userList = Arrays.asList(user1, user2, user4);

        List<User> expectedUserList = Arrays.asList(user2, user1, user4);

        String response = objectMapper.writeValueAsString(expectedUserList);

        when(userService.selectAllActiveUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).selectAllActiveUsers();
    }

    @Test
    public void sortByBirthDate() throws Exception {
        url += "/sortByBirthDate";

        List<User> userList = Arrays.asList(user1, user2, user4);

        List<User> expectedUserList = Arrays.asList(user2, user1, user4);

        String response = objectMapper.writeValueAsString(expectedUserList);

        when(userService.selectAllActiveUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).selectAllActiveUsers();
    }
}
