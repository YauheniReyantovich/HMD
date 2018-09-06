package org.reyantovich.yauheni.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@Entity
public class HmdObjectType implements Serializable {

    @Id
    private UUID objectTypeId;

    private String name;

    public HmdObjectType(){}

    public HmdObjectType(UUID objectTypeId, String name) {
        this.objectTypeId = objectTypeId;
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

    @Override
    public String toString() {
        return "HmdObjectType{" +
                "objectTypeId=" + objectTypeId +
                ", name='" + name + '\'' +
                '}';
    }
}
