package io.lhdev.ersbackend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="r_type")
public class ReimbursementType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeId;

    private String type;

    public ReimbursementType() {
        super();
    }

    public ReimbursementType(String type) {
        this.type = type;
    }

    public ReimbursementType(int typeId, String type) {
        this.typeId = typeId;
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementType that = (ReimbursementType) o;
        return typeId == that.typeId && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, type);
    }

    @Override
    public String toString() {
        return "ReimbursementType{" +
                "typeId=" + typeId +
                ", type='" + type + '\'' +
                '}';
    }
}
