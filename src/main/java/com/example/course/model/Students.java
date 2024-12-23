package com.example.course.model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;

@Entity
@Table (name = "students")
public class Students implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 50)
    private String firstName;

    @Column (nullable = false, length = 50)
    private String lastName;

    @Column (nullable = false, length = 50)
    private String email;

    @Column (nullable = false, length = 50)
    private String phone;

    @Column (nullable = false, length = 100)
    private String address;

    @ManyToMany
    @JoinTable()


    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
