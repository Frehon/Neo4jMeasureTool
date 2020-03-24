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
public class DeleteDataTestController {

    private InsertService insertService;
    private DeleteService deleteService;

    public DeleteDataTestController(@Autowired InsertService insertService,
                                    @Autowired DeleteService deleteService) {
        this.insertService = insertService;
        this.deleteService = deleteService;
    }

    /**
     * Delete test request handler
     *
     * @param dataAmount          amount of data to delete
     * @param numberOfRepetitions number of repetitions
     * @return result of the test including times for every repetition, average time and message
     */
    @GetMapping(value = "neo4j/deleteTest")
    public String deleteTestHandler(@RequestParam int dataAmount,
                                    @RequestParam int numberOfRepetitions) {
        var times = performDeleteTest(dataAmount, numberOfRepetitions);
        return Result.from(times, (long) TimeMeasurementService.averageTime(times), "Delete tests results").toString();
    }


    /**
     * Performing speed test deleting data from database
     *
     * @param dataAmount          amount of data to delete
     * @param numberOfRepetitions number of repetitions
     * @return result of the test including times for every repetition, average time and message
     */
    private List performDeleteTest(int dataAmount, int numberOfRepetitions) {
        var times = new ArrayList();
        IntStream.range(0, numberOfRepetitions).forEach(repetition -> {
            this.insertService.insert(dataAmount, true);
            var startTime = System.nanoTime();
            deleteService.eraseData(true);
            times.add(TimeMeasurementService.measureTime(startTime));
        });
        return times;
    }
}
