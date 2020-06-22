//package com.neosoft.spring_boot_poc.model;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//
//import java.sql.Date;
//import java.util.Arrays;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class UserDetailTest {
//
//    @Mock
//    User user1;
//
//    @Before
//    public void setUp(){
//        user1 = new User(1,
//                "john",
//                "P@ssw0rd",
//                true,
//                Date.valueOf("2020-01-07"),
//                Date.valueOf("2020-01-07"),
//                new UserDetail(1,
//                        null,
//                        "john",
//                        "cena",
//                        "9978607891",
//                        Date.valueOf("1998-09-19"),
//                        "john@gmail.com",
//                        "test address",
//                        380061),
//                new UserEducationDetail(1,
//                        null,
//                        86.32f,
//                        81.83f,
//                        "gseb",
//                        "gsheb",
//                        8.96f,
//                        "GTU"),
//                new UserEmploymentDetail(1,
//                        null,
//                        25000,
//                        "john@gmail.com",
//                        "9999999999",
//                        "Java",
//                        Date.valueOf("2020-01-07"),
//                        Byte.valueOf("3")),
//                Arrays.asList(new UserProjectDetail(1,
//                                null,
//                                "goods",
//                                "goods and services",
//                                "DHL",
//                                true,
//                                Date.valueOf("2020-03-15"),
//                                null),
//                        new UserProjectDetail(2,
//                                null,
//                                "goods",
//                                "goods and services",
//                                "DHL",
//                                true,
//                                Date.valueOf("2020-03-15"),
//                                null)),
//                new UserRole(1, "Developer", null));
//        user1.getUserEmploymentDetail().setUser(user1);
//        user1.getUserEducationDetail().setUser(user1);
//        user1.getUserRole().setUser(user1);
//        user1.getUserDetail().setUser(user1);
//        user1.getUserProjectDetail().forEach(userProjectDetail -> userProjectDetail.setUser(user1));
//    }
//
//    @Test
//    public void setMethods(){
//        UserDetail expectedResult = new UserDetail(3,
//                "newfn",
//                "newln",
//                "9999999999",
//                Date.valueOf("1990-02-01"),
//                Date.valueOf("2020-04-03"),
//                "newemail@gmail.com",
//                "new address",
//                false,
//                123456);
//
//        user1.setActive(false);
//        user1.setId(3);
//        user1.setAddress("new address");
//        user1.setDateOfBirth(Date.valueOf("1990-02-01"));
//        user1.setDateOfJoin(Date.valueOf("2020-04-03"));
//        user1.setEmailId("newemail@gmail.com");
//        user1.setFirstName("newfn");
//        user1.setLastName("newln");
//        user1.setMobileNumber("9999999999");
//        user1.setPincode(123456);
//
//       assertThat(expectedResult).isEqualTo(user1);
//    }
//
//    @Test
//    public void toStringTest(){
//        String str = "User(id=1, firstName=test1, lastName=test1, mobileNumber=9978607891, dateOfBirth=1997-12-31, dateOfJoin=2020-03-15, emailId=test1@gmail.com, address=address 1, active=true, pincode=380015)";
//        assertThat(str).isEqualTo(user1.toString());
//    }
//
//    @Test
//    public void noArgConstructorTest(){
//        User newUser = new User();
//        User anotherNewUser = new User();
//        assertThat(anotherNewUser).isEqualTo(newUser);
//        assertThat(anotherNewUser.hashCode()).isEqualTo(newUser.hashCode());
//    }
//}
