package com.neosoft.spring_boot_poc.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

public class SortByBirthDateAndJoinDateTest {

    User user1;
    User user2;

    @Before
    public void setUp(){
        user1 = new User(1,
                "test1",
                "test1",
                "9978607891",
//                new Date(1997-1900,11,31),
//                new Date(2020-1900,2,15),
                Date.valueOf("1997-12-31"),
                Date.valueOf("2020-03-15"),
                "test1@gmail.com",
                "address 1",
                true,
                380061);
        user2 = new User(2,
                "test2",
                "test2",
                "9978607892",
//                new Date(1991-1900,5,29),
//                new Date(2020-1900,7,5),
                Date.valueOf("1991-06-29"),
                Date.valueOf("2020-08-05"),
                "test2@gmail.com",
                "address 2",
                true,
                380063);
    }

    @Test
    public void sortByBirthDate(){
        Assert.assertTrue(user1.getDateOfBirth().compareTo(user2.getDateOfBirth())>0);
    }

    @Test
    public void sortByJoinDate(){
        Assert.assertTrue(user1.getDateOfJoin().compareTo(user2.getDateOfJoin())<0);
    }
}
