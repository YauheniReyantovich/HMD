package org.reyantovich.yauheni.hmdbase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "hmd_refs")
public class HmdRefs implements Serializable{

    @EmbeddedId
    private RefsId refsId;

    public HmdRefs(){}

    public HmdRefs(HmdAttributes attribute, HmdObjects object, HmdObjects ref) {
        this.refsId = new RefsId(attribute, object, ref);
    }

    public RefsId getRefsId() {
        return refsId;
    }

    public void setRefsId(RefsId refsId) {
        this.refsId = refsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HmdRefs hmdRefs = (HmdRefs) o;
        return Objects.equals(refsId, hmdRefs.refsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refsId);
    }

    @Override
    public String toString() {
        return "HmdRefs{" +
                "refsId=" + refsId +
                '}';
    }
}
