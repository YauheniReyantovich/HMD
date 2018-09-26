package org.reyantovich.yauheni.model.pojo;

import java.util.Objects;

public class IngredientHolder {

    private Ingredient ingredient;

    private Integer numberOfIngredients;

    public IngredientHolder(){}

    public IngredientHolder(Ingredient ingredient, Integer numberOfIngredients) {
        this.ingredient = ingredient;
        this.numberOfIngredients = numberOfIngredients;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getNumberOfIngredients() {
        return numberOfIngredients;
    }

    public void setNumberOfIngredients(Integer numberOfIngredients) {
        this.numberOfIngredients = numberOfIngredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientHolder that = (IngredientHolder) o;
        return Objects.equals(ingredient, that.ingredient) &&
                Objects.equals(numberOfIngredients, that.numberOfIngredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient, numberOfIngredients);
    }

    @Override
    public String toString() {
        return "IngredientHolder{" +
                "ingredient=" + ingredient +
                ", number=" + numberOfIngredients +
                '}';
    }
}
