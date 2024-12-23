package com.example.course.data.vo.v1;

import com.example.course.model.Course;
import com.example.course.model.Creator;

import java.io.Serializable;
import java.util.Objects;

public class CourseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String content;
    private float duration;
    private Long creatorId;

    public static CourseVO fromCourse(Course course) {
        return new CourseVO(
                course.getId(),
                course.getName(),
                course.getContent(),
                course.getDuration(),
                course.getCreator().getId()
        );
    }

    public CourseVO(Long id, String name, String content, float duration, Long creatorId) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.duration = duration;
        this.creatorId = creatorId;
    }

    public CourseVO() {
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseVO courseVO = (CourseVO) o;
        return Float.compare(duration, courseVO.duration) == 0 && Objects.equals(id, courseVO.id) && Objects.equals(name, courseVO.name) && Objects.equals(content, courseVO.content) && Objects.equals(creatorId, courseVO.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, duration, creatorId);
    }
}