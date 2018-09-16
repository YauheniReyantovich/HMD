package org.reyantovich.yauheni.service.impl;

import org.reyantovich.yauheni.dao.model.LayerDao;
import org.reyantovich.yauheni.model.pojo.Layer;
import org.reyantovich.yauheni.service.LayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LayerServiceImpl implements LayerService {

    private LayerDao layerDao;

    @Override
    public List<Layer> getAllLayers() {
        return layerDao.getAllLayers();
    }

    @Override
    public void addLayer(Layer layer, Integer[] layerChances) {
        layerDao.addLayer(layer, layerChances);
    }

    @Autowired
    public LayerServiceImpl(LayerDao layerDao){
        this.layerDao = layerDao;
    }
}
