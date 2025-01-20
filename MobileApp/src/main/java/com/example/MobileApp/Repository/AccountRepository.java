package com.example.MobileApp.Repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.MobileApp.Entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	@Query("SELECT u FROM User u WHERE u.username = :username")
	Optional<Account> findByUsername(@Param("username") String username);
}
