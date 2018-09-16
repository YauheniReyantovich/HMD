package org.reyantovich.yauheni.model.pojo;

import java.util.List;
import java.util.Objects;

public class Layer {

    private String rusName;
    private String engName;
    private Integer maxIngredients;
    private List<Integer> IngredientChance;

    public Layer() {
    }

    public Layer(String rusName, String engName, Integer maxIngredients, List<Integer> ingredientChance) {
        this.rusName = rusName;
        this.engName = engName;
        this.maxIngredients = maxIngredients;
        IngredientChance = ingredientChance;
    }

    public String getRusName() {
        return rusName;
    }

    public void setRusName(String rusName) {
        this.rusName = rusName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public Integer getMaxIngredients() {
        return maxIngredients;
    }

    public void setMaxIngredients(Integer maxIngredients) {
        this.maxIngredients = maxIngredients;
    }

    public List<Integer> getIngredientChance() {
        return IngredientChance;
    }

    public void setIngredientChance(List<Integer> ingredientChance) {
        IngredientChance = ingredientChance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Layer layer = (Layer) o;
        return Objects.equals(rusName, layer.rusName) &&
                Objects.equals(engName, layer.engName) &&
                Objects.equals(IngredientChance, layer.IngredientChance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rusName, engName, IngredientChance);
    }

    @Override
    public String toString() {
        return "Layer{" +
                "rusName='" + rusName + '\'' +
                ", engName='" + engName + '\'' +
                ", maxIngredients=" + maxIngredients +
                ", IngredientChance=" + IngredientChance +
                '}';
    }
}
