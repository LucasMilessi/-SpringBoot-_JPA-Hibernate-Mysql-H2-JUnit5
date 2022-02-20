package com.springboot.jpa_hibernate.controller;

import com.springboot.jpa_hibernate.model.Project;
import com.springboot.jpa_hibernate.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/apip")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/project/add")
    public ResponseEntity<Project> addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }

    @GetMapping("/project/all")
    public ResponseEntity<ArrayList<Project>> allProject(){
        return projectService.allProject();
    }

    @DeleteMapping(path = "/project/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") Long id){
       return projectService.deleteProject(id);
    }

    @PutMapping(path = "/project/update/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long id, @RequestBody Project project){
        return projectService.updateProject(id, project);
    }
}
