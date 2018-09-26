package org.reyantovich.yauheni.model.pojo;

import java.util.Objects;

public class Category implements Comparable{

    private String rusName;

    private String engName;

    private Integer Priority;

    public Category(){}

    public Category(String rusName, String engName, Integer priority) {
        this.rusName = rusName;
        this.engName = engName;
        Priority = priority;
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

    public Integer getPriority() {
        return Priority;
    }

    public void setPriority(Integer priority) {
        Priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(rusName, category.rusName) &&
                Objects.equals(engName, category.engName) &&
                Objects.equals(Priority, category.Priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rusName, engName, Priority);
    }

    @Override
    public String toString() {
        return "Category{" +
                "rusName='" + rusName + '\'' +
                ", engName='" + engName + '\'' +
                ", Priority=" + Priority +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) return 0;
        Category category = (Category) o;
        if(this.getPriority() > category.getPriority()){
            return 1;
        }else if(this.getPriority() < category.getPriority()){
            return -1;
        }else {
            return 0;
        }
    }
}
