package com.springboot.jpa_hibernate.repository;

import com.springboot.jpa_hibernate.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeJpaRepository extends JpaRepository<Employee, Long> {

    Employee findByEmployeeid(String employeeid);
}
