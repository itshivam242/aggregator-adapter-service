package com.nagarro.spi.models;

import lombok.Data;

@Data
public class CalorieInfo {
    private String calorieUnit;
    private int lowerRange;
    private int higherRange;
}
