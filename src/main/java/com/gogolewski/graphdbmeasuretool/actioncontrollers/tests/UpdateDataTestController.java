package com.gogolewski.graphdbmeasuretool.actioncontrollers.tests;

import com.gogolewski.graphdbmeasuretool.domain.User;
import com.gogolewski.graphdbmeasuretool.services.AggregationService;
import com.gogolewski.graphdbmeasuretool.services.DeleteService;
import com.gogolewski.graphdbmeasuretool.services.GetService;
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
public class UpdateDataTestController {

    private InsertService insertService;
    private GetService getService;
    private AggregationService aggregationService;
    private DeleteService deleteService;

    public UpdateDataTestController(@Autowired InsertService insertService,
                                    @Autowired GetService getService,
                                    @Autowired AggregationService aggregationService,
                                    @Autowired DeleteService deleteService) {
        this.insertService = insertService;
        this.getService = getService;
        this.aggregationService = aggregationService;
        this.deleteService = deleteService;
    }

    /**
     * Update test request handler
     *
     * @param dataAmount          - amount of data to update
     * @param numberOfRepetitions - number of repetitions
     * @param bulkUpdate          - if true update in chunks, single update otherwise
     * @return result of the test including times for every repetition, average time and message
     */
    @GetMapping(value = "neo4j/updateTest")
    public String updateTestHandler(@RequestParam int dataAmount,
                                    @RequestParam int numberOfRepetitions,
                                    @RequestParam boolean bulkUpdate,
                                    @RequestParam boolean eraseDataAfterEachRepetition) {
        var times = performUpdateTest(dataAmount, numberOfRepetitions, bulkUpdate, eraseDataAfterEachRepetition);
        return Result.from(times, (long) TimeMeasurementService.averageTime(times), "Update tests results").toString();
    }

    /**
     * Performing speed test updating data
     *
     * @param dataAmount          - amount of data to update
     * @param numberOfRepetitions - number of repetitions
     * @param bulkUpdate          - if true update in chunks, single update otherwise
     * @return result of the test including times for every repetition, average time and message
     */
    private List performUpdateTest(int dataAmount, int numberOfRepetitions, boolean bulkUpdate, boolean eraseDataAfterEachRepetition) {
        var times = new ArrayList();
        IntStream.range(0, numberOfRepetitions).forEach(repetition -> {
            var existingUsersAmount = aggregationService.count();
            if (existingUsersAmount < dataAmount) {
                this.insertService.insert(dataAmount, true);
            }
            List<User> usersToUpdate = this.getService.getUsers(dataAmount);
            usersToUpdate.forEach(user -> {
                user.setName(user.getName() + " updated");
            });
            var startTime = System.nanoTime();
            insertService.update(usersToUpdate, bulkUpdate);
            times.add(TimeMeasurementService.measureTime(startTime));
            this.deleteService.eraseData(eraseDataAfterEachRepetition);

        });
        deleteService.eraseData(true);
        return times;
    }
}
