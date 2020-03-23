package com.gogolewski.graphdbmeasuretool.actioncontrollers.helper;

import com.gogolewski.graphdbmeasuretool.dataaccess.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDataManagementController {

    private UserRepository userRepository;

    public UserDataManagementController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "neo4j/getUsers")
    public Iterable getUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "neo4j/deleteUsers")
    public void deleteUsers() {
        userRepository.deleteAll();
    }

}
