package io.lhdev.ersbackend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "r_status")
public class ReimbursementStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;

    private String status;

    public ReimbursementStatus() {
        super();
    }

    public ReimbursementStatus(String status) {
        this.status = status;
    }

    public ReimbursementStatus(int statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementStatus that = (ReimbursementStatus) o;
        return statusId == that.statusId && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, status);
    }

    @Override
    public String toString() {
        return "ReimbursementStatus{" +
                "statusId=" + statusId +
                ", status='" + status + '\'' +
                '}';
    }
}
