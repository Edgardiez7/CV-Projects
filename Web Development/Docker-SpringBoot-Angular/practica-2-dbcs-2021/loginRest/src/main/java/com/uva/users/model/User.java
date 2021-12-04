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
    @Column(unique = true, length = 50, nullable = false)
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

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
