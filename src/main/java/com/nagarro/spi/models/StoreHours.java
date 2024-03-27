package com.nagarro.spi.models;

import lombok.Data;

import java.time.DayOfWeek;
@Data
public class StoreHours {
    DayOfWeek dayOfWeek;
    TimePeriod timePeriod;
}
