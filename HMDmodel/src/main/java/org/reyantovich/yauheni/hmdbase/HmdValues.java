package org.reyantovich.yauheni.hmdbase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "HMD_VALUES")
@IdClass(HmdValues.ValuesId.class)
public class HmdValues implements Serializable{

    @Id
    @ManyToOne
    private HmdObjects object;

    @Id
    @ManyToOne
    private HmdAttributes attribute;

    private String value;

    public HmdValues(){}

    public HmdValues(HmdObjects object, HmdAttributes attribute, String value) {
        this.object = object;
        this.attribute = attribute;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HmdValues{" +
                "attribute=" + attribute +
                ", object=" + object +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HmdValues hmdValues = (HmdValues) o;
        return Objects.equals(attribute, hmdValues.attribute) &&
                Objects.equals(object, hmdValues.object) &&
                Objects.equals(value, hmdValues.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute, object, value);
    }

    public class ValuesId implements Serializable{

        private HmdObjects object;
        private HmdAttributes attribute;

        public ValuesId(){}

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
}
