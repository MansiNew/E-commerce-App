package com.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.domain.USER_ROLE;
import com.ecommerce.model.Cart;
import com.ecommerce.model.User;
import com.ecommerce.repo.UserRepository;
import com.ecommerce.request.SignUpRequest;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepositoty;
	private final CartRepository cartRepositoty;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	@Override
	public String createUserHandler(SignUpRequest request) {
		// TODO Auto-generated method stub
		User user = userRepositoty.findByEmailId(request.getEmail());
		if (user == null) {
			User createdUser = new User();
			createdUser.setEmailId(request.getEmail());
			createdUser.setFirstName(request.getFullName());
			createdUser.setUserRole(USER_ROLE.ROLE_CUSTOMER);
			createdUser.setMobileNo("8989896767");
			createdUser.setPassword(passwordEncoder.encode(request.getOtp()));
			user = userRepositoty.save(createdUser);
			Cart cart = new Cart();
			cart.setUser(user);
			cartRepositoty.save(cart);

		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));
		Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), null, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtProvider.generateToken(authentication);
	}

}
