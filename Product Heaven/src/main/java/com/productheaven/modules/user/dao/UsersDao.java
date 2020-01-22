package com.productheaven.modules.user.dao;

import com.productheaven.entities.Users;

import java.util.List;

public interface UsersDao {
    Users findById(long id);

    Users findByUsername(String username);

    Users findByEmailAddress(String email);

    Users save(Users user);

    Users update(Users user);

    void delete(Users user);

    void deleteById(long id);

    void deleteByEmail(String email);

    List<Users> findAllUsers();
}
