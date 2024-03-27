package com.nagarro.uber.eats.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModifierGroup {
    String id;
    Title title;
    QuantityInfo quantity_info;
    List<ModifierOption> modifier_options;
}
