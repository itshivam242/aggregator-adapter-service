package com.nagarro.uber.eats.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceAvailability {
    String day_of_week;
    List<TimePeriod> time_periods;
}
