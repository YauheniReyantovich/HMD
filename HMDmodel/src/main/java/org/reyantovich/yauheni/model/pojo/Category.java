package org.reyantovich.yauheni.model.pojo;

import java.util.Objects;

public class Category {

    private String rusName;

    private String engName;

    public Category(){}

    public Category(String rusName, String engName) {
        this.rusName = rusName;
        this.engName = engName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(rusName, category.rusName) &&
                Objects.equals(engName, category.engName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rusName, engName);
    }

    @Override
    public String toString() {
        return "Category{" +
                "rusName='" + rusName + '\'' +
                ", engName='" + engName + '\'' +
                '}';
    }
}
