package com.springboot.jpa_hibernate.service;

import com.springboot.jpa_hibernate.model.Project;
import com.springboot.jpa_hibernate.model.Role;
import com.springboot.jpa_hibernate.repository.IRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    IRoleJpaRepository roleRepository;

    public ResponseEntity<Role> addRole(Role role){
        try {
            return new ResponseEntity<>(roleRepository.save(role), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<ArrayList<Role>> allRole(){
        return new ResponseEntity<> ((ArrayList<Role>) roleRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteRole(Long id){
        try {
            if (roleRepository.existsById(id)){ //Si el id existe en la BD, entonces entra al if
                roleRepository.deleteById(id);
                return new ResponseEntity<>("El Role con id: "+id+" se elimino.",HttpStatus.OK);
            }
            return new ResponseEntity<>("El Role con id: "+id+" no existe.", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<Role> updateRole(Long id, Role role){
        Optional<Role> roleUpdate = roleRepository.findById(id);

        if(roleUpdate.isPresent()){
            Role roleSave = roleUpdate.get();
            roleSave.setName(role.getName());
            return new ResponseEntity<>(roleRepository.save(roleSave), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Role> getRoleById(Long id){
        try {
            Optional<Role> roleById = roleRepository.findById(id);

            if(roleById.isPresent()){ //Si el id de role que le pasamos por parametro se encuentra en nustra BD entra.
                return new ResponseEntity<>(roleById.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
