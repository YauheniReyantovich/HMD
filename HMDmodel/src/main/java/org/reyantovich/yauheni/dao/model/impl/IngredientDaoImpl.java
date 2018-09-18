package org.reyantovich.yauheni.dao.model.impl;

import org.hibernate.Session;
import org.reyantovich.yauheni.attributesIds.CategoryAttributes;
import org.reyantovich.yauheni.attributesIds.IngredientAttributes;
import org.reyantovich.yauheni.attributesIds.LayerAttributes;
import org.reyantovich.yauheni.dao.ObjectDao;
import org.reyantovich.yauheni.dao.RefDao;
import org.reyantovich.yauheni.dao.ValueDao;
import org.reyantovich.yauheni.dao.model.IngredientDao;
import org.reyantovich.yauheni.hmdbase.*;
import org.reyantovich.yauheni.model.pojo.Ingredient;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class IngredientDaoImpl implements IngredientDao {

    private final static String LOCALE_RU = "ru";

    private ObjectDao objectDao;

    private ValueDao valueDao;

    private RefDao refDao;

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
                        refAttribute = sessionHolder.getSession().get(HmdAttributes.class, (LOCALE_RU.equals(locale.getLanguage()) ? CategoryAttributes.RUS_NAME_UUID : CategoryAttributes.ENG_NAME_UUID));
                        //Берем имя категории/слоя по созданному ValuesId
                        CatOrLayerName = sessionHolder.getSession().get(HmdValues.class, new ValuesId(ref.getRef()/*ссылка на объект категории*/, refAttribute));
                        ingredient.setCategory(CatOrLayerName.getValue());
                    }
                    if(IngredientAttributes.LAYER_UUID.equals(attributeId)){
                        //Берем атрибут категории/слоя в зависимости от локализации
                        refAttribute = sessionHolder.getSession().get(HmdAttributes.class, (LOCALE_RU.equals(locale.getLanguage()) ? LayerAttributes.RUS_NAME_UUID : LayerAttributes.ENG_NAME_UUID));
                        //Берем имя категории/слоя по созданному ValuesId
                        CatOrLayerName = sessionHolder.getSession().get(HmdValues.class, new ValuesId(ref.getRef()/*ссылка на объект слоя*/, refAttribute));
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
            sessionHolder = sessionHolder.init();
            Session session = sessionHolder.getSession();
            HmdObjectType ingredientObjectType = session.get(HmdObjectType.class, IngredientAttributes.INGREDIENT_UUID);
            /*New object*/
            HmdObjects object = new HmdObjects(ingredientObjectType);
            sessionHolder.save(object);

            /*Add values to new object*/
            Map<UUID, String> values = new HashMap<>();
            values.put(IngredientAttributes.NAME_ENG_UUID, ingredient.getNameEng());
            values.put(IngredientAttributes.NAME_RUS_UUID, ingredient.getNameRus());
            values.put(IngredientAttributes.COST_UUID, ingredient.getCost());
            values.put(IngredientAttributes.WEIGHT_UUDI, ingredient.getWeight());
            valueDao.addValues(object, values);

            /*Add refs to new object*/
            Map<UUID, HmdObjects> refs = new HashMap<>();
            HmdObjects ref;
            if(ingredient.getCategory() != null) {
                ref = objectDao.getObjectByValue(
                        CategoryAttributes.CATEGORY,
                        LOCALE_RU.equals(locale.getLanguage()) ? CategoryAttributes.RUS_NAME : CategoryAttributes.ENG_NAME,
                        ingredient.getCategory());
                refs.put(IngredientAttributes.CATEGORY_UUID, ref);
            }
            if(ingredient.getLayer() != null) {
                ref = objectDao.getObjectByValue(
                        LayerAttributes.LAYER,
                        LOCALE_RU.equals(locale.getLanguage()) ? LayerAttributes.RUS_NAME : LayerAttributes.ENG_NAME,
                        ingredient.getLayer()
                );
                refs.put(IngredientAttributes.LAYER_UUID, ref);
            }
            refDao.addRefs(object, refs);

            sessionHolder.commit();
            sessionHolder.close();
        }
    }

    @Autowired
    IngredientDaoImpl(ObjectDao objectDao, ValueDao valueDao, RefDao refDao, SessionHolder sessionHolder){
        this.sessionHolder = sessionHolder;
        this.valueDao = valueDao;
        this.refDao = refDao;
        this.objectDao = objectDao;
    }
}
