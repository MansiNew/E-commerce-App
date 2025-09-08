package com.ecommerce.service;

import com.ecommerce.model.User;

public interface UserService {
public User findUserByJwtToken(String jwtToken);
//find user by email
}
