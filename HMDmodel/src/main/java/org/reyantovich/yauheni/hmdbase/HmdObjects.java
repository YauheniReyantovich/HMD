package org.reyantovich.yauheni.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "HMD_OBJECTS")
public class HmdObjects implements Serializable{

    @Id
    private UUID objectId;

    @ManyToOne
    private HmdObjectType objectType;

    @OneToMany
    private Set<HmdValues> values = new HashSet<>();

    @OneToMany
    private Set<HmdRefs> refs = new HashSet<>();

    public HmdObjects(){}

    public HmdObjects(UUID objectId, HmdObjectType objectType) {
        this.objectId = objectId;
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
                "objectId=" + objectId +
                ", objectType=" + objectType +
                '}';
    }
}
