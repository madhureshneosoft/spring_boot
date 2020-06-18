//package com.neosoft.spring_boot_poc.service;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.neosoft.spring_boot_poc.model.UserDetail;
//import com.neosoft.spring_boot_poc.repo.UserRepo;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.Date;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//@RunWith(SpringRunner.class)
//public class UserDetailServiceTest {
//
//    @Mock
//    UserRepo userRepo;
//
//    @InjectMocks
//    UserServiceImpl userService;
//
//    Gson gson;
//    UserDetail user1;
//    UserDetail user2;
//    UserDetail user3;
//    UserDetail user4;
//    UserDetail user5;
//    String user1JsonString;
//    String user2JsonString;
//    String user3JsonString;
//    String user4JsonString;
//    String user5JsonString;
//
//    @Before
//    public void setup() {
//        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//        user1 = new UserDetail(1,
//                "test1",
//                "test1",
//                "9978607891",
//                Date.valueOf("1997-12-31"),
//                Date.valueOf("2020-03-15"),
//                "test1@gmail.com",
//                "address 1",
//                true,
//                380015);
//        user2 = new UserDetail(2,
//                "test2",
//                "test2",
//                "9978607892",
//                Date.valueOf("1991-06-29"),
//                Date.valueOf("2020-02-05"),
//                "test2@gmail.com",
//                "address 2",
//                true,
//                380061);
//        user3 = new UserDetail(3,
//                "test3",
//                "test3",
//                "9978607893",
//                Date.valueOf("1993-03-02"),
//                Date.valueOf("2020-06-19"),
//                "test3@gmail.com",
//                "address 3",
//                false,
//                280062);
//        user4 = new UserDetail(4,
//                "test4",
//                "test4",
//                "9978607894",
//                Date.valueOf("1999-07-11"),
//                Date.valueOf("2020-04-03"),
//                "test4@gmail.com",
//                "address 4",
//                true,
//                250061);
//        user5 = new UserDetail(5,
//                "test5",
//                "test5",
//                "9978607895",
//                Date.valueOf("1998-09-19"),
//                Date.valueOf("2020-01-07"),
//                "test5.test@gmail.com",
//                "address 5",
//                true,
//                380061);
//
//        user1JsonString = gson.toJson(user1);
//        user2JsonString = gson.toJson(user2);
//        user3JsonString = gson.toJson(user3);
//        user4JsonString = gson.toJson(user4);
//        user5JsonString = gson.toJson(user5);
//    }
//
//    @Test
//    public void addUser(){
//
//        doReturn(user1).when(userRepo).save(any());
//
//        UserDetail expectedResult = user1;
//
//        UserDetail actualResult = userService.addUser(user1);
//
//        assertThat(expectedResult).isEqualTo(actualResult);
//
//        verify(userRepo).save(any());
//    }
//
//    @Test
//    public void selectAllUsersByPincode(){
//
//        List<UserDetail> expectedResult = Arrays.asList(user1,user5);
//
//        doReturn(expectedResult).when(userRepo).findAllByPincodeAndActiveTrue(anyInt());
//
//        List<UserDetail> actualResult = userService.selectAllUsersByPincode(380061);
//
//        assertThat(expectedResult).isEqualTo(actualResult);
//
//        verify(userRepo).findAllByPincodeAndActiveTrue(anyInt());
//    }
//
//    @Test
//    public void selectAllUsersByFirstName(){
//
//        List<UserDetail> expectedResult = Collections.singletonList(user1);
//
//        doReturn(expectedResult).when(userRepo).findAllByFirstNameAndActiveTrue(anyString());
//
//        List<UserDetail> actualResult = userService.selectAllUsersByFirstName("test1");
//
//        assertThat(expectedResult).isEqualTo(actualResult);
//
//        verify(userRepo).findAllByFirstNameAndActiveTrue(anyString());
//    }
//
//    @Test
//    public void selectAllUsersByLastName(){
//        List<UserDetail> expectedResult = Collections.singletonList(user1);
//
//        doReturn(expectedResult).when(userRepo).findAllByLastNameAndActiveTrue(anyString());
//
//        List<UserDetail> actualResult = userService.selectAllUsersByLastName("test1");
//
//        assertThat(expectedResult).isEqualTo(actualResult);
//
//        verify(userRepo).findAllByLastNameAndActiveTrue(anyString());
//    }
//
//    @Test
//    public void selectAllUsersByBirthDate(){
//        List<UserDetail> expectedResult = Collections.singletonList(user5);
//
//        doReturn(expectedResult).when(userRepo).findAllByDateOfBirthAndActiveTrue(any(Date.class));
//
//        List<UserDetail> actualResult = userService.selectAllUsersByBirthDate("1998-07-19");
//
//        assertThat(expectedResult).isEqualTo(actualResult);
//
//        verify(userRepo).findAllByDateOfBirthAndActiveTrue(any(Date.class));
//    }
//
//    @Test
//    public void selectAllUsersByJoinDate(){
//        List<UserDetail> expectedResult = Collections.singletonList(user4);
//
//        doReturn(expectedResult).when(userRepo).findAllByDateOfJoinAndActiveTrue(any(Date.class));
//
//        List<UserDetail> actualResult = userService.selectAllUsersByJoinDate("2020-02-03");
//
//        assertThat(expectedResult).isEqualTo(actualResult);
//
//        verify(userRepo).findAllByDateOfJoinAndActiveTrue(any(Date.class));
//    }
//
//    @Test
//    public void selectAllActiveUsers(){
//        List<UserDetail> expectedResult = Arrays.asList(user1,user2,user4,user5);
//
//        doReturn(expectedResult).when(userRepo).findAllByActiveTrue();
//
//        List<UserDetail> actualResult = userService.selectAllActiveUsers();
//
//        assertThat(expectedResult).isEqualTo(actualResult);
//
//        verify(userRepo).findAllByActiveTrue();
//    }
//
//    @Test
//    public void selectAllInActiveUsers(){
//        List<UserDetail> expectedResult = Collections.singletonList(user3);
//
//        doReturn(expectedResult).when(userRepo).findAllByActiveFalse();
//
//        List<UserDetail> actualResult = userService.selectAllInactiveUsers();
//
//        assertThat(expectedResult).isEqualTo(actualResult);
//
//        verify(userRepo).findAllByActiveFalse();
//    }
//
//    @Test
//    public void selectByEmailId(){
//        UserDetail expectedResult = user1;
//
//        doReturn(expectedResult).when(userRepo).findByEmailIdAndActiveTrue(anyString());
//
//        UserDetail actualResult = userService.selectByEmailId("test1.test@gmail.com");
//
//        assertThat(expectedResult).isEqualTo(actualResult);
//
//        verify(userRepo).findByEmailIdAndActiveTrue(anyString());
//    }
//
//    @Test
//    public void selectByMobileNumber(){
//        UserDetail expectedResult = user3;
//
//        doReturn(expectedResult).when(userRepo).findByMobileNumberAndActiveTrue(anyString());
//
//        UserDetail actualResult = userService.selectByMobileNumber("9978607893");
//
//        assertThat(expectedResult).isEqualTo(actualResult);
//
//        verify(userRepo).findByMobileNumberAndActiveTrue(anyString());
//    }
//
//    @Test
//    public void editUser(){
//        UserDetail expectedResult = user2;
//        expectedResult.setActive(false);
//
//        doReturn(expectedResult).when(userRepo).save(any(UserDetail.class));
//
//        UserDetail actualResult = userService.editUser(expectedResult);
//
//        assertThat(expectedResult).isEqualTo(actualResult);
//
//        verify(userRepo).save(any(UserDetail.class));
//    }
//
//    @Test
//    public void selectUser(){
//        UserDetail expectedResult = user2;
//
//        when(userRepo.findById(anyInt())).thenReturn(Optional.of(user2));
//
//        UserDetail actualResult = userService.selectUser(2);
//
//        assertThat(expectedResult).isEqualTo(actualResult);
//
//        verify(userRepo).findById(anyInt());
//    }
//
//    @Test
//    public void deleteUser(){
//        doNothing().when(userRepo).deleteById(anyInt());
//        userService.deleteUser(2);
//        verify(userRepo).deleteById(anyInt());
//    }
//}
