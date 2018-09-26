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

    @ManyToOne
    @JoinColumn(name = "ref")
    private HmdObjects ref;

    public RefsId() {
    }

    public RefsId(HmdAttributes attribute, HmdObjects object, HmdObjects ref) {
        this.attribute = attribute;
        this.object = object;
        this.ref = ref;
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

    public HmdObjects getRef() {
        return ref;
    }

    public void setRef(HmdObjects ref) {
        this.ref = ref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefsId refsId = (RefsId) o;
        return Objects.equals(attribute, refsId.attribute) &&
                Objects.equals(object, refsId.object) &&
                Objects.equals(ref, refsId.ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute, object, ref);
    }

    @Override
    public String toString() {
        return "RefsId{" +
                "attribute=" + attribute +
                ", object=" + object +
                ", ref=" + ref +
                '}';
    }
}
