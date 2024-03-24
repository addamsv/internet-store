package com.example.demo.endpoints.users;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.demo.endpoints.DTO.RespDTO;
import com.example.demo.endpoints.users.dto.CreateUserDTO;
import com.example.demo.endpoints.users.dto.UpdateUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // the same @Component
public class UsersService {
	private final UsersRepository usersRepository;

	@Autowired
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public ResponseEntity<RespDTO<Users>> getByLogin(String login) {
		Users candidate = usersRepository.findUserByEmail(login).orElse(null);

		if (candidate == null) {
			return new ResponseEntity<>(
					new RespDTO<>("Entity does not exist", null),
					HttpStatusCode.valueOf(409));
		}

		return new ResponseEntity<>(
				new RespDTO<>("SUCCESS", candidate),
				HttpStatusCode.valueOf(200)
		);
	}

	public ResponseEntity<RespDTO<List<Users>>> getAll() {
		return new ResponseEntity<>(
				new RespDTO<>("SUCCESS", this.usersRepository.findAll()),
				HttpStatusCode.valueOf(200)
		);
	}

	public ResponseEntity<RespDTO<Users>> create(CreateUserDTO dto) {
		Optional<Users> candidate = this.usersRepository.findUserByEmail(dto.getEmail());

		if (candidate.isPresent()) {
			return new ResponseEntity<>(
					new RespDTO<>("Entity is already exist", null),
					HttpStatusCode.valueOf(409));
		}

		Users user = new Users(dto.getPassword(), dto.getEmail(), dto.getRole());
		return new ResponseEntity<>(
				new RespDTO<>("Created", this.usersRepository.save(user)),
				HttpStatusCode.valueOf(201)//HttpStatus.CREATED
		);
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
			dto.getRole() != null && ! dto.getRole().isEmpty()
			&& ! Objects.equals(entity.getRole(), dto.getRole())
		) {
			entity.setRole(dto.getRole());
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

	public boolean check(HttpServletRequest req) {//ResponseEntity<RespDTO<Users>>
		System.out.println(req.getHeader("Authorization"));
		return false;
//		return new ResponseEntity<>(new RespDTO<>("Success", null), HttpStatus.OK);
	}
}
