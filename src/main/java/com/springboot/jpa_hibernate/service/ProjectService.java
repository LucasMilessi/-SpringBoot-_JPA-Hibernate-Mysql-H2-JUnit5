package com.springboot.jpa_hibernate.service;

import com.springboot.jpa_hibernate.model.Project;
import com.springboot.jpa_hibernate.repository.IProjectJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    IProjectJpaRepository projectRepository;

    public ResponseEntity<Project> addProject(Project project){
        try {
            return new ResponseEntity<>(projectRepository.save(project), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    public ResponseEntity<ArrayList<Project>> allProject(){
        return new ResponseEntity<>((ArrayList<Project>) projectRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteProject(Long id){
        try {
            if(projectRepository.existsById(id)){
                projectRepository.deleteById(id);
                return new ResponseEntity<>("El project con id: "+id+" se elimino.",HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("El project con id: "+id+" no existe.",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<Project> updateProject(Long id, Project project){
        Optional<Project> projectUpdate = projectRepository.findById(id);

        if(projectUpdate.isPresent()){
            Project projectSave = projectUpdate.get();
            projectSave.setName(project.getName());
            return new ResponseEntity<>(projectRepository.save(projectSave), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
