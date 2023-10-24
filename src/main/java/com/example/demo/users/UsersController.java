package com.example.demo.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin("*")
public class UsersController {
	private final UsersService usersService;

	@Autowired
	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	@GetMapping
	public List<Users> getUsers() {
		return usersService.getUsers();
	}

	@PostMapping
	public void addUser(@RequestBody Users user) {
		usersService.addUser(user);
	}

	@DeleteMapping(path = "{userId}")
	public void dellUser(@PathVariable("userId") Long id) {
		usersService.dellUser(id);
	}

	@PutMapping(path = "{userId}")
	public void updateUser(
			@PathVariable("userId") Long id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email
	) {
		usersService.updateUser(id, name, email);
	}
}
