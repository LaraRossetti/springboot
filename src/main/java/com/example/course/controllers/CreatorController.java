package com.example.course.controllers;

import com.example.course.data.vo.v1.CreatorVO;
import com.example.course.services.CreatorServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creator")
public class CreatorController {

    private final CreatorServices creatorServices;

    public CreatorController(CreatorServices creatorServices) {
        this.creatorServices = creatorServices;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CreatorVO findById(@PathVariable Long id){
        return creatorServices.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CreatorVO> findAll(){
        return creatorServices.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreatorVO create(@RequestBody CreatorVO creator){
        return creatorServices.create(creator);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreatorVO update(@RequestBody CreatorVO creator){
        return creatorServices.update(creator);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreatorVO updateById(@PathVariable Long id, @RequestBody CreatorVO creator){
        return creatorServices.updateById(id, creator);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        creatorServices.delete(id);

        return ResponseEntity.noContent().build();
    }

//    @GetMapping(value = "/courses/{id}")
//    public List<CourseVO> findCoursesByCreator(@PathVariable Long id){
//        return creatorServices.listCoursesById(id);
//    }
}
