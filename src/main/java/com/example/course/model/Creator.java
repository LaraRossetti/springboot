package com.example.course.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "creator")
public class Creator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 80)
    private String firstName;

    @Column (nullable = false, length = 80)
    private String lastName;

    @Column (nullable = false, length = 80)
    private String email;

    @Column (nullable = false, length = 50)
    private String category;

    @Column (nullable = false, length = 10)
    private String gender;

    @OneToMany (mappedBy = "creator")
    @JsonManagedReference
    private List<Course> courses;

    public Creator() {

    }

    public Creator(Long id, String firstName, String lastName, String email, String category, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.category = category;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Creator creator = (Creator) o;
        return Objects.equals(id, creator.id) && Objects.equals(firstName, creator.firstName) && Objects.equals(lastName, creator.lastName) && Objects.equals(email, creator.email) && Objects.equals(category, creator.category) && Objects.equals(gender, creator.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, category, gender);
    }
}
