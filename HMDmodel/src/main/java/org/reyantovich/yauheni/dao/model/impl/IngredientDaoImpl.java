package org.reyantovich.yauheni.dao.model.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.reyantovich.yauheni.HqlService;
import org.reyantovich.yauheni.attributesIds.CategoryAttributes;
import org.reyantovich.yauheni.attributesIds.IngredientAttributes;
import org.reyantovich.yauheni.attributesIds.LayerAttributes;
import org.reyantovich.yauheni.attributesIds.UserAttributes;
import org.reyantovich.yauheni.dao.ObjectDao;
import org.reyantovich.yauheni.dao.model.IngredientDao;
import org.reyantovich.yauheni.hmdbase.*;
import org.reyantovich.yauheni.model.pojo.Ingredient;
import org.reyantovich.yauheni.model.pojo.Layer;
import org.reyantovich.yauheni.model.pojo.User;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class IngredientDaoImpl implements IngredientDao {

    private ObjectDao objectDao;

    private SessionHolder sessionHolder;

    @Override
    public List<Ingredient> getAllIngredients(Locale locale) {
        try {
            sessionHolder.init();
            //Берем все объекты ингридиентов
            List<HmdObjects> objects = objectDao.getAllObjectsOfObjectType(IngredientAttributes.INGREDIENT_UUID);
            List<Ingredient> ingredients = new ArrayList<>();

            Ingredient ingredient;
            UUID attributeId;
            HmdAttributes refAttribute;
            HmdValues CatOrLayerName;
            //Делаем цикл по всем ингридиентам
            for(HmdObjects object: objects){
                ingredient = new Ingredient();
                //Делаем цикл по всем атрибутам ингредиента
                for(HmdValues value: object.getValues()){
                    attributeId = value.getValuesId().getAttribute().getAttrId();
                    if(IngredientAttributes.NAME_RUS_UUID.equals(attributeId)){
                        ingredient.setNameRus(value.getValue());
                    }
                    if(IngredientAttributes.NAME_ENG_UUID.equals(attributeId)){
                        ingredient.setNameEng(value.getValue());
                    }
                    if(IngredientAttributes.COST_UUID.equals(attributeId)){
                        ingredient.setCost(value.getValue());
                    }
                    if(IngredientAttributes.WEIGHT_UUDI.equals(attributeId)){
                        ingredient.setWeight(value.getValue());
                    }
                }
                //Цикл по всем объектам на которые ингредиент ссылается (категория, слой)
                for(HmdRefs ref: object.getRefsOfObject()){
                    attributeId = ref.getRefsId().getAttribute().getAttrId();
                    if(IngredientAttributes.CATEGORY_UUID.equals(attributeId)){
                        //Берем атрибут категории/слоя в зависимости от локализации
                        refAttribute = sessionHolder.getSession().get(HmdAttributes.class, ("ru".equals(locale.getLanguage()) ? CategoryAttributes.RUS_NAME_UUID : CategoryAttributes.ENG_NAME_UUID));
                        //Берем имя категории/слоя по созданному ValuesId
                        CatOrLayerName = sessionHolder.getSession().get(HmdValues.class, new ValuesId(ref.getRef()/*ссылка на объект категории*/, refAttribute));
                        ingredient.setCategory(CatOrLayerName.getValue());
                    }
                    if(IngredientAttributes.LAYER_UUID.equals(attributeId)){
                        //Берем атрибут категории/слоя в зависимости от локализации
                        refAttribute = sessionHolder.getSession().get(HmdAttributes.class, ("ru".equals(locale.getLanguage()) ? LayerAttributes.RUS_NAME_UUID : LayerAttributes.ENG_NAME_UUID));
                        //Берем имя категории/слоя по созданному ValuesId
                        CatOrLayerName = sessionHolder.getSession().get(HmdValues.class, new ValuesId(ref.getRef()/*ссылка на объект категории*/, refAttribute));
                        ingredient.setLayer(CatOrLayerName.getValue());
                    }
                }
                ingredients.add(ingredient);
            }

            return ingredients;
        } catch (Exception e) {
            sessionHolder.getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            sessionHolder.close();
        }
        return null;
    }

    @Override
    public void addIngredient(Ingredient ingredient, Locale locale) {
        if(ingredient != null) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(HqlService.SQL_PROPERTIES);
            sessionHolder = sessionHolder.init();
            Session session = sessionHolder.getSession();
            HmdObjectType ingredientObjectType = session.get(HmdObjectType.class, IngredientAttributes.INGREDIENT_UUID);
            HmdObjects object = new HmdObjects(ingredientObjectType);
            sessionHolder.save(object);

            if (ingredient.getNameEng() != null) {
                sessionHolder.save(
                        new HmdValues(object, session.get(HmdAttributes.class, IngredientAttributes.NAME_ENG_UUID), ingredient.getNameEng())
                );
            }
            if (ingredient.getNameRus() != null) {
                sessionHolder.save(
                        new HmdValues(object, session.get(HmdAttributes.class, IngredientAttributes.NAME_RUS_UUID), ingredient.getNameRus())
                );
            }
            if (ingredient.getCost() != null) {
                sessionHolder.save(
                        new HmdValues(object, session.get(HmdAttributes.class, IngredientAttributes.COST_UUID), ingredient.getCost())
                );
            }
            if (ingredient.getWeight() != null) {
                sessionHolder.save(
                        new HmdValues(object, session.get(HmdAttributes.class, IngredientAttributes.WEIGHT_UUDI), ingredient.getWeight())
                );
            }

            Query sqlQuery = session.createQuery(resourceBundle.getString(HqlService.GET_OBJECT_ID_BY_VALUE));

            sqlQuery.setParameter("value", ingredient.getCategory());
            sqlQuery.setParameter("objOTId", CategoryAttributes.CATEGORY);
            sqlQuery.setParameter("attrId", "ru".equals(locale.getLanguage()) ? CategoryAttributes.RUS_NAME : CategoryAttributes.ENG_NAME);

            Object result = sqlQuery.getSingleResult();

            if(ingredient.getCategory() != null) {
                sessionHolder.save(
                        new HmdRefs(session.get(HmdAttributes.class, IngredientAttributes.CATEGORY_UUID), object, (HmdObjects) result)
                );
            }

            sqlQuery = session.createQuery(resourceBundle.getString(HqlService.GET_OBJECT_ID_BY_VALUE));

            sqlQuery.setParameter("value", ingredient.getLayer());
            sqlQuery.setParameter("objOTId", LayerAttributes.LAYER);
            sqlQuery.setParameter("attrId", "ru".equals(locale.getLanguage()) ? LayerAttributes.RUS_NAME : LayerAttributes.ENG_NAME);

            result = sqlQuery.getSingleResult();

            if(ingredient.getLayer() != null) {
                sessionHolder.save(
                        new HmdRefs(session.get(HmdAttributes.class, IngredientAttributes.LAYER_UUID), object, (HmdObjects) result)
                );
            }

            sessionHolder.commit();
            sessionHolder.close();
        }
    }

    @Autowired
    IngredientDaoImpl(ObjectDao objectDao, SessionHolder sessionHolder){
        this.sessionHolder = sessionHolder;
        this.objectDao = objectDao;
    }
}
