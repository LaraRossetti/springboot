package com.example.course.services;

import com.example.course.data.vo.v1.CourseVO;
import com.example.course.data.vo.v1.CreatorVO;
import com.example.course.exceptions.ResourceNotFoundException;
import com.example.course.model.Course;
import com.example.course.model.Creator;
import com.example.course.repositores.CourseRepository;
import com.example.course.repositores.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CreatorServices {

    private Logger logger = Logger.getLogger(CreatorServices.class.getName());

    @Autowired
    CreatorRepository creatorRepository;

    public List<CreatorVO> findAll() {

        logger.info("Procurando todos os creators.");

        var creatorEntities = creatorRepository.findAll();

        return creatorEntities
                .stream()
                .map(it -> new CreatorVO().fromCreator(it)).toList();
    }

    public CreatorVO findById(Long id) {

        logger.info("Procurando um creator pelo id.");

        var creatorEntity = creatorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum recurso encontrado para esse ID!"));

        return CreatorVO.fromCreator(creatorEntity);
    }


    public CreatorVO create(CreatorVO creator) {

        logger.info("Criando um creator.");

        Creator creatorEntity = new Creator();
        creatorEntity.setFirstName(creator.getFirstName());
        creatorEntity.setLastName(creator.getLastName());
        creatorEntity.setEmail(creator.getEmail());
        creatorEntity.setCategory(creator.getCategory());
        creatorEntity.setGender(creator.getGender());
        creatorRepository.save(creatorEntity);

        return CreatorVO.fromCreator(creatorEntity);

    }

    public CreatorVO update(CreatorVO creator) {

        logger.info("Atualizando um creator.");

        var creatorEntity = creatorRepository.findById(creator.getId()).orElseThrow(() -> new ResourceNotFoundException("Creator não encontrado para esse ID!"));

        creatorEntity.setId(creator.getId());
        creatorEntity.setFirstName(creator.getFirstName()); //seta as atualizações no entity
        creatorEntity.setLastName(creator.getLastName());
        creatorEntity.setEmail(creator.getEmail());
        creatorEntity.setCategory(creator.getCategory());
        creatorEntity.setGender(creator.getGender());
        creatorRepository.save(creatorEntity);

        return CreatorVO.fromCreator(creatorEntity);
    }

    public CreatorVO updateById(Long id, CreatorVO creator) {

        logger.info("Atualizando um creator pelo id.");

        var creatorEntity = creatorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Creator não encontrado para esse ID!"));

        creatorEntity.setId(id);
        creatorEntity.setFirstName(creator.getFirstName()); //seta as atualizações no entity
        creatorEntity.setLastName(creator.getLastName());
        creatorEntity.setEmail(creator.getEmail());
        creatorEntity.setCategory(creator.getCategory());
        creatorEntity.setGender(creator.getGender());
        creatorRepository.save(creatorEntity);

        return CreatorVO.fromCreator(creatorEntity);
    }

    public void delete(Long id) {

        logger.info("Deletando um creator pelo id.");

        var entity = creatorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum recurso encontrado para esse ID!"));
        creatorRepository.delete(entity);
    }
}

