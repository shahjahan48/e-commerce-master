package com.productheaven.modules.user.service;

import com.productheaven.entities.Users;

import java.util.List;

public interface UserService {
    Users findById(long id);

    Users findByEmail(String email);

    Users saveUser(Users user);

    Users updateUser(Users user);

    void deleteUserByEmail(String email);

    List<Users> findAllUsers();

    boolean isUserEmailUnique(Long id, String email);
}
