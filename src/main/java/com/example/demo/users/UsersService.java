package com.example.demo.users;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

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

	public Users addUser(Users user) {
		Optional<Users> userCandidate = usersRepository.findUserByEmail(user.getEmail());

		if (userCandidate.isPresent()) {
			throw new IllegalStateException("email is already taken");
		}

		return usersRepository.save(user);
	}

	public void dellUser(Long userId) {
		boolean isUserCandidateExist = usersRepository.existsById(userId);

		if (!isUserCandidateExist) {
			throw new IllegalStateException("user does not exist");
		}

		usersRepository.deleteById(userId);
	}

	@Transactional
	public void updateUser(Long userId, String password, String email) {
		Users user = usersRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("user does not exist"));

		if (
				password != null &&
                !password.isEmpty() &&
				!Objects.equals(user.getPassword(), password)
		) {
			user.setPassword(password);
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
