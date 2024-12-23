package com.example.course.services;

import com.example.course.data.vo.v1.CourseVO;
import com.example.course.exceptions.ResourceNotFoundException;
import com.example.course.model.Course;
import com.example.course.model.Creator;
import com.example.course.repositores.CourseRepository;
import com.example.course.repositores.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CourseServices {

    private Logger logger = Logger.getLogger(CourseServices.class.getName());

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private CreatorRepository creatorRepository;

    public List<CourseVO> findAll() {

        logger.info("Procurando todos os cursos.");
        var courseEntities = courseRepository.findAll();
        return courseEntities.stream().map(CourseVO::fromCourse).toList();
    }

    public CourseVO findById(Long id) {

        logger.info("Procurando um curso pelo id: " + id);

        var courseEntity =  courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum recurso encontrado para esse ID!"));

        return CourseVO.fromCourse(courseEntity);
    }

    public CourseVO create(CourseVO course){

        logger.info("Criando um curso com o id: " + course.getId());

        var creator = creatorRepository.findById(course.getCreatorId()).orElseThrow(() -> new ResourceNotFoundException("Creator não encontrado para esse ID!"));

        //colocar um if para ver se já tem o nome
        validateDuplicatedName(creator, course);

        Course courseEntity = new Course();
        courseEntity.setName(course.getName());
        courseEntity.setContent(course.getContent());
        courseEntity.setDuration(course.getDuration());
        courseEntity.setCreator(creator);   //seta o creator inteiro
        courseRepository.save(courseEntity);

        return CourseVO.fromCourse(courseEntity);
    }


    private void validateDuplicatedName(Creator creator, CourseVO course){
        if (creator.getCourses().stream().anyMatch(it -> it.getName().equals(course.getName()))) {
            throw new RuntimeException(new Exception("Nome duplicado"));
        }
    }

    public CourseVO update(CourseVO course) {

        logger.info("Atualizando um curso com o id: " + course.getId());

        var courseEntity = courseRepository.findById(course.getId()).orElseThrow(() -> new ResourceNotFoundException("Nenhum recurso encontrado para esse ID!"));  //recupera o curso pelo id e coloca na variável entity

        if(!courseEntity.getCreator().getId().equals(course.getCreatorId())){
            throw new RuntimeException(new Exception("IDs dos cursos são diferentes!"));
        }

        if(!courseEntity.getName().equals(course.getName())){
            courseEntity.setName(course.getName());
        }
        if(!courseEntity.getContent().equals(course.getContent())){
            courseEntity.setContent(course.getContent());
        }
        if(courseEntity.getDuration() != course.getDuration()){
            courseEntity.setDuration(course.getDuration());
        }

        courseRepository.save(courseEntity);

        return CourseVO.fromCourse(courseEntity);
    }

    public CourseVO updateById(Long id, CourseVO course) {

        logger.info("Atualizando um curso pelo id: " + id);

        var courseEntity = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum recurso encontrado para esse ID!"));  //recupera o curso pelo id e coloca na variável entity

        courseEntity.setName(course.getName());  //seta as atualizações no entity
        courseEntity.setContent(course.getContent());
        courseEntity.setDuration(course.getDuration());
        courseRepository.save(courseEntity);

        return CourseVO.fromCourse(courseEntity);
    }

    public void delete(Long id) {

        logger.info("Deletando um curso.");

        var entity = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum recurso encontrado para esse ID!"));
        courseRepository.delete(entity);
    }

    public List<CourseVO> listCoursesByCreatorId(Long creatorId) {
        logger.info("Listando todos os cursos pelo id do creator: " + creatorId);

        List<Course> courseList = courseRepository.findAllByCreatorId(creatorId);
        return courseList.stream().map(it -> new CourseVO().fromCourse(it)).toList();
    }

    //listar em ordem alfabética
    public List<CourseVO> listCoursesByAlphabeticOrder() {
        logger.info("Listando todos os cursos em ordem alfabética");

        List<Course> courseList = courseRepository.findAll();

        List<String> coursesNamesList = new ArrayList<>(courseList.stream().map(Course::getName).toList());

        Collections.sort(coursesNamesList);
        courseList.sort((c1, c2) -> {
            int index1 = coursesNamesList.indexOf(c1.getName());
            int index2 = coursesNamesList.indexOf(c2.getName());
            return Integer.compare(index1, index2);
        });


        //courseList.sort((Course c1, Course c2) -> c1.getName().compareTo(c2.getName())); //-> compareTo compara duas strings em ordem alfabética

       return courseList.stream().map(it -> new CourseVO().fromCourse(it)).toList();

    }



}

