package com.springboot.jpa_hibernate.repository;

import com.springboot.jpa_hibernate.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleJpaRepository extends JpaRepository<Role, Long> {
}
