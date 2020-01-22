package com.productheaven.modules.user.dao;

import com.productheaven.entities.Roles;
import com.productheaven.entities.UserRoles;

import java.util.List;

public interface RolesDao {
    List<Roles> findAllRoles();

    Roles findByRole(String role);

    Roles findById(long id);

    List<Roles> findRolesByUserId(long id);
}
