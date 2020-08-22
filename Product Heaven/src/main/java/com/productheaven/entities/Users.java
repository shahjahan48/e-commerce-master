package com.productheaven.entities;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Users {
    private long id;
    private String username;
    private String password;
    private String emailAddress;
    private Date createdDate;
    private boolean isActive;
    private Set<UserRoles> userRoles;

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "Id", table = "Users", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Username", table = "Users", nullable = false, length = 128)
    @Nationalized
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "Password", table = "Users", nullable = false, length = 128)
    @Nationalized
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "EmailAddress", table = "Users", nullable = false, length = 64)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Basic
    @Column(name = "CreatedDate", table = "Users", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "IsActive", table = "Users", nullable = false)
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @OneToMany(mappedBy = "user")
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
        Users users = (Users) o;
        return id == users.id &&
                isActive == users.isActive &&
                Objects.equals(username, users.username) &&
                Objects.equals(password, users.password) &&
                Objects.equals(emailAddress, users.emailAddress) &&
                Objects.equals(createdDate, users.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, emailAddress, createdDate, isActive);
    }
}
