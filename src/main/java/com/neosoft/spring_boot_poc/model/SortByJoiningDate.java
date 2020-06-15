package com.neosoft.spring_boot_poc.model;

import java.util.Comparator;

public class SortByJoiningDate implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getDateOfJoin().compareTo(o2.getDateOfJoin());
    }
}
