package com.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.domain.USER_ROLE;
import com.ecommerce.model.Cart;
import com.ecommerce.model.User;
import com.ecommerce.model.VerificationCode;
import com.ecommerce.repo.UserRepository;
import com.ecommerce.repo.VerificationCodeRepository;
import com.ecommerce.request.LoginRequest;
import com.ecommerce.request.SignUpRequest;
import com.ecommerce.response.AuthResponse;
import com.ecommerce.service.AuthService;
import com.ecommerce.repo.CartRepository;
import com.ecommerce.utility.LoginConstants;
import com.ecommerce.utility.OtpUtil;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepositoty;
	private final CartRepository cartRepositoty;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final VerificationCodeRepository verificcationCodeRepository;
	private final EmailService emailService;
	private final CustomerServiceImpl customerService;;
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	/*
	 * Creates a new user.
	 * 
	 * @date 13-03-25
	 */
	@Override
	public String createUserHandler(SignUpRequest request) throws Exception {
		VerificationCode verificationCode = verificcationCodeRepository.findByEmail(request.getEmail());

		if (verificationCode == null || !verificationCode.getOtp().equals(request.getOtp())) {
			throw new Exception("wrong otp..");
		}

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

	/**
	 * Creates and send otp
	 * 
	 * @date -18-03-25
	 */
	@Override
	public void sendLoginOtp(String email) throws Exception {
		if (email.startsWith(LoginConstants.SIGNING_PREFIX)) {
			email = email.substring(LoginConstants.SIGNING_PREFIX.length());
		}
		VerificationCode existingVerificationCode = verificcationCodeRepository.findByEmail(email);
		
		if (existingVerificationCode != null) {
			verificcationCodeRepository.delete(existingVerificationCode);
		}

		String otp = OtpUtil.generateOtp();
		VerificationCode newVerificationCode= new VerificationCode();
		newVerificationCode.setEmail(email);
		newVerificationCode.setOtp(otp);
		verificcationCodeRepository.save(newVerificationCode);

		//sent email
		String subject = "Login Otp Send";
		String text = "send otp on email" + "  " + otp;
		emailService.sendVerificationOtpEmail(email, otp, subject, text);
	}

	/**
	 * user signin
	 * 
	 * @date 19-03-25
	 */
	@Override
	public AuthResponse userSignin(LoginRequest request) {
		Authentication authentication = authenticateUser(request.getEmail(), request.getOtp());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		AuthResponse response = new AuthResponse();
		String jwtToken = jwtProvider.generateToken(authentication);

		response.setMessage(LoginConstants.LOGIN_SUCCESSFULL);
		response.setJwtToken(jwtToken);

		// role implementation
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String userRole = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
		response.setRole(USER_ROLE.valueOf(userRole));
		// TODO Auto-generated method stub
		return response;
	}

	/*
	 * Verify otp ,if otp is valid then it will return authentication
	 */
	private Authentication authenticateUser(String email, String otp) {
		// TODO Auto-generated method stub
		UserDetails userDetails = customerService.loadUserByUsername(email);
		if (userDetails == null) {
			throw new BadCredentialsException("Wrong Username");
		}
		VerificationCode verificationCode = verificcationCodeRepository.findByEmail(email);
		if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
			throw new BadCredentialsException("Wrong otp");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
