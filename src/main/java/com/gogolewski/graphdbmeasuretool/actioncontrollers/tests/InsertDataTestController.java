package com.gogolewski.graphdbmeasuretool.actioncontrollers.tests;

import com.gogolewski.graphdbmeasuretool.dataaccess.UserRepository;
import com.gogolewski.graphdbmeasuretool.domain.User;
import com.gogolewski.graphdbmeasuretool.utils.TimeMeasurementService;
import com.gogolewski.graphdbmeasuretool.utils.builders.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
public class InsertDataTestController {

    public InsertDataTestController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    /**
     * Insert test request handler
     * @param dataAmount - amount of data to insert
     * @param numberOfRepetitions - number of repetitions
     * @param eraseDataAfterEachTest - if true erase all data from the table after each test unit
     * @param bulkInsert - if true insert in chunks, single save otherwise
     * @return Test finished if test runs till the end, error stack trace otherwise
     */
    @GetMapping(value = "neo4j/insertTest")
    public String insertTestHandler(@RequestParam("dataAmount") int dataAmount,
                                    @RequestParam("numberOfRepetitions") int numberOfRepetitions,
                                    @RequestParam("eraseDataAfterEachTest") boolean eraseDataAfterEachTest,
                                    @RequestParam("bulkInsert") boolean bulkInsert) {
        performInsertTest(dataAmount, numberOfRepetitions, eraseDataAfterEachTest, bulkInsert);
        return "Test finished";
    }

    /**
     * Performing speed test saving data into database
     * @param recordsAmount
     * @param numberOfRepetitions
     * @param eraseDataAfterEachTest
     * @param bulkInsert
     */
    private void performInsertTest(int recordsAmount, int numberOfRepetitions, boolean eraseDataAfterEachTest, boolean bulkInsert) {

        List times = new ArrayList();
        IntStream.range(0, numberOfRepetitions).forEach(repetition -> {
            Long startTime = System.nanoTime();
            insert(recordsAmount, bulkInsert);
            times.add(TimeMeasurementService.measureTime(startTime));
            eraseData(eraseDataAfterEachTest);
        });
        System.out.println("Time by repetition: " + times);
        System.out.println(TimeMeasurementService.averageTime(times) + " sec");
    }

    /**
     * Insert data into database. If bulkInsert = true data will be saved in 10 parts, otherwise every single record separated
     * @param recordsAmount amount of records to save
     * @param bulkInsert if the save should be in chunks or every single record separated
     */
    private void insert(int recordsAmount, boolean bulkInsert) {

        if(!bulkInsert){
            IntStream.range(0, recordsAmount).forEach(record -> {
                userRepository.save(UserBuilder.createUser("User nr: " + record));
            });
        }
        else {
            List<User> dataChunk = new ArrayList<>();
            IntStream.range(0, recordsAmount/10).forEach(chunk -> {
                IntStream.range(0, 10).forEach(record -> {
                    dataChunk.add(UserBuilder.createUser("User nr: " + chunk + "." + record));
                });
                userRepository.saveAll(dataChunk);
                dataChunk.clear();
            });
        }
    }

    /**
     * Removes all data from the user table if eraseDataAfterEachTest flag set
     * @param eraseDataAfterEachTest
     */
    private void eraseData(boolean eraseDataAfterEachTest) {
        if (eraseDataAfterEachTest) {
            userRepository.deleteAll();
        }
    }

}
