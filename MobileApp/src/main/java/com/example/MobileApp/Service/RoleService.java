package com.example.MobileApp.Service;

import java.util.Optional;

import com.example.MobileApp.Entity.Role;

public interface RoleService {

	Optional<Role> findById(Integer id);

}
