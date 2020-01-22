package com.productheaven.modules.user.service;

import com.productheaven.entities.Users;

public interface LoginService {
    Users loadUserByUsername(String email);
}
