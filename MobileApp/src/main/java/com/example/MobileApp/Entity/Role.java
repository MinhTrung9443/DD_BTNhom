package com.example.MobileApp.Entity;


import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7809009003865164428L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	private String roleName;
}
