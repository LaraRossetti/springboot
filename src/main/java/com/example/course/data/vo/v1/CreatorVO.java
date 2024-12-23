package com.example.course.data.vo.v1;

import com.example.course.model.Creator;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CreatorVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String category;
    private String gender;
    private List<CourseVO> coursesList;

    public static CreatorVO fromCreator(Creator creator) {
        return new CreatorVO(
                creator.getId(),
                creator.getFirstName(),
                creator.getLastName(),
                creator.getEmail(),
                creator.getCategory(),
                creator.getGender()
        );
    }

    public CreatorVO() {
    }

    public CreatorVO(Long id, String firstName, String lastName, String email, String category, String gender) {
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
    public List<CourseVO> getCoursesList() {
        return coursesList;
    }
    public void setCoursesList(List<CourseVO> coursesList) {
        this.coursesList = coursesList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatorVO creatorVO = (CreatorVO) o;
        return Objects.equals(id, creatorVO.id) && Objects.equals(firstName, creatorVO.firstName) && Objects.equals(lastName, creatorVO.lastName) && Objects.equals(email, creatorVO.email) && Objects.equals(category, creatorVO.category) && Objects.equals(gender, creatorVO.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, category, gender);
    }
}


