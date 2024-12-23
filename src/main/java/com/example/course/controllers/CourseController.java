package com.example.course.controllers;

import com.example.course.data.vo.v1.CourseVO;
import com.example.course.model.Course;
import com.example.course.services.CourseServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course2")
public class CourseController {

    private final CourseServices courseServices;

    public CourseController(CourseServices courseServices) {
        this.courseServices = courseServices;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseVO findById(@PathVariable Long id){
        return courseServices.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseVO> findAll(){
        return courseServices.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseVO create(@RequestBody CourseVO course){
        return courseServices.create(course);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseVO update(@RequestBody CourseVO course){
        return courseServices.update(course);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseVO updateById(@PathVariable Long id, @RequestBody CourseVO course){
        return courseServices.updateById(id, course);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        courseServices.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping (value = "/creator/{id}")
    public List<CourseVO> findByCreator(@PathVariable Long id){
        return courseServices.listCoursesByCreatorId(id);
    }

    @GetMapping(value = "/ordem")
    public List<CourseVO> findByOrdem(){
        return courseServices.listCoursesByAlphabeticOrder();
    }
}
