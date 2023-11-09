package com.example.demo.users;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
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
	@Schema(example = "1", description = "User ID")
	private Long id;

	@Schema(example = "pass-1-asa", description = "User Password")
	private String password;

	@Schema(example = "a@a.a", description = "Users email")
	private String email;

	@Schema(example = "a@a.a - pass-1-asa", description = "Processing")
	@Transient
	private String field;

	public Users() {}
	
	public Users(String password, String email) {
		this.password = password;
		this.email = email;
	}
	
	public Users(Long id, String password, String email) {
		this.id = id;
		this.password = password;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Users{id=" + id + ", password=" + password + ", email=" + email + "}";
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) { this.id = id; }
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getField() { return "field of " + this.password; }

	public void setField(String field) { this.field = field; }
}
