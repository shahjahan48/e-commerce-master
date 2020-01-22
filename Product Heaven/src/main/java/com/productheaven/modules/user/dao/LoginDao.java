package com.productheaven.modules.user.dao;

import com.productheaven.entities.Users;

public interface LoginDao {
    Users findUserByEmail(String email);
}
