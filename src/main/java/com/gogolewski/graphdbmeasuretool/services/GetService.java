package com.gogolewski.graphdbmeasuretool.services;

import com.gogolewski.graphdbmeasuretool.dataaccess.UserRepository;
import com.gogolewski.graphdbmeasuretool.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetService {

    private UserRepository userRepository;

    public GetService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(long usersAmount) {
        return this.userRepository.findAllWithLimit(usersAmount);
    }
}


/*
@Service
public class GetService {

    private UserRepository userRepository;

    public GetService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(long usersAmount) {
        var ids = new ArrayList<Long>();
        for(long i = 0 ; i < usersAmount; i++){
            ids.add(i);
        }
        Collections.shuffle(ids);

        var users = this.userRepository.findAllById(ids);
        return (List<User>) users;
    }
}*/
