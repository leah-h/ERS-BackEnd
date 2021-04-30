package io.lhdev.ersbackend.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reimbursements")
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reimId;

    private int amount;
    private Date submitted;
    private Date resolved;
    private String description;

    @JoinColumn(name="userId")
    private int author;
    private int resolver;

    @JoinColumn(name="statusId")
    private int statusId;

    @JoinColumn(name="typeId")
    private int typeId;

    public Reimbursement() {
        super();
    }

    public Reimbursement(int amount, Date submitted, Date resolved, String description, int author, int resolver,
                         int statusId, int typeId) {
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public Reimbursement(int reimId, int amount, String description, int author, int typeId){
        this.reimId = reimId;
        this.amount = amount;
        this.description = description;
        this.author = author;
        this.typeId = typeId;
    }

    public Reimbursement(int reimId, int amount, Date submitted, Date resolved, String description, int author,
                         int resolver, int statusId, int typeId) {
        this.reimId = reimId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public int getId() {
        return reimId;
    }

    public void setId(int reimId) {
        this.reimId = reimId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getResolver() {
        return resolver;
    }

    public void setResolver(int resolver) {
        this.resolver = resolver;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return reimId == that.reimId && amount == that.amount && author == that.author && resolver == that.resolver &&
                statusId == that.statusId && typeId == that.typeId && submitted.equals(that.submitted) &&
                resolved.equals(that.resolved) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimId, amount, submitted, resolved, description, author, resolver, statusId, typeId);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + reimId +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", statusId=" + statusId +
                ", typeId=" + typeId +
                '}';
    }
}
