package com.pashenko.wintermarket.services;

import com.pashenko.wintermarket.entities.SystemUser;
import com.pashenko.wintermarket.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    boolean save(SystemUser systemUser);
}
