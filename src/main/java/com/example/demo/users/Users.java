package com.example.demo.users;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {
	@Id
	@SequenceGenerator(
			name = "users_sequence",
			sequenceName = "users_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "users_sequence"
	)
	private Long id;
	
	private String name;
	
	private String email;

	@Transient
	private String field;

	public Users() {}
	
	public Users(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public Users(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Users{id=" + id + ", name=" + name + ", email=" + email + "}";
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) { this.id = id; }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getField() { return "field of " + this.name; }

	public void setField(String field) { this.field = field; }
}
