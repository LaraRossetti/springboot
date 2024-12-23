package com.example.course.services;

import com.example.course.data.vo.v1.CourseVO;
import com.example.course.model.Course;
import com.example.course.model.Creator;
import com.example.course.repositores.CourseRepository;
import com.example.course.repositores.CreatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CourseServicesTest {


    @Mock
    CourseRepository courseRepository;
    @Mock
    private CreatorRepository creatorRepository;

    @InjectMocks
    CourseServices courseServices;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void listCoursesByAlphabeticOrder() throws Exception{
        Course course1 = new Course(1L, "Java", "Java", 40, createCreator());
        Course course2 = new Course(2L, "Python", "Python", 40, createCreator());
        Course course3 = new Course(3L, "C#", "C#", 40, createCreator());
        List<Course> courseList = new ArrayList<>();

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        when(courseRepository.findAll()).thenReturn(courseList);

        List<CourseVO> result = courseServices.listCoursesByAlphabeticOrder();

        assertEquals("C#", result.get(0).getName());
        assertEquals("Java", result.get(1).getName());
        assertEquals("Python", result.get(2).getName());
    }

    private Creator createCreator(){
        Creator creator1 = new Creator();
        creator1.setId(1L);
        creator1.setFirstName("Lara");
        creator1.setLastName("Rossetti");
        creator1.setEmail("lara.rossetti@gmail.com");
        creator1.setCategory("Technology");
        creator1.setGender("Female");
        return creator1;
    }


    @Test
    void createCourseShouldRuntimeExceptionWhenNameIsDuplicated(){

        Creator creator = createCreator();
        Course course = new Course(1L, "Java", "Java Content", 40, creator);
        creator.setCourses(List.of(course));

        when(creatorRepository.findById(1L)).thenReturn(Optional.of(creator));

        CourseVO courseVO = new CourseVO();
        courseVO.setName("Java");
        courseVO.setContent("Java Content");
        courseVO.setId(1L);
        courseVO.setDuration(30);
        courseVO.setCreatorId(1L);

        Exception exception = assertThrows(RuntimeException.class, () -> {courseServices.create(courseVO);});

        String expectedMessage = "Nome duplicado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void updateCourseWhenNameIsChanged(){
        Creator creator = createCreator();
        Course course = new Course(1L, "Java", "Java Content", 40, creator);
        creator.setCourses(List.of(course));

        when(creatorRepository.findById(1L)).thenReturn(Optional.of(creator));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        CourseVO courseVO = new CourseVO();
        courseVO.setId(1L);
        courseVO.setName("Javascript");

        CourseVO updatedCourse = courseServices.update(courseVO);
        assertEquals("Javascript", updatedCourse.getName());
    }

    @Test
    void updateCourseWhenContentIsChanged(){
        Creator creator = createCreator();
        Course course = new Course(1L, "Java", "Java Content", 40, creator);
        creator.setCourses(List.of(course));

        when(creatorRepository.findById(1L)).thenReturn(Optional.of(creator));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        CourseVO courseVO = new CourseVO();
        courseVO.setId(1L);
        courseVO.setContent("Java for Beginners");
        courseVO.setCreatorId(1L);

        CourseVO updatedCourse = courseServices.update(courseVO);
        assertEquals("Java for Beginners", updatedCourse.getContent());
    }

    @Test
    void updateCourseWhenDurationIsChanged(){
        Creator creator = createCreator();
        Course course = new Course(1L, "Java", "Java Content", 40, creator);
        creator.setCourses(List.of(course));

        when(creatorRepository.findById(1L)).thenReturn(Optional.of(creator));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        CourseVO courseVO = new CourseVO();
        courseVO.setId(1L);
        courseVO.setDuration(30);

        CourseVO updatedCourse = courseServices.update(courseVO);
        assertEquals(30, updatedCourse.getDuration());
    }

}