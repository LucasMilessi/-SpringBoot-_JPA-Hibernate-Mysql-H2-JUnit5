package com.springboot.jpa_hibernate.repositoryTest;

import com.springboot.jpa_hibernate.model.Employee;
import com.springboot.jpa_hibernate.model.Project;
import com.springboot.jpa_hibernate.model.Role;
import com.springboot.jpa_hibernate.repository.IEmployeeJpaRepository;
import com.springboot.jpa_hibernate.repository.IProjectJpaRepository;
import com.springboot.jpa_hibernate.repository.IRoleJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeJpaRepositoryTest {

    @Autowired
    private IEmployeeJpaRepository repositoryEmployee;

    @Autowired
    private IProjectJpaRepository repositoryProject;

    @Autowired
    private IRoleJpaRepository repositoryRole;

    @Test
    public void saveEmployee(){

        Role administrator = new Role("ROLE_ADMINISTRATOR");
        Role developer = new Role("ROLE_DEVELOPER");

        administrator = repositoryRole.save(administrator);
        developer = repositoryRole.save(developer);

        Project project1 = new Project("PROJECT 1");
        Project project2 = new Project("PROJECT 2");
        Project project3 = new Project("PROJECT 3");

        project1 = repositoryProject.save(project1);
        project2 = repositoryProject.save(project2);
        project3 = repositoryProject.save(project3);


        Employee jhon = new Employee("jhon", "Smith", "empl123", administrator);
        Employee lucas = new Employee("Lucas", "Milessi", "empl124", developer);

        jhon.getProjects().add(project1);
        jhon.getProjects().add(project2);
        jhon.getProjects().add(project3);

        lucas.getProjects().add(project2);
        lucas.getProjects().add(project3);

        repositoryEmployee.save(jhon);
        repositoryEmployee.save(lucas);

        repositoryEmployee.flush();

        Employee empl124 = repositoryEmployee.findByEmployeeid("empl124");
        assertEquals("Lucas", empl124.getFirstName());
        assertEquals(2, repositoryEmployee.findAll().size());
        assertEquals(developer, empl124.getRole());
    }
}
