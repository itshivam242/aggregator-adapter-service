package com.nagarro.converter;

import com.nagarro.spi.models.Menu;
import com.nagarro.spi.models.MenuConfiguration;
import com.nagarro.uber.eats.models.MenuData;
import com.nagarro.uber.eats.models.Menus;
import com.nagarro.uber.eats.models.Title;

public class SpitoUberEats {
    private static MenuData convertModelType1ToType2(MenuConfiguration modelType1) {
        MenuData modelType2 = new MenuData();

        // Convert menus
        for (Menu menu : modelType1.getMenus()) {
            Menus menus = new Menus();
            //menuData.setId(menuType1.getId());
            menus.setId(menu.getId());

            // Assuming English translation is always available
            //menuData.setTitle(Map.of("translations", Map.of("en", menuType1.getTitle())));
            //menus.setTitle(Map.of("transalations",Map.of("en",menu.getName())));
            menus.setTitle(Title.builder().translations(menu.getName()).build());

            // Assuming service availability remains the same
            //menus.setServiceAvailability(menu.getServiceAvailability());
            //menuData.setCategoryIds(menuType1.getCategoryIds());
            //modelType2.getMenus().add(menuData);
        }

        // Convert categories
       /* for (CategoryType1 categoryType1 : modelType1.getCategories()) {
            Category category = new Category();
            category.setId(categoryType1.getId());
            category.setTitle(Map.of("translations", Map.of("en", categoryType1.getTitle())));
            category.setSubtitle(Map.of("translations", Map.of("en", categoryType1.getSubtitle())));
            // Assuming entities remain the same
            category.setEntities(categoryType1.getEntities());
            modelType2.getCategories().add(category);
        }*/

        // Convert items
        /*for (ItemType1 itemType1 : modelType1.getItems()) {
            Item item = new Item();
            item.setId(itemType1.getId());
            item.setTitle(Map.of("translations", Map.of("en", itemType1.getTitle())));
            item.setDescription(Map.of("translations", Map.of("en", itemType1.getDescription())));
            item.setPriceInfo(itemType1.getPriceInfo());
            item.setTaxInfo(itemType1.getTaxInfo());
            item.setNutritionalInfo(itemType1.getNutritionalInfo());
            item.setDishInfo(itemType1.getDishInfo());
            item.setTaxLabelInfo(itemType1.getTaxLabelInfo());
            item.setProductInfo(itemType1.getProductInfo());
            item.setBundledItems(itemType1.getBundledItems());
            modelType2.getItems().add(item);
        }

        // Convert modifier groups
        for (ModifierGroupType1 modifierGroupType1 : modelType1.getModifierGroups()) {
            ModifierGroup modifierGroup = new ModifierGroup();
            modifierGroup.setId(modifierGroupType1.getId());
            modifierGroup.setTitle(Map.of("translations", Map.of("en", modifierGroupType1.getTitle())));
            modifierGroup.setQuantityInfo(modifierGroupType1.getQuantityInfo());
            modifierGroup.setModifierOptions(modifierGroupType1.getModifierOptions());
            modelType2.getModifierGroups().add(modifierGroup);
        }*/

        return modelType2;
    }
}
