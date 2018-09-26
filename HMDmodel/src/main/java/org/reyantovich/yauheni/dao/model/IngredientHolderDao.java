package org.reyantovich.yauheni.dao.model;

import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.model.pojo.IngredientHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public interface IngredientHolderDao {

    List<IngredientHolder> getIngredientHolders(List<HmdObjects> ingredientHolderInDB, Locale locale);

}
