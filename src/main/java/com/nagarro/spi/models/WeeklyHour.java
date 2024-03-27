package com.nagarro.spi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeeklyHour {
    public DayOfWeek dayOfWeek;
    public String startTime;
    public String endTime;
}