package com.bilas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilas.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
