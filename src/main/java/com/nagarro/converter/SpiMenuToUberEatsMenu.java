package com.nagarro.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.spi.models.MenuConfiguration;
import com.nagarro.uber.eats.models.*;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.util.*;

@Dependent
public class SpiMenuToUberEatsMenu {
    @Inject
    ObjectMapper objectMapper;

    public MenuData spiMenuItemToUberEatsMenuItemConverter(MenuConfiguration menu){
        MenuData menuData=new MenuData();
        System.out.println("conversion start");

        List<Menus> menusList =new ArrayList<>();
        Menus menus =new Menus();
        menus.setId(menu.getMenus().get(0).getId());
        menus.setTitle(Title.builder().translations(menu.getMenus().get(0).getName()).build());
        List<ServiceAvailability> serviceAvailabilities=new ArrayList<>();

        menu.getWeeklyHours().forEach((s, weeklyHours) ->{
            weeklyHours.forEach(weeklyHour -> {
                ServiceAvailability serviceAvailability=new ServiceAvailability();
                serviceAvailability.setDay_of_week(weeklyHour.getDayOfWeek().toString().toLowerCase());
                List<TimePeriod> timePeriods= new ArrayList<>();
                TimePeriod timePeriod=new TimePeriod(weeklyHour.getStartTime(),weeklyHour.getEndTime());
                timePeriods.add(timePeriod);
                serviceAvailability.setTime_periods(timePeriods);
                serviceAvailabilities.add(serviceAvailability);
                menus.setService_availability(serviceAvailabilities);
            });
        });

        menu.getStoreHours().forEach(storeHours -> {
            ServiceAvailability serviceAvailability1=new ServiceAvailability();
            serviceAvailability1.setDay_of_week(storeHours.getDayOfWeek().toString().toLowerCase());
            List<TimePeriod> timePeriods=new ArrayList<>();
            TimePeriod timePeriod=new TimePeriod(storeHours.getTimePeriod().getStartTime(),storeHours.getTimePeriod().getEndTime());
            timePeriods.add(timePeriod);
            serviceAvailability1.setTime_periods(timePeriods);
            menus.getService_availability().add(serviceAvailability1);
        });
        menu.getMenus().forEach(menu1 -> {
            menus.setCategory_ids(menu1.getCategoryIds());
        });
        menusList.add(menus);
        menuData.setMenus(menusList);



        List<Category> categories=new ArrayList<>();
        menu.getCategories().forEach((s, category1) -> {
            Category category=new Category();
            category.setId(s);
            Map<String, String> translations = new HashMap<>();
            category1.getName().forEach((locale, s1) -> {
                translations.put(locale.toString(), s1);
            });
            category.setTitle(new Title(translations));


            List<Entity> entities=new ArrayList<>();
            category1.getItemIds().forEach(s1 -> {
                entities.add(new Entity(s1));});
            category.setEntities(entities);
            categories.add(category);
        });
        menuData.setCategories(categories);


        List<Item> items=new ArrayList<>();
        menu.getItems().forEach((s, item1)  -> {
            Item item =new Item();
            item.setId(s);

            Map<String, String> nameTranslations = new HashMap<>();
            item1.getName().forEach((locale, s1) -> {
                nameTranslations.put(locale.toString(), s1);
            });
            item.setTitle(new Title(nameTranslations));

            Map<String, String> descriptionTranslations = new HashMap<>();
            item1.getDescription().forEach((locale, s1) -> {
                descriptionTranslations.put(locale.toString(), s1);
            });
            item.setDescription(new Description(descriptionTranslations));

            item.setPrice_info(PriceInfo.builder().price((long) item1.getPrice()).build());
            item.setTax_info(TaxInfo.builder().taxRate(item1.getTaxRate()).build());
            item.setModifier_group_ids(ModifierGroupIds.builder().ids(item1.getModifierGroups()).build());

            items.add(item);
        });
        menuData.setItems(items);


        List<ModifierGroup> modifierGroups=new ArrayList<>();
        menu.getModifierGroups().forEach((s, modifierGroup1) -> {
            ModifierGroup modifierGroup=new ModifierGroup();
            modifierGroup.setId(s);

            Map<String, String> nameTranslations = new HashMap<>();
            modifierGroup1.getName().forEach((locale, s1) -> {
                nameTranslations.put(locale.toString(), s1);
            });
            modifierGroup.setTitle(new Title(nameTranslations));

            List<ModifierOption> modifierOptions=new ArrayList<>();
            ModifierOption modifierOption=new ModifierOption();
            modifierGroup1.getModifierOptions().forEach(s1 -> {
                modifierOptions.add(new ModifierOption().builder().id(s1).type("ITEM").build());
            });
            modifierGroup.setModifier_options(modifierOptions);
            modifierGroups.add(modifierGroup);
        });
        menuData.setModifier_groups(modifierGroups);


        //System.out.println(menuData);
        return menuData;
    }
}
