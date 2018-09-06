package org.reyantovich.yauheni.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
//@Table(name = "HMD_ATTRIBUTES")
public class HmdAttributes {

    @Id
    private UUID attrId;

    @ManyToOne
    private HmdObjectType objectType;

    private String name;

    public HmdAttributes(){}

    public HmdAttributes(UUID attrId, HmdObjectType objectType, String name) {
        this.attrId = attrId;
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
}
