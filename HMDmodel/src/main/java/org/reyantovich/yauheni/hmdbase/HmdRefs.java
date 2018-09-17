package org.reyantovich.yauheni.hmdbase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "hmd_refs")
public class HmdRefs implements Serializable{

    @EmbeddedId
    private RefsId refsId;

    @ManyToOne
    @JoinColumn(name = "ref")
    private HmdObjects ref;

    public HmdRefs(){}

    public HmdRefs(HmdAttributes attribute, HmdObjects object, HmdObjects ref) {
        this.refsId = new RefsId(attribute, object);
        this.ref = ref;
    }

    public RefsId getRefsId() {
        return refsId;
    }

    public void setRefsId(RefsId refsId) {
        this.refsId = refsId;
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
        HmdRefs hmdRefs = (HmdRefs) o;
        return Objects.equals(refsId, hmdRefs.refsId) &&
                Objects.equals(ref, hmdRefs.ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refsId, ref);
    }

    @Override
    public String toString() {
        return "HmdRefs{" +
                "refsId=" + refsId +
                ", ref=" + ref +
                '}';
    }
}
