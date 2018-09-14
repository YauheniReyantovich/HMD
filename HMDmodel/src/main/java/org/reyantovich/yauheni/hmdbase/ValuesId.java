package org.reyantovich.yauheni.hmdbase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ValuesId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "object_id")
    private HmdObjects object;

    @ManyToOne
    @JoinColumn(name = "attr_id")
    private HmdAttributes attribute;

    public ValuesId() {
    }

    public ValuesId(HmdObjects object, HmdAttributes attribute) {
        this.object = object;
        this.attribute = attribute;
    }

    public HmdAttributes getAttribute() {
        return attribute;
    }

    public void setAttribute(HmdAttributes attribute) {
        this.attribute = attribute;
    }

    public HmdObjects getObject() {
        return object;
    }

    public void setObject(HmdObjects object) {
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValuesId valuesId = (ValuesId) o;
        return Objects.equals(attribute, valuesId.attribute) &&
                Objects.equals(object, valuesId.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute, object);
    }

    @Override
    public String toString() {
        return "ValuesId{" +
                "attribute=" + attribute +
                ", object=" + object +
                '}';
    }
}
