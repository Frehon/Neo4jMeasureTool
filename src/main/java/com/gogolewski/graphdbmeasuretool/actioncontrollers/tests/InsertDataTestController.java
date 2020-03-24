package com.gogolewski.graphdbmeasuretool.actioncontrollers.tests;

import com.gogolewski.graphdbmeasuretool.services.DeleteService;
import com.gogolewski.graphdbmeasuretool.services.InsertService;
import com.gogolewski.graphdbmeasuretool.utils.Result;
import com.gogolewski.graphdbmeasuretool.utils.TimeMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
public class InsertDataTestController {

    private InsertService insertService;
    private DeleteService deleteService;

    public InsertDataTestController(@Autowired InsertService insertService,
                                    @Autowired DeleteService deleteService) {
        this.insertService = insertService;
        this.deleteService = deleteService;
    }

    /**
     * Insert test request handler
     *
     * @param dataAmount                   - amount of data to insert
     * @param numberOfRepetitions          - number of repetitions
     * @param eraseDataAfterEachRepetition - if true erase all data from the table after each test unit
     * @param bulkInsert                   - if true insert in chunks, single save otherwise
     * @return result of the test including times for every repetition, average time and message
     */
    @GetMapping(value = "neo4j/insertTest")
    public String insertTestHandler(@RequestParam int dataAmount,
                                    @RequestParam int numberOfRepetitions,
                                    @RequestParam boolean eraseDataAfterEachRepetition,
                                    @RequestParam boolean bulkInsert) {
        var times = performInsertTest(dataAmount, numberOfRepetitions, eraseDataAfterEachRepetition, bulkInsert);
        return Result.from(times, (long) TimeMeasurementService.averageTime(times), "Insert tests results").toString();
    }

    /**
     * Performing speed test saving data into database
     *
     * @param dataAmount             - amount of data to insert
     * @param numberOfRepetitions    - number of repetitions
     * @param eraseDataAfterEachTest - if true erase all data from the table after each test unit
     * @param bulkInsert             - if true insert in chunks, single save otherwise
     * @return result of the test including times for every repetition, average time and message
     */
    private List performInsertTest(int dataAmount, int numberOfRepetitions, boolean eraseDataAfterEachTest, boolean bulkInsert) {

        var times = new ArrayList();
        IntStream.range(0, numberOfRepetitions).forEach(repetition -> {
            var startTime = System.nanoTime();
            insertService.insert(dataAmount, bulkInsert);
            times.add(TimeMeasurementService.measureTime(startTime));
            deleteService.eraseData(eraseDataAfterEachTest);
        });
        deleteService.eraseData(true);
        return times;
    }

}
