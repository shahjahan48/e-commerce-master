package com.productheaven.modules.user.serviceimpl;

import com.productheaven.entities.Roles;
import com.productheaven.entities.UserRoles;
import com.productheaven.modules.user.dao.RolesDao;
import com.productheaven.modules.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rolesService")
public class RoleServiceImpl implements RoleService {
    private final RolesDao rolesDao;
    @Autowired
    public RoleServiceImpl(RolesDao rolesDao){
        this.rolesDao = rolesDao;
    }

    @Override
    public Roles findById(long id) {
        return rolesDao.findById(id);
    }

    @Override
    public Roles findByRole(String role) {
        return rolesDao.findByRole(role);
    }

    @Override
    public List<Roles> findAll() {
        return rolesDao.findAllRoles();
    }

    @Override
    public List<Roles> findRolesByUserId(long id) {
        return rolesDao.findRolesByUserId(id);
    }
}
