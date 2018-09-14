package org.reyantovich.yauheni.hmdbase;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "hmd_object_type")
public class HmdObjectType implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "object_type_id")
    private UUID objectTypeId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "objectType")
    private Set<HmdObjects> objects = new HashSet<>();

    @OneToMany(mappedBy = "objectType")
    private Set<HmdAttributes> attributes = new HashSet<>();

    public HmdObjectType(){}

    public HmdObjectType(String name) {
        this.name = name;
    }

    public UUID getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(UUID objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<HmdObjects> getObjects() {
        return objects;
    }

    public void setObjects(Set<HmdObjects> objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "HmdObjectType{" +
                "objectTypeId=" + objectTypeId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HmdObjectType that = (HmdObjectType) o;
        return Objects.equals(objectTypeId, that.objectTypeId) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectTypeId, name);
    }
}
