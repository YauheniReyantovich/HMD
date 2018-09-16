package org.reyantovich.yauheni.dao.model;

import org.reyantovich.yauheni.model.pojo.Layer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LayerDao {

    List<Layer> getAllLayers();

    void addLayer(Layer layer, Integer[] layerChances);

}
