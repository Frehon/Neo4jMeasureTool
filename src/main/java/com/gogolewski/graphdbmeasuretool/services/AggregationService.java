package com.gogolewski.graphdbmeasuretool.services;


import com.gogolewski.graphdbmeasuretool.dataaccess.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AggregationService {

    private UserRepository userRepository;

    public AggregationService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long count() {
        return this.userRepository.count();
    }
}
