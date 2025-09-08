package com.ecommerce.serviceImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.exception.InvalidTokenException;
import com.ecommerce.exception.UserNotFoundException;
import com.ecommerce.model.User;
import com.ecommerce.repo.UserRepository;
import com.ecommerce.service.UserService;
import com.ecommerce.utility.ErrorConstants;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;

	@Override
	public User findUserByJwtToken(String jwtToken) {
		try {
			String email = jwtProvider.getEmailFromJwt(jwtToken); // Extract the email from JWT
			User user = userRepository.findByEmailId(email); // Find the user by email

			if (user == null) {
				throw new UserNotFoundException(ErrorConstants.USER_NOT_FOUND); // Custom exception if user is
																						// not found
			}

			return user;
		} catch (Exception e) {
			// Handle specific JWT-related exceptions
			throw new InvalidTokenException(ErrorConstants.INVALID_JWT_TOKEN);
		}

	}

}
