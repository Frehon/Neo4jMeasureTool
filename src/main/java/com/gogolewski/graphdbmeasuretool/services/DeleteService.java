package com.gogolewski.graphdbmeasuretool.services;

import com.gogolewski.graphdbmeasuretool.dataaccess.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {

    private UserRepository userRepository;

    public DeleteService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Removes all data from the user table if eraseDataAfterEachTest flag set
     *
     * @param erase if data should be deleted or not
     */
    public void eraseData(boolean erase) {
        if (erase) {
            userRepository.deleteAll();
        }
    }
}
