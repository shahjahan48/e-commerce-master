package com.productheaven.entities;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Roles {
    private long id;
    private String roleName;
    private String roleText;
    private Date createdDate;
    private Set<UserRoles> userRoles;

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "Id", table = "Roles", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Nationalized
    @Column(name = "RoleName", table = "Roles", nullable = false, length = 128)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Nationalized
    @Column(name = "RoleText", table = "Roles", nullable = false, length = 128)
    public String getRoleText() {
        return roleText;
    }

    public void setRoleText(String roleText) {
        this.roleText = roleText;
    }

    @Basic
    @Column(name = "CreatedDate", table = "Roles", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @OneToMany(mappedBy = "role")
    public Set<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return id == roles.id &&
                Objects.equals(roleName, roles.roleName) &&
                Objects.equals(roleText, roles.roleText) &&
                Objects.equals(createdDate, roles.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, roleText, createdDate);
    }
}
