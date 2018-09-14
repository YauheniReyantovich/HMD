package org.reyantovich.yauheni.hmdbase;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "hmd_attributes")
public class HmdAttributes {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "attr_id")
    private UUID attrId;

    @ManyToOne
    @JoinColumn(name = "object_type_id")
    private HmdObjectType objectType;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "valuesId.attribute")
    private Set<HmdValues> values = new HashSet<>();

    @OneToMany(mappedBy = "refsId.attribute")
    private Set<HmdRefs> refs = new HashSet<>();

    public HmdAttributes(){}

    public HmdAttributes(HmdObjectType objectType, String name) {
        this.objectType = objectType;
        this.name = name;
    }

    public UUID getAttrId() {
        return attrId;
    }

    public void setAttrId(UUID attrId) {
        this.attrId = attrId;
    }

    public HmdObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(HmdObjectType objectType) {
        this.objectType = objectType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HmdAttributes{" +
                "attrId=" + attrId +
                ", objectType=" + objectType +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HmdAttributes that = (HmdAttributes) o;
        return Objects.equals(attrId, that.attrId) &&
                Objects.equals(objectType, that.objectType) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attrId, objectType, name);
    }
}
