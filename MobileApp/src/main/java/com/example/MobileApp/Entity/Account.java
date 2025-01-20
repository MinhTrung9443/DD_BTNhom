package com.example.MobileApp.Entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1045283451506266552L;
	@Id
	private int accountId;
	// thêm username
	private String username;
	private String email;
	private String password;
	
	@ManyToOne()
	@JoinColumn(name = "roleId")
	private Role role;
}
