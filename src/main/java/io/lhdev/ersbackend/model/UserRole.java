package io.lhdev.ersbackend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    private String role;

    public UserRole() {
        super();
    }

    public UserRole(String role) {
        this.role = role;
    }

    public UserRole(int roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return roleId == userRole.roleId && role.equals(userRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }
}
