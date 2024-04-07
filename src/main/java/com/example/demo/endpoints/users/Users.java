package com.example.demo.endpoints.users;

import com.example.demo.endpoints.address.Address;
import com.example.demo.endpoints.role.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	@Schema(example = "false", description = "Is User Activated")
	@Column(name = "is_activated", nullable = false)
	private Boolean isActivated = false;

	@Schema(example = "xcvcbv-asdb-nmhj-kasd-fgdfg-yuty", description = "Users Activation Link")
	@Column(name = "activation_link", nullable = false)
	private String activationLink = "";

	@Schema(example = "xcvcbvasdb.nmhjkasd.fgdfgyuty", description = "Users Refresh Token")
	@Column(name = "refresh_token", nullable = false, columnDefinition="text")
	private String refreshToken = "";

	@Schema(example = "false", description = "Is User Banned")
	@Column(name = "is_banned", nullable = false)
	private Boolean isBanned = false;

	@Schema(example = "For Cheating", description = "Reason of Ban of the User")
	@Column(name = "ban_reason", nullable = true)
	private String banReason;


	/* Owner */
	@ManyToMany(fetch = FetchType.EAGER)
	/* in order to avoid a circular calls */
	@JsonBackReference
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "users_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<Role> roles;



	/* One-to-one or unidirectional rel-p */
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;


	public Users() {}

	public Users(Long id) {
		this.id = id;
	}

	public Users(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Users(Long id, String email, String password, String activationLink) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) { this.id = id; }


	public Boolean getActivated() {
		return isActivated;
	}

	public void setActivated(Boolean activated) {
		isActivated = activated;
	}

	public String getActivationLink() {
		return activationLink;
	}

	public void setActivationLink(String activationLink) {
		this.activationLink = activationLink;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getBanned() {
		return isBanned;
	}

	public void setBanned(Boolean banned) {
		isBanned = banned;
	}

	public String getBanReason() {
		return banReason;
	}

	public void setBanReason(String banReason) {
		this.banReason = banReason;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}





	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();

		for (Role role : roles) {
			list.add(new SimpleGrantedAuthority("ROLE_" + role.getValue()));
		}

		return list;
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

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		return "Users{" +
				"id=" + id +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", isActivated=" + isActivated +
				", activationLink='" + activationLink + '\'' +
				", refreshToken='" + refreshToken + '\'' +
				", isBanned=" + isBanned +
				", banReason='" + banReason + '\'' +
				", roles=" + roles +
				", address=" + address +
				'}';
	}
}
