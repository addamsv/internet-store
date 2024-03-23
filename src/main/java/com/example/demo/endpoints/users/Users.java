package com.example.demo.endpoints.users;

import com.example.demo.endpoints.address.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "users")
public class Users implements UserDetails {
	@Id
	@SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
	@Schema(example = "1", description = "User ID")
	private Long id;

	@Schema(example = "pass-1-asa", description = "User Password")
	@Column(name = "password", nullable = false)
	private String password;

	@Schema(example = "a@a.a", description = "Users email")
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Schema(example = "ADMIN", description = "Users role")
	@Column(name = "role", nullable = false)
	private String role = "ADMIN";




	/* One-to-one or unidirectional rel-p */
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;





	@Override
	public String toString() {
		return "Users{" +
				"id=" + id +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", role='" + role + '\'' +
				", address=" + address +
				'}';
	}

	public Users() {}

	public Users(String email, String password, String role) {
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public Users(Long id, String email, String password, String role) {
		this.id = id;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) { this.id = id; }

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
