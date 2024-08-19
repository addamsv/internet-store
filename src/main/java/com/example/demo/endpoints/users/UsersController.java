package com.example.demo.endpoints.users;

import java.util.List;

import com.example.demo.endpoints.users.dto.CreateUserDTO;
import com.example.demo.endpoints.users.dto.UpdateUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users")
@RestController
@RequestMapping("api/v1/users")
@CrossOrigin("*")
@SecurityRequirement(name = "BearerAuth")
public class UsersController {
	private final UsersService usersService;

	@Autowired
	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}


	@GetMapping(path = "/check")
	@Operation(description = "check User By jwt", summary = "check User By jwt",
	responses = {
		@ApiResponse(description = "Success", responseCode = "200", useReturnTypeSchema = true)
	})
	public boolean check(HttpServletRequest req) {
		return usersService.check(req);
	}


	@GetMapping(path = "/{login}")
	@Operation(description = "GET User By Login/email", summary = "Get User By Login/email",
	responses = {
		@ApiResponse(description = "Success", responseCode = "200", useReturnTypeSchema = true)
	})
	public ResponseEntity<Users> getByLogin(@PathVariable("login") String login) {
		return usersService.getByLogin(login);
	}


	@GetMapping
	@Operation(description = "Get endpoint", summary = "Get all users",
	responses = {
		@ApiResponse(description = "Success", responseCode = "200", useReturnTypeSchema = true)
	})
	public ResponseEntity<List<Users>> getAll() {
		return usersService.getAll();
	}


	@PostMapping(path = "/assign-role/{userId}", params = "roleName")
	@Operation(description = "ASSIGN ROLE to User", summary = "ASSIGN ROLE to User",
	responses = {
		@ApiResponse(description = "Success", responseCode = "201", useReturnTypeSchema = true)
	})
	public ResponseEntity<Users> assignRole(@PathVariable("userId") Long id, @RequestParam("roleName") String roleName) {
		return usersService.assignRole(id, roleName);
	}


	@PostMapping(path = "/reassign-role/{userId}", params = "roleName")
	@Operation(description = "REASSIGN ROLE to User", summary = "REASSIGN ROLE to User",
	responses = {
		@ApiResponse(description = "Success", responseCode = "201", useReturnTypeSchema = true)
	})
	public ResponseEntity<Users> reAssignRole(@PathVariable("userId") Long id, @RequestParam("roleName") String roleName) {
		return usersService.reAssignRole(id, roleName);
	}


	@PostMapping(path = "/ban/{userId}", params = "banReason")
	@Operation(description = "BAN a User", summary = "BAN a User",
	responses = {
		@ApiResponse(description = "Success", responseCode = "201", useReturnTypeSchema = true)
	})
	public ResponseEntity<Users> ban(@PathVariable("userId") Long id, @RequestParam("banReason") String reason) {
		return usersService.ban(id, reason);
	}


	@PostMapping
	@Operation(description = "CREATE New User", summary = "CREATE New User",
	responses = {
		@ApiResponse(description = "Success", responseCode = "201", useReturnTypeSchema = true)
	})
	public ResponseEntity<Users> create(@Valid @RequestBody CreateUserDTO dto) {
		return usersService.create(dto);
	}


	@PutMapping // (path = "{userId}")
	@Operation(description = "UPDATE Users Login", summary = "Update User")
	public ResponseEntity<String> update(@RequestBody UpdateUserDTO dto
//			@PathVariable("userId") Long id,
//			@RequestParam(required = false) String password,
//			@RequestParam(required = false) String email
	) {
		return usersService.update(dto); // id, password, email, role
	}


	@DeleteMapping(path = "{userId}")
	@Operation(description = "Delete", summary = "Remove user")
	public void delete(@PathVariable("userId") Long id) {
		usersService.delete(id);
	}

}
