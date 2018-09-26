package org.reyantovich.yauheni.dao.model.impl;

import org.reyantovich.yauheni.attributesIds.IngredientHolderAttributes;
import org.reyantovich.yauheni.dao.model.IngredientDao;
import org.reyantovich.yauheni.dao.model.IngredientHolderDao;
import org.reyantovich.yauheni.hmdbase.*;
import org.reyantovich.yauheni.model.pojo.Ingredient;
import org.reyantovich.yauheni.model.pojo.IngredientHolder;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class IngredientHolderDaoImpl implements IngredientHolderDao {

    private SessionHolder sessionHolder;

    private IngredientDao ingredientDao;

    @Override
    public List<IngredientHolder> getIngredientHolders(List<HmdObjects> ingredientHolderInDB, Locale locale) {
        List<IngredientHolder> ingredientHolders = new ArrayList<>();

        IngredientHolder ingredientHolder;
        Ingredient ingredient;
        for(HmdObjects object: ingredientHolderInDB){
            ingredientHolder = new IngredientHolder();
            for(HmdValues value: object.getValues()){
                if(IngredientHolderAttributes.NUMBER_OF_INGREDIENTS_UUID.equals(value.getValuesId().getAttribute().getAttrId())){
                    ingredientHolder.setNumberOfIngredients(Integer.parseInt(value.getValue()));
                }
            }
            for(HmdRefs ref: object.getRefsOfObject()){
                if(IngredientHolderAttributes.INGREDIENT_UUID.equals(ref.getRefsId().getAttribute().getAttrId())){

                    HmdObjects ingredientInDB = ref.getRefsId().getRef();
                    ingredient = ingredientDao.getIngredientWithValues(ingredientInDB, locale);
                    ingredientHolder.setIngredient(ingredient);

                }
            }
            ingredientHolders.add(ingredientHolder);
        }

        return ingredientHolders;
    }

    @Autowired
    IngredientHolderDaoImpl(SessionHolder sessionHolder, IngredientDao ingredientDao){
        this.sessionHolder = sessionHolder;
        this.ingredientDao = ingredientDao;
    }
}
