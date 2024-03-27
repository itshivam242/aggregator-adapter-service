package com.nagarro.uber.eats.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Description {
    Map<String,String> translations;
}
