package com.neosoft.spring_boot_poc.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neosoft.spring_boot_poc.model.*;
import com.neosoft.spring_boot_poc.repo.UserRepo;
import com.neosoft.spring_boot_poc.repo.UserRepoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserDetailServiceTest {

    @Mock
    UserRepo userRepo;

    @Mock
    UserRepoImpl userRepoImpl;

    @InjectMocks
    UserServiceImpl userService;

    Gson gson;
    User user1;
    User user2;
    User user3;
    User user4;
    User user5;

    void setUser(User user) {
        user.getUserEmploymentDetail().setUser(user);
        user.getUserEducationDetail().setUser(user);
        user.getUserRole().setUser(user);
        user.getUserDetail().setUser(user);
        user.getUserProjectDetail().forEach(userProjectDetail -> userProjectDetail.setUser(user));
    }

    @Before
    public void setup() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
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
    }

    @Test
    public void addUser(){

        doReturn(user1).when(userRepo).save(user1);

        User expectedResult = user1;

        User actualResult = userService.addUser(user1);

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).save(any());
    }

    @Test(expected = InputMismatchException.class)
    public void addUserWrong(){

        user1.getUserDetail().setDateOfBirth(Date.valueOf("2020-06-22"));

        userService.addUser(user1);
    }

    @Test
    public void dynamicSearch(){

        List<User> expectedResult = Collections.singletonList(user1);

        doReturn(expectedResult).when(userRepoImpl).dynamicSearch(anyString());

        List<User> actualResult = userService.dynamicSearch("userDetail.pincode=380061&userName=john");

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepoImpl).dynamicSearch(anyString());
    }

    @Test
    public void dynamicSort(){

        List<User> expectedResult = Arrays.asList(user1,user2,user4,user5);

        doReturn(expectedResult).when(userRepo).findAll(any(Sort.class));

        List<User> actualResult = userService.selectAllUserSortBy("userEmploymentDetail.dateOfJoin");

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).findAll(any(Sort.class));
    }
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
    @Test
    public void selectAllActiveUsers(){
        List<User> expectedResult = Arrays.asList(user1,user2,user4,user5);

        doReturn(expectedResult).when(userRepo).findAllByActiveTrue();

        List<User> actualResult = userService.selectAllActiveUsers();

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).findAllByActiveTrue();
    }

    @Test
    public void selectAllInActiveUsers(){
        List<User> expectedResult = Collections.singletonList(user3);

        doReturn(expectedResult).when(userRepo).findAllByActiveFalse();

        List<User> actualResult = userService.selectAllInactiveUsers();

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).findAllByActiveFalse();
    }
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
    @Test
    public void editUser(){
        User expectedResult = user2;
        expectedResult.setActive(false);

        doReturn(expectedResult).when(userRepo).save(any(User.class));
        when(userRepo.findById(anyInt())).thenReturn(Optional.of(user2));

        User actualResult = userService.editUser(expectedResult,2);

        assertThat(expectedResult).isEqualTo(actualResult);

        verify(userRepo).save(any());
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
        when(userRepo.findById(anyInt())).thenReturn(Optional.of(user2));
        userService.deleteUser(2);
        verify(userRepo).delete(any());
    }
}
