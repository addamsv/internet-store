package com.example.demo.endpoints.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "users")
public class Users implements UserDetails {
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

	@Schema(example = "ADMIN", description = "Users role")
	private String role;

	@Schema(example = "a@a.a - pass-1-asa", description = "Processing")
	@Transient
	private String field;

	public Users() {}

	public Users(String password, String email) {
		this.password = password;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Users{" +
				"id=" + id +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", role='" + role + '\'' +
				", field='" + field + '\'' +
				'}';
	}

	public Users(Long id, String password, String email) {
		this.id = id;
		this.password = password;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) { this.id = id; }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
