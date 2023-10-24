package com.example.demo.users;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // the same @Component
public class UsersService {
	private final UsersRepository usersRepository;

	@Autowired
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public List<Users> getUsers() { return usersRepository.findAll(); }

	public void addUser(Users user) {
		Optional<Users> userCandidate = usersRepository.findUserByEmail(user.getEmail());

		if (userCandidate.isPresent()) {
			throw new IllegalStateException("email is already taken");
		}

		usersRepository.save(user);
	}

	public void dellUser(Long userId) {
		boolean isUserCandidateExist = usersRepository.existsById(userId);

		if (!isUserCandidateExist) {
			throw new IllegalStateException("user does not exist");
		}

		usersRepository.deleteById(userId);
	}

	@Transactional
	public void updateUser(Long userId, String name, String email) {
		Users user = usersRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("user does not exist"));

		if (
				name != null &&
                !name.isEmpty() &&
				!Objects.equals(user.getName(), name)
		) {
			user.setName(name);
		}

		if (
				email != null &&
                !email.isEmpty() &&
				!Objects.equals(user.getEmail(), email)
		) {

			Optional<Users> userCandidate = usersRepository.findUserByEmail(email);

			if (userCandidate.isPresent()) {
				throw new IllegalStateException("email is already taken");
			}

			user.setEmail(email);
		}
	}
}
