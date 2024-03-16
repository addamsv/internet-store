package com.example.demo.endpoints.users;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping
	@Operation(
			description = "Get endpoint",
			summary = "Get all users",
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200",
							useReturnTypeSchema = true
					)
			}
	)
	public List<Users> getUsers() {
		return usersService.getUsers();
	}

	@PostMapping
	@Operation(
			description = "Post",
			summary = "Create user"
	)
	public Users addUser(@RequestBody Users user) {
		return usersService.addUser(user);
	}

	@DeleteMapping(path = "{userId}")
	@Operation(
			description = "Delete",
			summary = "Remove user"
	)
	public void dellUser(@PathVariable("userId") Long id) {
		usersService.dellUser(id);
	}

	@PutMapping(path = "{userId}")
	@Operation(
			description = "Put",
			summary = "Update User"
	)
	public void updateUser(
			@PathVariable("userId") Long id,
			@RequestParam(required = false) String password,
			@RequestParam(required = false) String email
	) {
		usersService.updateUser(id, password, email);
	}
}
