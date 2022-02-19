package com.springboot.jpa_hibernate.repositoryTest;

import com.springboot.jpa_hibernate.model.Employee;
import com.springboot.jpa_hibernate.repository.IEmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeJpaRepositoryTest {

    @Autowired
    private IEmployeeJpaRepository repositoryEmployee;

    public void saveEmployee(){

        Employee jhon = new Employee("jhon", "Smith", "empl123");
        Employee lucas = new Employee("Lucas", "Milessi", "empl124");

        repositoryEmployee.save(jhon);
        repositoryEmployee.save(lucas);

        repositoryEmployee.flush();

        assertEquals(2, repositoryEmployee.findAll().size());
    }
}
