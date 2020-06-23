package com.neosoft.spring_boot_poc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neosoft.spring_boot_poc.model.*;
import com.neosoft.spring_boot_poc.service.UserDetailService;
import com.neosoft.spring_boot_poc.service.UserEmploymentDetailService;
import com.neosoft.spring_boot_poc.service.UserServiceImpl;
import org.apache.coyote.Response;
import org.junit.Before;
import org.junit.Test;
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
import org.springframework.web.bind.MissingServletRequestParameterException;

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

    @MockBean
    UserDetailService userDetailService;

    @MockBean
    UserEmploymentDetailService userEmploymentDetailService;

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

    void setUser(User user) {
        user.getUserEmploymentDetail().setUser(user);
        user.getUserEducationDetail().setUser(user);
        user.getUserRole().setUser(user);
        user.getUserDetail().setUser(user);
        user.getUserProjectDetail().forEach(userProjectDetail -> userProjectDetail.setUser(user));
    }

    @Before
    public void setup() throws JsonProcessingException {
        userController = new UserController(userService,userDetailService,userEmploymentDetailService);

        user1 = new User(1,
                "john",
                "P@ssw0rd",
                true,
                Date.valueOf("2020-01-07"),
                Date.valueOf("2020-01-07"),
                new UserDetail(1,
                        null,
                        "john",
                        "cena",
                        "9978607891",
                        Date.valueOf("1998-09-19"),
                        "john@gmail.com",
                        "test address",
                        380061),
                new UserEducationDetail(1,
                        null,
                        86.32f,
                        81.83f,
                        "gseb",
                        "gsheb",
                        8.96f,
                        "GTU"),
                new UserEmploymentDetail(1,
                        null,
                        25000,
                        "john@gmail.com",
                        "9999999999",
                        "Java",
                        Date.valueOf("2020-01-07"),
                        Byte.valueOf("3")),
                Arrays.asList(new UserProjectDetail(1,
                                null,
                                "goods",
                                "goods and services",
                                "DHL",
                                true,
                                Date.valueOf("2020-03-15"),
                                null),
                        new UserProjectDetail(2,
                                null,
                                "goods",
                                "goods and services",
                                "DHL",
                                true,
                                Date.valueOf("2020-03-15"),
                                null)),
                new UserRole(1, "Developer", null));
        setUser(user1);

        user2 = new User(2,
                "smith",
                "P@ssw0rd1",
                true,
                Date.valueOf("2020-03-17"),
                Date.valueOf("2020-03-17"),
                new UserDetail(2,
                        null,
                        "will",
                        "smith",
                        "9978607892",
                        Date.valueOf("1994-04-29"),
                        "smith@gmail.com",
                        "test address2",
                        380061),
                new UserEducationDetail(2,
                        null,
                        83.61f,
                        86.63f,
                        "gseb",
                        "gsheb",
                        8.99f,
                        "GTU"),
                new UserEmploymentDetail(2,
                        null,
                        23000,
                        "smith@gmail.com",
                        "9999999998",
                        "dot net",
                        Date.valueOf("2020-03-17"),
                        Byte.valueOf("2")),
                Arrays.asList(new UserProjectDetail(3,
                                null,
                                "trading",
                                "stocks and trading",
                                "xyz",
                                true,
                                Date.valueOf("2020-02-25"),
                                null),
                        new UserProjectDetail(4,
                                null,
                                "marketing",
                                "marketing services",
                                "abc",
                                true,
                                Date.valueOf("2016-01-17"),
                                null)),
                new UserRole(2, "Developer", null));
        setUser(user2);

        user3 = new User(3,
                "robert",
                "P@ssw0rd",
                false,
                Date.valueOf("2020-01-17"),
                Date.valueOf("2020-01-17"),
                new UserDetail(3,
                        null,
                        "robert",
                        "taylor",
                        "9978607893",
                        Date.valueOf("1997-11-09"),
                        "robert@gmail.com",
                        "test address3",
                        380063),
                new UserEducationDetail(3,
                        null,
                        81.12f,
                        81.21f,
                        "gseb",
                        "gsheb",
                        8.31f,
                        "GTU"),
                new UserEmploymentDetail(3,
                        null,
                        25500,
                        "robert@gmail.com",
                        "9999999997",
                        "php",
                        Date.valueOf("2020-01-27"),
                        Byte.valueOf("1")),
                Arrays.asList(new UserProjectDetail(5,
                                null,
                                "website",
                                "website",
                                "MNB",
                                true,
                                Date.valueOf("2020-02-27"),
                                null),
                        new UserProjectDetail(6,
                                null,
                                "social media",
                                "social media",
                                "instagram",
                                false,
                                Date.valueOf("2019-04-25"),
                                Date.valueOf("2020-02-21"))),
                new UserRole(3, "Tester", null));
        setUser(user3);

        user4 = new User(4,
                "mark",
                "P@ssw0rd3",
                true,
                Date.valueOf("2020-04-27"),
                Date.valueOf("2020-04-27"),
                new UserDetail(4,
                        null,
                        "mark",
                        "evans",
                        "9978607894",
                        Date.valueOf("1994-04-14"),
                        "mark@gmail.com",
                        "test address 4",
                        380064),
                new UserEducationDetail(4,
                        null,
                        83.34f,
                        86.43f,
                        "gseb",
                        "gsheb",
                        9.23f,
                        "IIT"),
                new UserEmploymentDetail(4,
                        null,
                        29000,
                        "mark@gmail.com",
                        "9999999996",
                        "php",
                        Date.valueOf("2020-05-24"),
                        Byte.valueOf("10")),
                Collections.singletonList(new UserProjectDetail(7,
                        null,
                        "netflix",
                        "netflix",
                        "netflix",
                        true,
                        Date.valueOf("2019-05-13"),
                        null)),
                new UserRole(4, "Developer", null));
        setUser(user4);

        user5 = new User(5,
                "chris",
                "P@ssw0rd5",
                true,
                Date.valueOf("2020-04-19"),
                Date.valueOf("2020-04-19"),
                new UserDetail(5,
                        null,
                        "chris",
                        "russel",
                        "9978607895",
                        Date.valueOf("1998-04-14"),
                        "chris@gmail.com",
                        "test address 5",
                        380061),
                new UserEducationDetail(5,
                        null,
                        83.32f,
                        91.43f,
                        "gseb",
                        "gsheb",
                        9.91f,
                        "IIT"),
                new UserEmploymentDetail(5,
                        null,
                        27000,
                        "chris@gmail.com",
                        "9999999995",
                        "Java",
                        Date.valueOf("2020-05-27"),
                        Byte.valueOf("7")),
                Collections.singletonList(new UserProjectDetail(5,
                        null,
                        "XYZ",
                        "XYZ",
                        "XYZ",
                        false,
                        Date.valueOf("2018-06-11"),
                        Date.valueOf("2020-01-13"))),
                new UserRole(5, "Developer", null));
        setUser(user5);

        user1JsonString = objectMapper.writeValueAsString(user1);
        user2JsonString = objectMapper.writeValueAsString(user2);
        user3JsonString = objectMapper.writeValueAsString(user3);
        user4JsonString = objectMapper.writeValueAsString(user4);
        user5JsonString = objectMapper.writeValueAsString(user5);
    }

    @Test
    public void addNewUser() throws Exception {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        String userTempJson = "{\"userName\":\"john\",\"password\":\"P@ssw0rd4\",\"active\":true,\"createDate\":\"2020-06-16\",\"updateDate\":\"2020-06-17\",\"userDetail\":{\"firstName\":\"john\",\"lastName\":\"cena\",\"mobileNumber\":\"9978607890\",\"dateOfBirth\":\"1998-09-18\",\"emailId\":\"john@gmail.com\",\"address\":\"Vastrapur\",\"pincode\":380061},\"userEducationDetail\":{\"sscPercentage\":79.94,\"hscPercentage\":89.13,\"sscBoardName\":\"gseb\",\"hscBoardName\":\"gsheb\",\"cgpa\":7.44,\"universityName\":\"GTU\"},\"userEmploymentDetail\":{\"salary\":200000,\"workEmail\":\"john@neosoft.com\",\"workMobileNumber\":\"1111111111\",\"department\":\"JAVA\",\"dateOfJoin\":\"2020-01-06\",\"experience\":2},\"userProjectDetail\":[{\"projectName\":\"Trade\",\"projectDetail\":\"Trade\",\"projectCompany\":\"XYZ\",\"active\":true,\"startDate\":\"2020-02-10\",\"endDate\":null},{\"projectName\":\"Share\",\"projectDetail\":\"Share\",\"projectCompany\":\"ABC\",\"active\":false,\"startDate\":\"2011-07-23\",\"endDate\":\"2012-07-23\"}],\"userRole\":{\"role\":\"Developer\"}}";

        User userTemp = gson.fromJson(userTempJson, User.class);

        doReturn(userTemp).when(userService).addUser(userTemp);

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

        String userTempJson = "{\"userName\":\"john\",\"password\":\"P@ssw0rd4\",\"active\":true,\"createDate\":\"2020-06-16\",\"updateDate\":\"2020-06-17\",\"userDetail\":{\"firstName\":\"john\",\"lastName\":\"cena\",\"mobileNumber\":\"9978607890\",\"dateOfBirth\":\"1999-06-18\",\"emailId\":\"john@gmail.com\",\"address\":\"Vastrapur\",\"pincode\":380061},\"userEducationDetail\":{\"sscPercentage\":79.94,\"hscPercentage\":89.13,\"sscBoardName\":\"gseb\",\"hscBoardName\":\"gsheb\",\"cgpa\":7.44,\"universityName\":\"GTU\"},\"userEmploymentDetail\":{\"salary\":200000,\"workEmail\":\"john@neosoft.com\",\"workMobileNumber\":\"1111111111\",\"department\":\"JAVA\",\"dateOfJoin\":\"2020-01-06\",\"experience\":2},\"userProjectDetail\":[{\"projectName\":\"Trade\",\"projectDetail\":\"Trade\",\"projectCompany\":\"XYZ\",\"active\":true,\"startDate\":\"2020-02-10\",\"endDate\":null},{\"projectName\":\"Share\",\"projectDetail\":\"Share\",\"projectCompany\":\"ABC\",\"active\":false,\"startDate\":\"2011-07-23\",\"endDate\":\"2012-07-23\"}],\"userRole\":{\"role\":\"Developer\"}}";

        doThrow(InputMismatchException.class).when(userService).addUser(any());

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(userTempJson)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InputMismatchException))
                .andDo(MockMvcResultHandlers.print());

        verify(userService,times(1)).addUser(Mockito.any());
    }

    @Test
    public void addNewUserMissingParameter() throws Exception {

        String userTempJson = "{\"createDate\":\"2020-06-16\",\"updateDate\":\"2020-06-17\",\"userDetail\":{\"firstName\":\"john\",\"lastName\":\"cena\",\"dateOfBirth\":\"1999-06-18\",\"emailId\":\"john@gmail.com\",\"address\":\"Vastrapur\",\"pincode\":380061},\"userEducationDetail\":{\"sscPercentage\":79.94,\"hscPercentage\":89.13,\"sscBoardName\":\"gseb\",\"hscBoardName\":\"gsheb\",\"cgpa\":7.44,\"universityName\":\"GTU\"},\"userEmploymentDetail\":{\"salary\":200000,\"workEmail\":\"john@neosoft.com\",\"department\":\"JAVA\",\"dateOfJoin\":\"2020-01-06\",\"experience\":2},\"userProjectDetail\":[{\"projectName\":\"Trade\",\"projectDetail\":\"Trade\",\"projectCompany\":\"XYZ\",\"active\":true,\"startDate\":\"2020-02-10\",\"endDate\":null},{\"projectName\":\"Share\",\"projectDetail\":\"Share\",\"projectCompany\":\"ABC\",\"active\":false,\"startDate\":\"2011-07-23\",\"endDate\":\"2012-07-23\"}],\"userRole\":{\"role\":\"Developer\"}}";

        doThrow(InputMismatchException.class).when(userService).addUser(any());

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(userTempJson)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

        verify(userService,times(0)).addUser(Mockito.any());
    }

    @Test
    public void addNewUserArgumentNotValid() throws Exception {

        String userTempJson = "{\"userName\":@#$@#$\",\"password\":\"P@ssw0rd4\",\"active\":\"123543\",\"createDate\":\"2020-06-16\",\"updateDate\":\"2020-06-17\",\"userDetail\":{\"firstName\":\"john\",\"lastName\":\"cena\",\"mobileNumber\":\"9291234567\",\"dateOfBirth\":\"1999-06-18\",\"emailId\":\"john@gmail.com\",\"address\":\"Vastrapur\",\"pincode\":380061},\"userEducationDetail\":{\"sscPercentage\":79.94,\"hscPercentage\":89.13,\"sscBoardName\":\"gseb\",\"hscBoardName\":\"gsheb\",\"cgpa\":7.44,\"universityName\":\"GTU\"},\"userEmploymentDetail\":{\"salary\":200000,\"workEmail\":\"john@neosoft.com\",\"workMobileNumber\":\"1111111111\",\"department\":\"JAVA\",\"dateOfJoin\":\"2020-01-06\",\"experience\":2},\"userProjectDetail\":[{\"projectName\":\"Trade\",\"projectDetail\":\"Trade\",\"projectCompany\":\"XYZ\",\"active\":true,\"startDate\":\"2020-02-10\",\"endDate\":null},{\"projectName\":\"Share\",\"projectDetail\":\"Share\",\"projectCompany\":\"ABC\",\"active\":false,\"startDate\":\"2011-07-23\",\"endDate\":\"2012-07-23\"}],\"userRole\":{\"role\":\"Developer\"}}";

        doThrow(InputMismatchException.class).when(userService).addUser(any());

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(userTempJson)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

        verify(userService,times(0)).addUser(Mockito.any());
    }

    @Test
    public void addNewUserConstraintViolation() throws Exception {

        String userTempJson = "{\"userName\":\"john\",\"password\":\"P@ssw0rd4\",\"active\":true,\"createDate\":\"2020-06-16\",\"updateDate\":\"2020-06-17\",\"userDetail\":{\"firstName\":\"john\",\"lastName\":\"cena\",\"mobileNumber\":\"9291234567\",\"dateOfBirth\":\"1999-06-18\",\"emailId\":\"john@gmail.com\",\"address\":\"Vastrapur\",\"pincode\":\"hello\"},\"userEducationDetail\":{\"sscPercentage\":79.94,\"hscPercentage\":89.13,\"sscBoardName\":\"gseb\",\"hscBoardName\":\"gsheb\",\"cgpa\":7.44,\"universityName\":\"GTU\"},\"userEmploymentDetail\":{\"salary\":200000,\"workEmail\":\"john@neosoft.com\",\"workMobileNumber\":\"1111111111\",\"department\":\"JAVA\",\"dateOfJoin\":\"2020-01-06\",\"experience\":2},\"userProjectDetail\":[{\"projectName\":\"Trade\",\"projectDetail\":\"Trade\",\"projectCompany\":\"XYZ\",\"active\":true,\"startDate\":\"2020-02-10\",\"endDate\":null},{\"projectName\":\"Share\",\"projectDetail\":\"Share\",\"projectCompany\":\"ABC\",\"active\":false,\"startDate\":\"2011-07-23\",\"endDate\":\"2012-07-23\"}],\"userRole\":{\"role\":\"Developer\"}}";

        doThrow(InputMismatchException.class).when(userService).addUser(any());

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(userTempJson)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

        verify(userService,times(0)).addUser(Mockito.any());
    }

    @Test
    public void getAllActiveUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
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
    public void getAllInActiveUsers() throws Exception {
        url += "/inactive";

        List<User> inactiveUserList = new ArrayList<>();
        inactiveUserList.add(user3);

        when(userService.selectAllInactiveUsers()).thenReturn(inactiveUserList);

        String response = objectMapper.writeValueAsString(inactiveUserList);

        mockMvc.perform(MockMvcRequestBuilders.get(url).characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).selectAllInactiveUsers();
    }

    @Test
    public void dynamicSearch() throws Exception {
        url += "/getUser/userDetail.mobileNumber=9978607891";
        List<User> users = new ArrayList<>();
        users.add(user1);

        when(userService.dynamicSearch(anyString())).thenReturn(Collections.singletonList(user1));

        String response = objectMapper.writeValueAsString(users);

        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).dynamicSearch(anyString());
    }

    @Test
    public void editUser() throws Exception {
        url += "/1";

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        String userTempJson = "{\"id\":1,\"userName\":\"john\",\"password\":\"P@ssw0rd4\",\"active\":true,\"createDate\":\"2020-06-16\",\"updateDate\":\"2020-06-17\",\"userDetail\":{\"id\":1,\"firstName\":\"john\",\"lastName\":\"cena\",\"mobileNumber\":\"9978607890\",\"dateOfBirth\":\"1998-09-18\",\"emailId\":\"john@gmail.com\",\"address\":\"Vastrapur\",\"pincode\":380061},\"userEducationDetail\":{\"id\":1,\"sscPercentage\":79.94,\"hscPercentage\":89.13,\"sscBoardName\":\"gseb\",\"hscBoardName\":\"gsheb\",\"cgpa\":7.44,\"universityName\":\"GTU\"},\"userEmploymentDetail\":{\"id\":1,\"salary\":200000,\"workEmail\":\"john@neosoft.com\",\"workMobileNumber\":\"1111111111\",\"department\":\"JAVA\",\"dateOfJoin\":\"2020-01-06\",\"experience\":2},\"userProjectDetail\":[{\"id\":1,\"projectName\":\"Trade\",\"projectDetail\":\"Trade\",\"projectCompany\":\"XYZ\",\"active\":true,\"startDate\":\"2020-02-10\",\"endDate\":null},{\"id\":2,\"projectName\":\"Share\",\"projectDetail\":\"Share\",\"projectCompany\":\"ABC\",\"active\":false,\"startDate\":\"2011-07-23\",\"endDate\":\"2012-07-23\"}],\"userRole\":{\"id\":1,\"role\":\"Developer\"}}";

        User userTemp = gson.fromJson(userTempJson, User.class);

        doReturn(userTemp).when(userService).editUser(Mockito.any(),anyInt());

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

        doReturn(user1).when(userService).editUser(Mockito.any(),anyInt());

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
        url += "/sortByuserEmploymentDetail.dateOfJoin";

        List<User> userList = Arrays.asList(user1, user2, user4, user5);

        String response = objectMapper.writeValueAsString(userList);

        when(userService.selectAllUserSortBy(anyString())).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).selectAllUserSortBy(anyString());
    }

    /*
//    @Test
//    public void getSpecificUserById() throws Exception {
//        url += "/getUser/3";
//
//        when(userService.selectUser(anyInt())).thenReturn(user3);
//
//        String response = objectMapper.writeValueAsString(Collections.singletonList(user3));
//
//        mockMvc.perform(MockMvcRequestBuilders.get(url))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.content().json(response))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        verify(userService).selectUser(anyInt());
//    }
//
//    @Test
//    public void getSpecificUserByEmail() throws Exception {
//        url += "/getUser/test1.test@gmail.com";
//        List<UserDetail> userList = Collections.singletonList(user1);
//
////        when(userService.selectByEmailId(anyString())).thenReturn(user1);
//        doReturn(user1).when(userService).selectByEmailId(anyString());
//
//        String response = objectMapper.writeValueAsString(userList);
//
//        mockMvc.perform(MockMvcRequestBuilders.get(url))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.content().json(response))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        verify(userService).selectByEmailId(anyString());
//    }
//
//    @Test
//    public void getSpecificUserByName() throws Exception {
//        url += "/getUser/test1";
//
//        List<UserDetail> users = new ArrayList<>();
//        users.add(user1);
//
//        when(userService.selectAllUsersByFirstName(anyString())).thenReturn(users);
//        when(userService.selectAllUsersByLastName(anyString())).thenReturn(users);
//
//        String response = objectMapper.writeValueAsString(users);
//
//        mockMvc.perform(MockMvcRequestBuilders.get(url))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.content().json(response))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        verify(userService).selectAllUsersByFirstName(anyString());
//        verify(userService).selectAllUsersByLastName(anyString());
//    }
//
//    @Test
//    public void getAllUsersByBirthAndJoinDate() throws Exception {
//        url += "/getUser/2020-01-07";
//
//        List<UserDetail> users = new ArrayList<>();
//        users.add(user5);
//
//        when(userService.selectAllUsersByBirthDate(anyString())).thenReturn(users);
//        when(userService.selectAllUsersByJoinDate(anyString())).thenReturn(users);
//
//        String response = objectMapper.writeValueAsString(users);
//
//        mockMvc.perform(MockMvcRequestBuilders.get(url))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.content().json(response))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        verify(userService).selectAllUsersByBirthDate(anyString());
//        verify(userService).selectAllUsersByJoinDate(anyString());
//    }
//
//    @Test
//    public void getUsersByPincode() throws Exception {
//        url += "/getUser/380061";
//
//        List<UserDetail> expectedResult = Arrays.asList(user1,user4);
//
//        when(userService.selectAllUsersByPincode(anyInt())).thenReturn(Arrays.asList(user1,user4));
//
//        String response = objectMapper.writeValueAsString(expectedResult);
//
//        mockMvc.perform(MockMvcRequestBuilders.get(url))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.content().json(response))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        verify(userService).selectAllUsersByPincode(anyInt());
//    }
//
 */

}
