package com.productheaven.entities;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
public class LoginInfo {
    private long id;
    private String username;
    private String series;
    private String token;
    private Date lastUsed;

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "Id", table = "LoginInfo", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Username", table = "LoginInfo", nullable = false, length = 128)
    @Nationalized
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "Series", table = "LoginInfo", nullable = false, length = 256)
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Basic
    @Column(name = "Token", table = "LoginInfo", nullable = false, length = 128)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "LastUsed", table = "LoginInfo", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginInfo loginInfo = (LoginInfo) o;
        return id == loginInfo.id &&
                Objects.equals(username, loginInfo.username) &&
                Objects.equals(series, loginInfo.series) &&
                Objects.equals(token, loginInfo.token) &&
                Objects.equals(lastUsed, loginInfo.lastUsed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, series, token, lastUsed);
    }
}
