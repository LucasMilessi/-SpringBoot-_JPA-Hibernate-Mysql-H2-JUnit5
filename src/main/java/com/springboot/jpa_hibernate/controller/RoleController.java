package com.springboot.jpa_hibernate.controller;

import com.springboot.jpa_hibernate.model.Project;
import com.springboot.jpa_hibernate.model.Role;
import com.springboot.jpa_hibernate.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/apir")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/role/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @GetMapping("/role/all")
    public ResponseEntity<ArrayList<Role>> allRole(){
        return roleService.allRole();
    }

    @DeleteMapping(path = "/role/delete/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id){
        return roleService.deleteRole(id);
    }

    @PutMapping(path = "/role/update/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") Long id, @RequestBody Role role){
        return roleService.updateRole(id, role);
    }

    @GetMapping(path = ("/role/getById/{id}"))
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id){
        return roleService.getRoleById(id);
    }

}
