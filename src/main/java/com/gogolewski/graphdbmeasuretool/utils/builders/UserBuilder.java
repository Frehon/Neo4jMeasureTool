package com.gogolewski.graphdbmeasuretool.utils.builders;

import com.gogolewski.graphdbmeasuretool.domain.User;
import org.springframework.stereotype.Service;

public class UserBuilder {

    public static User createUser(String userName){
        User user = new User();
        user.setName(userName);
        return user;
    }

}
