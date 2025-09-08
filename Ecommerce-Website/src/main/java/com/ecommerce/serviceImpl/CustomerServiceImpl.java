package com.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ecommerce.domain.USER_ROLE;
import com.ecommerce.model.Seller;
import com.ecommerce.model.User;
import com.ecommerce.repo.SellerRepository;
import com.ecommerce.repo.UserRepository;
import com.ecommerce.utility.JwtConstants;
import lombok.RequiredArgsConstructor;

/*
 * This class is used for manual password generation
 * 
 */

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	private final SellerRepository sellerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if (username.startsWith(JwtConstants.SELLER_PREFIX)) {
			String actualUserName = username.substring(JwtConstants.SELLER_PREFIX.length());
			Seller seller = sellerRepository.findByEmail(actualUserName);
			if (seller != null) {
				return buildUserDetails(seller.getEmail(), seller.getPassword(), seller.getRole());
			}

		} else {
			//User user = userRepository.findByEmailId(username);
		User user = userRepository.findByEmailId(username);
		System.out.println("========"+  user);
			if (user!=null) {
				System.out.println("====================================================");
				return buildUserDetails(user.getEmailId(), user.getPassword(), user.getUserRole());
			}
		}

		throw new UsernameNotFoundException("user or seller not found of this email" + username);
	}

	private UserDetails buildUserDetails(String emailId, String password, USER_ROLE userRole) {
		if (userRole == null)
			userRole = userRole.ROLE_CUSTOMER;
		List<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority(userRole.toString()));

		return new org.springframework.security.core.userdetails.User(emailId, password, authorityList);
	}

}
