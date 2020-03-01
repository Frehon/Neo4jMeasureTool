package com.gogolewski.graphdbmeasuretool.utils.builders;

import com.gogolewski.graphdbmeasuretool.domain.User;

public class UserBuilder {

    public static User from(String userName) {
        User user = new User();
        user.setName(userName);
        return user;
    }

}
