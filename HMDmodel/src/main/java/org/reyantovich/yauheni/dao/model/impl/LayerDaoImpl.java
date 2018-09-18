package org.reyantovich.yauheni.dao.model.impl;

import org.hibernate.Session;
import org.reyantovich.yauheni.attributesIds.LayerAttributes;
import org.reyantovich.yauheni.dao.ObjectDao;
import org.reyantovich.yauheni.dao.ValueDao;
import org.reyantovich.yauheni.dao.model.LayerDao;
import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.hmdbase.HmdValues;
import org.reyantovich.yauheni.model.pojo.Layer;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class LayerDaoImpl implements LayerDao {

    private static final String splitter = ", ";

    private SessionHolder sessionHolder;

    private ObjectDao objectDao;

    private ValueDao valueDao;

    @Override
    public List<Layer> getAllLayers() {
        try {
            sessionHolder.init();
            List<HmdObjects> objects = objectDao.getAllObjectsOfObjectType(LayerAttributes.LAYER_UUID);
            List<Layer> layers = new ArrayList<>();

            Layer layer;
            for(HmdObjects object: objects){
                layer = new Layer();
                for(HmdValues value: object.getValues()){
                    UUID attributeId = value.getValuesId().getAttribute().getAttrId();
                    if(LayerAttributes.RUS_NAME_UUID.equals(attributeId)){
                        layer.setRusName(value.getValue());
                    }
                    if(LayerAttributes.ENG_NAME_UUID.equals(attributeId)){
                        layer.setEngName(value.getValue());
                    }
                    if(LayerAttributes.MAXIMUM_INGREDIENTS_UUID.equals(attributeId)){
                        layer.setMaxIngredients(Integer.valueOf(value.getValue()));
                    }
                    if(LayerAttributes.INGREDIENTS_CHANCE_UUID.equals(attributeId)){
                        layer.setIngredientChance(Arrays.stream(value.getValue().split(splitter)).map(Integer::parseInt).collect(Collectors.toList()));
                    }
                }
                layers.add(layer);
            }

            return layers;
        } catch (Exception e) {
            sessionHolder.getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            sessionHolder.close();
        }
        return null;
    }

    @Override
    public void addLayer(Layer layer, Integer[] layerChances) {
        if(layer != null) {
            sessionHolder = sessionHolder.init();
            Session session = sessionHolder.getSession();
            HmdObjectType layerObjectType = session.get(HmdObjectType.class, LayerAttributes.LAYER_UUID);
            HmdObjects object = new HmdObjects(layerObjectType);
            sessionHolder.save(object);

            Map<UUID, String> values = new HashMap<>();
            values.put(LayerAttributes.ENG_NAME_UUID, layer.getEngName());
            values.put(LayerAttributes.RUS_NAME_UUID, layer.getRusName());
            values.put(LayerAttributes.MAXIMUM_INGREDIENTS_UUID, layer.getMaxIngredients().toString());
            String layerChancesStr = Arrays.toString(layerChances);
            values.put(LayerAttributes.INGREDIENTS_CHANCE_UUID, layerChancesStr.substring(1, layerChancesStr.length()-1));
            valueDao.addValues(object, values);

            sessionHolder.commit();
            sessionHolder.close();
        }

    }

    public LayerDaoImpl(SessionHolder sessionHolder, ObjectDao objectDao, ValueDao valueDao){
        this.sessionHolder = sessionHolder;
        this.objectDao = objectDao;
        this.valueDao = valueDao;
    }
}
