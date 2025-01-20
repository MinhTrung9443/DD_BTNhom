package com.example.MobileApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MobileApp.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	

}
