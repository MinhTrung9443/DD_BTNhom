package com.example.MobileApp.Entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5740653891798730048L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "nvarchar(150)")
	private String fullname;
	private String phone;
	@Column(columnDefinition = "nvarchar(max)")
	private String address;
	private int gender;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accountId")
	private Account account;
}
