package com.nagarro.uber.eats.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    String id;
    Title title;
    Description description;
    PriceInfo price_info;
    TaxInfo tax_info;
    ModifierGroupIds modifier_group_ids;
    NutritionalInfo nutritional_info;
    DishInfo dish_info;
    TaxLabelInfo tax_label_info;
    ProductInfo product_info;
    List<BundledItem> bundled_items;
    VisibilityInfo visibility_info;
}
