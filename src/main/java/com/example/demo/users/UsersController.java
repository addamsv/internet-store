package com.example.demo.users;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users")
@RestController
@RequestMapping("api/v1/users")
@CrossOrigin("*")
public class UsersController {
	private final UsersService usersService;

	@Autowired
	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

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
	@GetMapping
	public List<Users> getUsers() {
		return usersService.getUsers();
	}

	@Operation(
			description = "Post",
			summary = "Create user"
	)
	@PostMapping
	public Users addUser(@RequestBody Users user) {
		return usersService.addUser(user);
	}

	@Operation(
			description = "",
			summary = "Remove user"
	)
	@DeleteMapping(path = "{userId}")
	public void dellUser(@PathVariable("userId") Long id) {
		usersService.dellUser(id);
	}

	@Operation(
			description = "",
			summary = "Update User"
	)
	@PutMapping(path = "{userId}")
	public void updateUser(
			@PathVariable("userId") Long id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email
	) {
		usersService.updateUser(id, name, email);
	}
}
