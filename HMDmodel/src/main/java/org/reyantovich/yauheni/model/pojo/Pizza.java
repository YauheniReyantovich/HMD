package org.reyantovich.yauheni.model.pojo;

import java.util.List;
import java.util.Objects;

public class Pizza {

    private List<IngredientHolder> ingredients;

    private Double totalCost;

    public Pizza(){
        totalCost = 0.;
    }

    public Pizza(List<IngredientHolder> ingredients, Double totalCost) {
        this.ingredients = ingredients;
        this.totalCost = totalCost;
    }

    public List<IngredientHolder> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientHolder> ingredients) {
        this.ingredients = ingredients;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(ingredients, pizza.ingredients) &&
                Objects.equals(totalCost, pizza.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients, totalCost);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "ingredients=" + ingredients +
                ", totalCost=" + totalCost +
                '}';
    }
}
