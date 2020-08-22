package com.productheaven.modules.user.service;

import com.productheaven.entities.Roles;

import java.util.List;

public interface RoleService {
    Roles findById(long id);

    Roles findByRole(String role);

    List<Roles> findAll();

    List<Roles> findRolesByUserId(long id);
}
