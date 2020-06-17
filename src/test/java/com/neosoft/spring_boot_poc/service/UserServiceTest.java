package com.neosoft.spring_boot_poc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neosoft.spring_boot_poc.model.User;
import com.neosoft.spring_boot_poc.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    UserRepo userRepo;

    @InjectMocks
    UserServiceImpl userService;

    Gson gson;
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

    @Before
    public void setup() throws JsonProcessingException {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        user1 = new User(1,
                "test1",
                "test1",
                "9978607891",
                new Date(1997-1900,11,31),
                new Date(2020-1900,2,15),
                "test1@gmail.com",
                "address 1",
                true,
                380015);
        user2 = new User(2,
                "test2",
                "test2",
                "9978607892",
                new Date(1991-1900,5,29),
                new Date(2020-1900,1,5),
                "test2@gmail.com",
                "address 2",
                true,
                380061);
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
                250061);
        user5 = new User(5,
                "test5",
                "test5",
                "9978607895",
                new Date(1998-1900,8,19),
                new Date(2020-1900,0,7),
                "test5.test@gmail.com",
                "address 5",
                true,
                380061);

        user1JsonString = gson.toJson(user1);
        user2JsonString = gson.toJson(user2);
        user3JsonString = gson.toJson(user3);
        user4JsonString = gson.toJson(user4);
        user5JsonString = gson.toJson(user5);
    }

    @Test
    public void addUser(){

        doReturn(user1).when(userRepo).save(any());

        User expectedResult = user1;

        User actualResult = userService.addUser(user1);

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).save(any());
    }

    @Test
    public void selectAllUsersByPincode(){

        List<User> expectedResult = Arrays.asList(user1,user5);

        doReturn(expectedResult).when(userRepo).findAllByPincodeAndActiveTrue(anyInt());

        List<User> actualResult = userService.selectAllUsersByPincode(380061);

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).findAllByPincodeAndActiveTrue(anyInt());
    }

    @Test
    public void selectAllUsersByFirstName(){

        List<User> expectedResult = Collections.singletonList(user1);

        doReturn(expectedResult).when(userRepo).findAllByFirstNameAndActiveTrue(anyString());

        List<User> actualResult = userService.selectAllUsersByFirstName("test1");

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).findAllByFirstNameAndActiveTrue(anyString());
    }

    @Test
    public void selectAllUsersByLastName(){
        List<User> expectedResult = Collections.singletonList(user1);

        doReturn(expectedResult).when(userRepo).findAllByLastNameAndActiveTrue(anyString());

        List<User> actualResult = userService.selectAllUsersByLastName("test1");

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).findAllByLastNameAndActiveTrue(anyString());
    }

    @Test
    public void selectAllUsersByBirthDate(){
        List<User> expectedResult = Collections.singletonList(user5);

        doReturn(expectedResult).when(userRepo).findAllByDateOfBirthAndActiveTrue(any(Date.class));

        List<User> actualResult = userService.selectAllUsersByBirthDate("1998-07-19");

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).findAllByDateOfBirthAndActiveTrue(any(Date.class));
    }

    @Test
    public void selectAllUsersByJoinDate(){
        List<User> expectedResult = Collections.singletonList(user4);

        doReturn(expectedResult).when(userRepo).findAllByDateOfJoinAndActiveTrue(any(Date.class));

        List<User> actualResult = userService.selectAllUsersByJoinDate("2020-02-03");

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).findAllByDateOfJoinAndActiveTrue(any(Date.class));
    }

    @Test
    public void selectAllActiveUsers(){
        List<User> expectedResult = Arrays.asList(user1,user2,user4,user5);

        doReturn(expectedResult).when(userRepo).findAllByActiveTrue();

        List<User> actualResult = userService.selectAllActiveUsers();

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).findAllByActiveTrue();
    }

    @Test
    public void selectByEmailId(){
        User expectedResult = user1;

        doReturn(expectedResult).when(userRepo).findByEmailIdAndActiveTrue(anyString());

        User actualResult = userService.selectByEmailId("test1.test@gmail.com");

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).findByEmailIdAndActiveTrue(anyString());
    }

    @Test
    public void selectByMobileNumber(){
        User expectedResult = user3;

        doReturn(expectedResult).when(userRepo).findByMobileNumberAndActiveTrue(anyString());

        User actualResult = userService.selectByMobileNumber("9978607893");

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).findByMobileNumberAndActiveTrue(anyString());
    }

    @Test
    public void editUser(){
        User expectedResult = user2;
        expectedResult.setActive(false);

        doReturn(expectedResult).when(userRepo).save(any(User.class));

        User actualResult = userService.editUser(expectedResult);

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).save(any(User.class));
    }

    @Test
    public void selectUser(){
        User expectedResult = user2;

        when(userRepo.findById(anyInt())).thenReturn(Optional.of(user2));

        User actualResult = userService.selectUser(2);

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).findById(anyInt());
    }

    @Test
    public void deleteUser(){
        doNothing().when(userRepo).deleteById(anyInt());
        userService.deleteUser(2);
        verify(userRepo).deleteById(anyInt());
    }
}
