package com.gogolewski.graphdbmeasuretool.utils;

import java.util.List;

public class Result {

    private List times;
    private Long averageTime;
    private String message;

    public static Result from(List times, long averageTime, String message) {
        var result = new Result();
        result.setTimes(times);
        result.setAverageTime(averageTime);
        result.setMessage(message);
        return result;
    }

    public List getTimes() {
        return times;
    }

    public void setTimes(List times) {
        this.times = times;
    }

    public Long getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(Long averageTime) {
        this.averageTime = averageTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.getMessage() + ". Repetition times (seconds):  " + this.getTimes() + ". Average time (seconds): " + this.getAverageTime();
    }
}
