package com.neosoft.spring_boot_poc.model;

import java.util.Comparator;

public class SortByBirthDate implements Comparator<User> {

    @Override
    public int compare(User user1, User user2) {
        return user1.getDateOfBirth().compareTo(user2.getDateOfBirth());
    }
}
