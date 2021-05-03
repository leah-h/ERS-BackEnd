package io.lhdev.ersbackend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="reimbursements")
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="reim_id")
    private int reimId;

    private double amount;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date submitted;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date resolved;

    private String description;

    private int author;

    private int resolver;

    @Column(name="status_id")
    @JoinColumn(name="status_id")
    private int statusId;

    @Column(name="type_id")
    @JoinColumn(name="type_id")
    private int typeId;

    private String receipt;

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

    public Reimbursement(int reimId, double amount, String description, int author, int typeId){
        this.reimId = reimId;
        this.amount = amount;
        this.description = description;
        this.author = author;
        this.typeId = typeId;
    }

    public Reimbursement(double amount, Date submitted, String description, int author, int typeId){
        this.amount = amount;
        this.submitted = submitted;
        this.description = description;
        this.author = author;
        this.typeId = typeId;
    }

    public Reimbursement(int reimId, double amount, Date submitted, Date resolved, String description, int author,
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

    public Reimbursement(int reimId, double amount, Date submitted, Date resolved, String description, int author,
                         int resolver, int statusId, int typeId, String receipt) {
        this.reimId = reimId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.statusId = statusId;
        this.typeId = typeId;
        this.receipt = receipt;
    }

    public int getId() {
        return reimId;
    }

    public void setId(int reimId) {
        this.reimId = reimId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return reimId == that.reimId && amount == that.amount && author == that.author && resolver == that.resolver
                && statusId == that.statusId && typeId == that.typeId && receipt == that.receipt
                && submitted.equals(that.submitted) && Objects.equals(resolved, that.resolved)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimId, amount, submitted, resolved, description, author, resolver, statusId, typeId, receipt);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimId=" + reimId +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", statusId=" + statusId +
                ", typeId=" + typeId +
                ", receipt=" + receipt +
                '}';
    }
}
