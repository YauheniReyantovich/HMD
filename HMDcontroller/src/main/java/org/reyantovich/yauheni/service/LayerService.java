package org.reyantovich.yauheni.service;

import org.reyantovich.yauheni.model.pojo.Layer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LayerService {

    List<Layer> getAllLayers();

    void addLayer(Layer layer, Integer[] layerChances);

}
