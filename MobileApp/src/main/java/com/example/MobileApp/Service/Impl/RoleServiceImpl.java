package com.example.MobileApp.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MobileApp.Entity.Role;
import com.example.MobileApp.Repository.RoleRepository;
import com.example.MobileApp.Service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Optional<Role> findById(Integer id) {
		return roleRepository.findById(id);
	}
}
