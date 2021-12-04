package com.uva.users.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.uva.users.util.AttributeEncryptor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "User", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class User {
    @GeneratedValue
    @Id
    private int id;

    @Size(max = 30)
    @Column(nullable = false)
    private String nick;

    @Size(max = 30)
    private String firstName;

    @Size(max = 30)
    private String lastName;

    @Size(max = 30)
    @Email(message = "Email is not valid")
    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Size(max = 30)
    @Column(nullable = false)
    @Convert(converter = AttributeEncryptor.class)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @CreatedDate
    @Column(nullable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public User() {
    }

    public User(String nick, String firstName, String lastName, String email, String password, Boolean enabled) {
        this.nick = nick;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public int getId() {
        return this.id;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "{" + " Id='" + getId() + "'" + ", nick='" + getNick() + "'" + ", firstName='" + getFirstName() + "'"
                + ", lastName='" + getLastName() + "'" + ", email='" + getEmail() + "'" + ", password='" + getPassword()
                + "'" + ", enabled='" + isEnabled() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", updatedAt='"
                + getUpdatedAt() + "'" + "}";
    }

    public void updateParams(User json) {
        if (json.getNick() != null)
            this.setNick(json.getNick());
        if (json.getFirstName() != null)
            this.setFirstName(json.getFirstName());
        if (json.getLastName() != null)
            this.setLastName(json.getLastName());
        if (json.getEmail() != null)
            this.setEmail(json.getEmail());
        if (json.getPassword() != null)
            this.setPassword(json.getPassword());
        if (json.isEnabled() != null)
            this.setEnabled(json.isEnabled());
    }

}
