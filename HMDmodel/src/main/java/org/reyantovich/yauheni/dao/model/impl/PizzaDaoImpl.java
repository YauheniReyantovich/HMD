package org.reyantovich.yauheni.dao.model.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.reyantovich.yauheni.HqlService;
import org.reyantovich.yauheni.attributesIds.IngredientAttributes;
import org.reyantovich.yauheni.attributesIds.IngredientHolderAttributes;
import org.reyantovich.yauheni.attributesIds.PizzaAttributes;
import org.reyantovich.yauheni.dao.ObjectDao;
import org.reyantovich.yauheni.dao.model.IngredientHolderDao;
import org.reyantovich.yauheni.dao.model.PizzaDao;
import org.reyantovich.yauheni.hmdbase.*;
import org.reyantovich.yauheni.model.pojo.Pizza;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.*;

@Component
public class PizzaDaoImpl implements PizzaDao {

    private final static String LOCALE_RU = "ru";

    private final static String THE_ONLY_INGREDIENT = "1";

    private SessionHolder sessionHolder;

    private IngredientHolderDao ingredientHolderDao;

    private ObjectDao objectDao;

    @Override
    public String createNewPizza() {
        try {
            sessionHolder = sessionHolder.init();
            Session session = sessionHolder.getSession();
            HmdObjectType pizzaObjectType = session.get(HmdObjectType.class, PizzaAttributes.PIZZA_UUID);
            HmdObjects object = new HmdObjects(pizzaObjectType);
            sessionHolder.saveAndCommit(object);
            return object.getObjectId().toString();
        } catch (Exception e) {
            sessionHolder.getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            sessionHolder.close();
        }
        return null;
    }

    @Override
    public Pizza getPizza(String id, Locale locale){
        try {
            sessionHolder.init();
            HmdObjects pizzaInDB = sessionHolder.getSession().get(HmdObjects.class, UUID.fromString(id));
            Pizza pizza = new Pizza();
            List<HmdObjects> ingredientHolders = new ArrayList<>();
            for(HmdRefs ref: pizzaInDB.getRefsOfObject()){
                if(PizzaAttributes.INGREDIENT_HOLDERS_UUID.equals(ref.getRefsId().getAttribute().getAttrId())){
                    ingredientHolders.add(ref.getRefsId().getRef());
                }
            }

            pizza.setIngredients(ingredientHolderDao.getIngredientHolders(ingredientHolders, locale));

            return pizza;
        } catch (Exception e) {
            sessionHolder.getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            sessionHolder.close();
        }
        return null;
    }

    @Override
    public void addIngredient(String pizzaId, String ingredientToAdd, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(HqlService.SQL_PROPERTIES);
        try {
            sessionHolder.init();
            Session session = sessionHolder.getSession();

            Query sqlQuery = session.createQuery(resourceBundle.getString(HqlService.GET_INGREDIENT_HOLDER_OF_PIZZA_WITH_INGREDIENT_NAME));

            sqlQuery.setParameter("ihToIngrAttr", IngredientHolderAttributes.INGREDIENT);
            sqlQuery.setParameter("pizToIhAttr", PizzaAttributes.INGREDIENT_HOLDERS);
            sqlQuery.setParameter("ingrName", ingredientToAdd);
            sqlQuery.setParameter("ingrNameAttr", LOCALE_RU.equals(locale.getLanguage()) ? IngredientAttributes.NAME_RUS : IngredientAttributes.NAME_ENG);
            sqlQuery.setParameter("pizId", pizzaId);

            Object result =  sqlQuery.getResultList();

            if(((List) result).isEmpty()){
                addNewIngredient(pizzaId, ingredientToAdd, locale);
            }else {
                increaseNumberExistingIngredients((HmdObjects) ((List) result).get(0));
            }

            sessionHolder.commit();
        } catch (Exception e) {
            sessionHolder.getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            sessionHolder.close();
        }
    }

    private void addNewIngredient(String pizzaId, String ingredientToAdd, Locale locale){
        Session session = sessionHolder.getSession();
        HmdObjectType ingredientHolderObjectType = session.get(HmdObjectType.class, IngredientHolderAttributes.INGREDIENT_HOLDER_UUID);
        //записываем объект ingredientHolder
        HmdObjects ingredientHolder = new HmdObjects(ingredientHolderObjectType);
        sessionHolder.save(ingredientHolder);

        for(HmdAttributes attribute: ingredientHolderObjectType.getAttributes()){
            if(IngredientHolderAttributes.INGREDIENT_UUID.equals(attribute.getAttrId())){
                //Добавляем ingredientHolder'у ссылку на ингредиент
                HmdObjects ingredient = objectDao.getObjectByValue(
                        IngredientAttributes.INGREDIENT,
                        LOCALE_RU.equals(locale.getLanguage()) ? IngredientAttributes.NAME_RUS : IngredientAttributes.NAME_ENG,
                        ingredientToAdd);
                HmdRefs refIngredientHolderToIngredient = new HmdRefs(attribute, ingredientHolder, ingredient);
                sessionHolder.save(refIngredientHolderToIngredient);
            }
            if(IngredientHolderAttributes.NUMBER_OF_INGREDIENTS_UUID.equals(attribute.getAttrId())){
                //Добавляем пицце ссылку на ingredientHolder
                HmdValues numberOfIngredientsOfIngredientHolder = new HmdValues(ingredientHolder, attribute, THE_ONLY_INGREDIENT);
                sessionHolder.save(numberOfIngredientsOfIngredientHolder);
            }
        }

        //Добавляем ingredientHolder'у количество ингредиентов равное 1
        HmdObjects pizza = session.get(HmdObjects.class, UUID.fromString(pizzaId));
        HmdAttributes refAttrPizzaToIngredientHolder = session.get(HmdAttributes.class, PizzaAttributes.INGREDIENT_HOLDERS_UUID);
        HmdRefs RefPizzaToIngredientHolder = new HmdRefs(refAttrPizzaToIngredientHolder, pizza, ingredientHolder);
        sessionHolder.save(RefPizzaToIngredientHolder);
    }

    private void increaseNumberExistingIngredients(HmdObjects ingredientToAdd){
        Session session = sessionHolder.getSession();

        HmdAttributes attribute = session.get(HmdAttributes.class, IngredientHolderAttributes.NUMBER_OF_INGREDIENTS_UUID);
        HmdValues value = session.get(HmdValues.class, new ValuesId(ingredientToAdd, attribute));
        int numberOfIngredients = Integer.parseInt(value.getValue()) + 1;
        value.setValue(String.valueOf(numberOfIngredients));
        session.save(value);

    }

    @Autowired
    PizzaDaoImpl(SessionHolder sessionHolder, IngredientHolderDao ingredientHolderDao, ObjectDao objectDao){
        this.sessionHolder = sessionHolder;
        this.ingredientHolderDao = ingredientHolderDao;
        this.objectDao = objectDao;
    }
}
