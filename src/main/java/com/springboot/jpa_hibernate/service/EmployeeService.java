package com.springboot.jpa_hibernate.service;

import com.springboot.jpa_hibernate.model.Employee;
import com.springboot.jpa_hibernate.model.Project;
import com.springboot.jpa_hibernate.repository.IEmployeeJpaRepository;
import com.springboot.jpa_hibernate.repository.IProjectJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    IEmployeeJpaRepository employeeRepository;

    @Autowired
    IProjectJpaRepository projectRepository;

    public ResponseEntity<Employee> addEmployee(Employee employee){
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
    }

    public ResponseEntity<ArrayList<Employee>> getAllEmployee(){
        return new ResponseEntity<>((ArrayList<Employee>) employeeRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteEmployee(Long id){
        try {
            if (employeeRepository.existsById(id)){
                employeeRepository.deleteById(id);
                return new ResponseEntity<>("El employee con id: "+id+" se elimino.",HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("El employee con id: "+id+" no existe.",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<Employee> updateEmployee(Long id, Employee employee){
        Optional<Employee> employeeUpdate = employeeRepository.findById(id);

        if(employeeUpdate.isPresent()){
            Employee employeeSave = employeeUpdate.get();
            employeeSave.setFirstName(employee.getFirstName());
            employeeSave.setLastName(employee.getLastName());
            employeeSave.setEmployeeid(employee.getEmployeeid());
            employeeSave.setRole(employee.getRole());
            return new ResponseEntity<>(employeeRepository.save(employeeSave), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> asignProjectToEmployee(Long idEmployee, Long idProject){
        try {
            Optional<Employee> employee = employeeRepository.findById(idEmployee);
            Optional<Project> project = projectRepository.findById(idProject);

            Employee employeeSave = employee.get();
            Project projectSave = project.get();

            if (employee.isPresent() && project.isPresent()){

                employeeSave.getProjects().add(projectSave);
                employeeRepository.save(employeeSave);

                return new ResponseEntity<>("Se agrego el project con nombre: "+projectSave.getName()+
                        " a el employee "+employeeSave.getFirstName()+" id: "+ employeeSave.getId(), HttpStatus.OK);
            }
            return  new ResponseEntity<>("El employee con id: "+idEmployee+", o el project con id: "+idProject+
                    " no existe.", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }


    }
}
