package com.neosoft.spring_boot_poc.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

import java.sql.Date;

public class UserTest {

    @Mock
    User user1;

    @Before
    public void setUp(){
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
    }

    @Test
    public void setMethods(){
        User expectedResult = new User(3,
                "newfn",
                "newln",
                "9999999999",
                new Date(1990-1900,1,1),
                new Date(2020-1900,3,3),
                "newemail@gmail.com",
                "new address",
                false,
                123456);

        user1.setActive(false);
        user1.setId(3);
        user1.setAddress("new address");
        user1.setDateOfBirth(new Date(1990-1900,1,1));
        user1.setDateOfJoin(new Date(2020-1900,3,3));
        user1.setEmailId("newemail@gmail.com");
        user1.setFirstName("newfn");
        user1.setLastName("newln");
        user1.setMobileNumber("9999999999");
        user1.setPincode(123456);

       assertThat(expectedResult).isEqualTo(user1);
    }

    @Test
    public void toStringTest(){
        String str = "User(id=1, firstName=test1, lastName=test1, mobileNumber=9978607891, dateOfBirth=1997-12-31, dateOfJoin=2020-03-15, emailId=test1@gmail.com, address=address 1, active=true, pincode=380015)";
        assertThat(str).isEqualTo(user1.toString());
    }

    @Test
    public void noArgConstructorTest(){
        User newUser = new User(0,null,null,null,null,null,null,null,false,0);
        User anotherNewUser = new User();
        assertThat(anotherNewUser).isEqualTo(newUser);
        assertThat(anotherNewUser.hashCode()).isEqualTo(newUser.hashCode());
    }
}
