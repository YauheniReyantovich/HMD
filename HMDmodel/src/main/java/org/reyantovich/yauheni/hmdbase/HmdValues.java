package org.reyantovich.yauheni.hmdbase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "hmd_values")
public class HmdValues implements Serializable{

    @EmbeddedId
    private ValuesId valuesId;

    @Column(name = "value")
    private String value;

    public HmdValues(){}

    public HmdValues(HmdObjects object, HmdAttributes attribute, String value) {
        this.valuesId = new ValuesId(object, attribute);
        this.value = value;
    }

    public ValuesId getValuesId() {
        return valuesId;
    }

    public void setValuesId(ValuesId valuesId) {
        this.valuesId = valuesId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HmdValues hmdValues = (HmdValues) o;
        return Objects.equals(valuesId, hmdValues.valuesId) &&
                Objects.equals(value, hmdValues.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valuesId, value);
    }

    @Override
    public String toString() {
        return "HmdValues{" +
                "valuesId=" + valuesId +
                ", value='" + value + '\'' +
                '}';
    }
}
