package org.reyantovich.yauheni.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "HMD_REFS")
@IdClass(HmdRefs.RefId.class)
public class HmdRefs implements Serializable{

    @Id
    @ManyToOne
    private HmdAttributes attribute;

    @Id
    @ManyToOne
    private HmdObjects object;

    @Id
    @ManyToOne
    private HmdObjects reference;

    public HmdRefs(){}

    public HmdRefs(HmdAttributes attribute, HmdObjects object, HmdObjects reference) {
        this.attribute = attribute;
        this.object = object;
        this.reference = reference;
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

    public HmdObjects getReference() {
        return reference;
    }

    public void setReference(HmdObjects reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "HmdRefs{" +
                "attributes=" + attribute +
                ", object=" + object +
                ", ref=" + reference +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HmdRefs hmdRefs = (HmdRefs) o;
        return Objects.equals(attribute, hmdRefs.attribute) &&
                Objects.equals(object, hmdRefs.object) &&
                Objects.equals(reference, hmdRefs.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute, object, reference);
    }

    public class RefId implements Serializable{

        private HmdAttributes attribute;
        private HmdObjects object;
        private HmdObjects reference;

        public RefId(){}

        public RefId(HmdAttributes attribute, HmdObjects object, HmdObjects reference) {
            this.attribute = attribute;
            this.object = object;
            this.reference = reference;
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

        public HmdObjects getReference() {
            return reference;
        }

        public void setReference(HmdObjects reference) {
            this.reference = reference;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RefId refId = (RefId) o;
            return Objects.equals(attribute, refId.attribute) &&
                    Objects.equals(object, refId.object) &&
                    Objects.equals(reference, refId.reference);
        }

        @Override
        public int hashCode() {

            return Objects.hash(attribute, object, reference);
        }

        @Override
        public String toString() {
            return "RefId{" +
                    "attribute=" + attribute +
                    ", object=" + object +
                    ", reference=" + reference +
                    '}';
        }
    }
}
