package com.gogolewski.graphdbmeasuretool.services;

import com.gogolewski.graphdbmeasuretool.dataaccess.UserRepository;
import com.gogolewski.graphdbmeasuretool.domain.User;
import com.gogolewski.graphdbmeasuretool.utils.builders.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class InsertService {

    private UserRepository userRepository;

    public InsertService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Insert data into database. If bulkInsert = true data will be saved in 10 parts, otherwise every single record separated
     *
     * @param recordsAmount amount of records to save
     * @param bulkInsert    if the save should be in chunks or every single record separated
     */
    public void insert(int recordsAmount, boolean bulkInsert) {

        if (!bulkInsert) {
            IntStream.range(0, recordsAmount).forEach(record -> {
                userRepository.save(UserBuilder.from("User nr: " + record));
            });
        } else {
            List<User> dataChunk = new ArrayList<>();
            IntStream.range(0, recordsAmount / 10).forEach(chunk -> {
                IntStream.range(0, 10).forEach(record -> {
                    dataChunk.add(UserBuilder.from("User nr: " + chunk + "." + record));
                });
                userRepository.saveAll(dataChunk);
                dataChunk.clear();
            });
        }
    }


    /**
     * Update list of users. If bulkInsert = true data will be saved in one transaction, otherwise every single record separated
     *
     * @param users      list to update
     * @param bulkUpdate if the save should be in one or in separate transactions
     */
    public void update(List<User> users, boolean bulkUpdate) {
        if (!bulkUpdate) {
            users.forEach(user -> userRepository.save(user));
        } else {
            userRepository.saveAll(users);
        }
    }
}
