package com.example.demo.endpoints.users;


import com.example.demo.endpoints.role.Role;
import com.example.demo.endpoints.role.RoleRepository;
import com.example.demo.endpoints.users.dto.CreateUserDTO;
import com.example.demo.endpoints.users.dto.UpdateUserDTO;
import com.example.demo.guard.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service // the same @Component
public class UsersService {
	private final UsersRepository usersRepository;
	private final RoleRepository roleRepository;
	private final JwtService jwtService;

	@Autowired
	public UsersService(UsersRepository usersRepository, RoleRepository roleRepository, JwtService jwtService) {
		this.usersRepository = usersRepository;
		this.roleRepository = roleRepository;
		this.jwtService = jwtService;
	}

	public ResponseEntity<Users> getByLogin(String login) {
		Users candidate = usersRepository.findUserByEmail(login).orElse(null);

		if (candidate == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);}

		return new ResponseEntity<>(candidate, HttpStatus.OK);
	}

	public ResponseEntity<List<Users>> getAll() {
		return new ResponseEntity<>(this.usersRepository.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<Users> create(CreateUserDTO dto) {
		Optional<Users> candidate = this.usersRepository.findUserByEmail(dto.getEmail());

		if (candidate.isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		Role role = roleRepository.findRoleByValue("USER").orElse(new Role(7777777777L,"USER-COMMON", "AVERAGE"));

		Users user = new Users(dto.getPassword(), dto.getEmail());

		user.setRoles(List.of(role));

		return new ResponseEntity<>(this.usersRepository.save(user), HttpStatus.CREATED);
	}

	@Transactional
	public ResponseEntity<String> update(UpdateUserDTO dto) {
		Users entity = usersRepository.findById(dto.getId()).orElse(null);

		if (entity == null) {
			return new ResponseEntity<>("Entity does not exist", HttpStatusCode.valueOf(404));
		}

		boolean isUpdated = false;

		if (
			dto.getPassword() != null && ! dto.getPassword().isEmpty()
		) {
			/* Hash */
			entity.setPassword(dto.getPassword());
			isUpdated = true;
		}

		if (
			dto.getEmail() != null && ! dto.getEmail().isEmpty()
			&& ! Objects.equals(entity.getEmail(), dto.getEmail())
		) {
			Optional<Users> userCandidate = usersRepository.findUserByEmail(dto.getEmail());

			if (userCandidate.isPresent()) {
				return new ResponseEntity<>("Login/email is already taken", HttpStatusCode.valueOf(400));
			}

			entity.setEmail(dto.getEmail());
			isUpdated = true;
		}

		if (isUpdated) {
			return new ResponseEntity<>("Successfully Updated", HttpStatusCode.valueOf(200));
		}

		return new ResponseEntity<>("There is nothing to update", HttpStatusCode.valueOf(400));
	}

	public ResponseEntity<String> delete(Long id) {
		boolean isCandidateExist = usersRepository.existsById(id);

		if (! isCandidateExist) {
			return new ResponseEntity<>("Entity does not exist", HttpStatusCode.valueOf(404));
		}

		usersRepository.deleteById(id);

		return new ResponseEntity<>("Removed", HttpStatusCode.valueOf(200));
	}

	public boolean check(HttpServletRequest req) {
		final String authHeader = req.getHeader("Authorization");

		if (authHeader == null || ! authHeader.startsWith("Bearer ")) {
			return false;
		}

		final String token = authHeader.substring(7);

		final String email = jwtService.extractUsername(token);

		final Users user = usersRepository.findUserByEmail(email).orElse(null);

		if (user == null) {
			return  false;
		}

		return jwtService.isTokenValid(token, user);
	}

	public ResponseEntity<Users> assignRole(Long id, String roleName) {
		Users users = this.usersRepository.findById(id).orElse(null);

		if (users == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Role role = roleRepository.findRoleByValue(roleName).orElse(null);

		if (role == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}


		/* validation IS_ROLE_ALREADY_EXIST */
		List<Role> roles = users.getRoles();

		boolean isRoleAlreadyExist = false;

		for (Role r : roles) {
			if (Objects.equals(r.getValue(), role.getValue())) {
				isRoleAlreadyExist = true;
			}
		}

		if (isRoleAlreadyExist) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


		roles.add(role);
		users.setRoles(roles);

		// Next line will insert on USERS and ROLE_USERS tables
		users = usersRepository.saveAndFlush(users);

		List<Users> usersList = role.getUsers();
		usersList.add(users);

		// We add the Users to the Role object to keep both sides in sync
		role.setUsers(usersList); // should add the user (not override)

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	public ResponseEntity<Users> reAssignRole(Long id, String roleName) {
		Users users = this.usersRepository.findById(id).orElse(null);

		if (users == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Role role = roleRepository.findRoleByValue(roleName).orElse(null);

		if (role == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}


		/* validation IS_ROLE_ALREADY_EXIST */
		List<Role> roles = users.getRoles();

		boolean isRoleAlreadyExist = false;

		for (Role r : roles) {
			if (Objects.equals(r.getValue(), role.getValue())) {
				isRoleAlreadyExist = true;
			}
		}

		if (isRoleAlreadyExist) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

//	@Transactional
	public ResponseEntity<Users> ban(Long id, String reason) {
		Users users = this.usersRepository.findById(id).orElse(null);

		if (users == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		users.setBanned(true);
		users.setBanReason(reason);

		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
