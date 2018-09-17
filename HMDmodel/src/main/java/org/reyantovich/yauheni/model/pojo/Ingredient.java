package org.reyantovich.yauheni.model.pojo;

import java.util.Objects;

public class Ingredient {

    private String nameRus;

    private String nameEng;

    private String cost;

    private String weight;

    private String category;

    private String layer;

    public Ingredient(){}

    public Ingredient(String nameRus, String nameEng, String cost, String weight, String category, String layer) {
        this.nameRus = nameRus;
        this.nameEng = nameEng;
        this.cost = cost;
        this.weight = weight;
        this.category = category;
        this.layer = layer;
    }

    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(nameRus, that.nameRus) &&
                Objects.equals(nameEng, that.nameEng) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(category, that.category) &&
                Objects.equals(layer, that.layer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameRus, nameEng, cost, weight, category, layer);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "nameRus='" + nameRus + '\'' +
                ", nameEng='" + nameEng + '\'' +
                ", cost='" + cost + '\'' +
                ", weight='" + weight + '\'' +
                ", category='" + category + '\'' +
                ", layer='" + layer + '\'' +
                '}';
    }
}
