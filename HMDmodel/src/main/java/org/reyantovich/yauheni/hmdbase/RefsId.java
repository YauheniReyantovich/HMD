package org.reyantovich.yauheni.hmdbase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RefsId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "attr_id")
    private HmdAttributes attribute;

    @ManyToOne
    @JoinColumn(name = "object_id")
    private HmdObjects object;

    public RefsId() {
    }

    public RefsId(HmdAttributes attribute, HmdObjects object) {
        this.attribute = attribute;
        this.object = object;
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
        RefsId refsId = (RefsId) o;
        return Objects.equals(attribute, refsId.attribute) &&
                Objects.equals(object, refsId.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute, object);
    }

    @Override
    public String toString() {
        return "RefsId{" +
                "attribute=" + attribute +
                ", object=" + object +
                '}';
    }
}
