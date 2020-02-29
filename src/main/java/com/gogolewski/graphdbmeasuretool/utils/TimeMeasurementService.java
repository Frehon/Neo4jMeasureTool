package com.gogolewski.graphdbmeasuretool.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeMeasurementService {

    public static Long measureTime(long startTime) {
        Long elapsedTime = System.nanoTime() - startTime;
        return TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
    }

    public static double averageTime(List<Long> times) {
        double averageTime = 0;
        if (times.size() > 0) {
            averageTime = times.stream()
                    .mapToDouble(time -> time.doubleValue())
                    .average().getAsDouble();
        }
        return averageTime;
    }
}
