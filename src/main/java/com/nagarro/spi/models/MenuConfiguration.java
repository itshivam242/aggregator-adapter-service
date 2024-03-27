package com.nagarro.spi.models;

import lombok.Data;

import java.util.*;
@Data
public class MenuConfiguration {
    String format;
    Currency currency;
    Locale defaultLocale;
    List<Locale> locales;
    TimeZone timeZone;
    List<StoreHoursPerDisposition> storeHoursPerDisposition;
    Map<String, List<WeeklyHour>> weeklyHours;
    List<StoreHours> storeHours;
    Map<String, ModifierOption> modifierOptions;
    Map<String, ModifierGroup> modifierGroups;
    Map<String, Item> items;
    Map<String, Category> categories;
    List<Menu> menus;
}
