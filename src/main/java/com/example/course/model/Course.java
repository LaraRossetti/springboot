package com.example.course.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table (name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 80)
    private String name;

    @Column (nullable = false, length = 100)
    private String content;

    @Column (nullable = false)
    private float duration;

    @ManyToOne
    @JoinColumn (name = "creator_id", nullable = false)
    @JsonBackReference
    private Creator creator;

    public Course() {

    }

    public Course(Long id, String name, String content, float duration) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.duration = duration;
    }

    public Course(Long id, String name, String content, float duration, Creator creator) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.duration = duration;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public float getDuration() {
        return duration;
    }
    public void setDuration(float duration) {
        this.duration = duration;
    }
    public Creator getCreator() {
        return creator;
    }
    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(name, course.name) && Objects.equals(content, course.content) && Objects.equals(duration, course.duration) && Objects.equals(creator, course.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, duration, creator);
    }
}