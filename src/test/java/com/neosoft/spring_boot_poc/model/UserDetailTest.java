//package com.neosoft.spring_boot_poc.model;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//
//import java.sql.Date;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class UserDetailTest {
//
//    @Mock
//    UserDetail user1;
//
//    @Before
//    public void setUp(){
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
//        UserDetail newUser = new UserDetail(0,null,null,null,null,null,null,null,false,0);
//        UserDetail anotherNewUser = new UserDetail();
//        assertThat(anotherNewUser).isEqualTo(newUser);
//        assertThat(anotherNewUser.hashCode()).isEqualTo(newUser.hashCode());
//    }
//}
