package com.productheaven.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_roles", schema = "product_heaven")
public class UserRoles {
    private long id;
    private Timestamp createdDate;
    private Users user;
    private Roles role;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "created_date", nullable = false)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    public Users getUser() {
        return user;
    }

    private void setUser(Users user) {
        this.user = user;
    }

    @ManyToOne()
    @JoinColumn(name = "role_id", updatable = false)
    public Roles getRole() {
        return role;
    }

    private void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoles userRoles = (UserRoles) o;

        if (id != userRoles.id) return false;
        if (createdDate != null ? !createdDate.equals(userRoles.createdDate) : userRoles.createdDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
