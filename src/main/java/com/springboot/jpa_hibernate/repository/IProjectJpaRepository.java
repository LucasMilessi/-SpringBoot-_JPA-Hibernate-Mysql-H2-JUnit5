package com.springboot.jpa_hibernate.repository;

import com.springboot.jpa_hibernate.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectJpaRepository extends JpaRepository<Project, Long> {
}
