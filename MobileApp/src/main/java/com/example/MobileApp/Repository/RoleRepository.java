package com.example.MobileApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MobileApp.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
