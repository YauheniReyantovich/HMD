package org.reyantovich.yauheni.hmdbase;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "hmd_objects")
public class HmdObjects implements Serializable{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "object_id")
    private UUID objectId;

    @ManyToOne
    @JoinColumn(name = "object_type_id")
    private HmdObjectType objectType;

    @OneToMany(mappedBy = "valuesId.object")
    private Set<HmdValues> values = new HashSet<>();

    @OneToMany(mappedBy = "refsId.object")
    private Set<HmdRefs> objects = new HashSet<>();

    @OneToMany(mappedBy = "refsId.ref")
    private Set<HmdRefs> refs = new HashSet<>();

    public HmdObjects(){}

    public HmdObjects(HmdObjectType objectType) {
        this.objectType = objectType;
    }

    public UUID getObjectId() {
        return objectId;
    }

    public void setObjectId(UUID objectId) {
        this.objectId = objectId;
    }

    public HmdObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(HmdObjectType objectType) {
        this.objectType = objectType;
    }

    public Set<HmdValues> getValues(){return values;}

    public void setValues(Set<HmdValues> values){this.values = values;}

    @Override
    public String toString() {
        return "HmdObjects{" +
                "objectId=" + objectId.toString() +
                ", objectType=" + objectType.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HmdObjects objects = (HmdObjects) o;
        return Objects.equals(objectId, objects.objectId) &&
                Objects.equals(objectType, objects.objectType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectId, objectType);
    }
}
