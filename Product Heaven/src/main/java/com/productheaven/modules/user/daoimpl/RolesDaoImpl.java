package com.productheaven.modules.user.daoimpl;

import com.productheaven.entities.Roles;
import com.productheaven.generics.AbstractHibernateDao;
import com.productheaven.modules.user.dao.RolesDao;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("rolesDao")
public class RolesDaoImpl extends AbstractHibernateDao<Roles, Long> implements RolesDao {

    public List<Roles> findAllRoles() {
        return findAll();
    }

    public Roles findByRole(String roleName) {
        Roles role = new Roles();
        Optional roleOpt = findAll().stream().filter(x->x.getRoleName().equals(roleName)).findAny();
        if(roleOpt.isPresent()){
            role = (Roles) roleOpt.get();
        }
        return role;
    }

    public Roles findById(long id) {
        return findById(id);
    }

    public List<Roles> findRolesByUserId(long id) {
        return findAll().stream()
                .filter(x->x.getUserRoles().stream().filter(y->y.getUser().getId() == id).findAny().get() != null)
                .collect(Collectors.toList());
    }
}
