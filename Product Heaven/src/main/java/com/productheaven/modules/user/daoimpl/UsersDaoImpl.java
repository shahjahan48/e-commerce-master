package com.productheaven.modules.user.daoimpl;

import com.productheaven.entities.Users;
import com.productheaven.generics.AbstractHibernateDao;
import com.productheaven.modules.user.dao.UsersDao;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("usersDao")
@Transactional
public class UsersDaoImpl extends AbstractHibernateDao<Users, Long> implements UsersDao {

    public Users findById(long id) {
        return findOne(id);
    }

    public Users findByUsername(String username) {
        return findAll().stream().filter(x->x.getUserName().equals(username)).findAny().get();
    }

    public Users findByEmailAddress(String email) {
        return findAll().stream().filter(x->x.getEmailAddress().equals(email)).findAny().get();
    }

    public Users save(Users user) {
        save(user);
        return user;
    }

    public Users update(Users user){
        update(user);
        return user;
    }

    public void delete(Users user){
        delete(user);
    }

    public void deleteById(long id){
        deleteById(id);
    }

    public void deleteByEmail(String email) {
        delete(findAll().stream().filter(x->x.getEmailAddress().equals(email)).findAny().get());
    }

    public List<Users> findAllUsers() {
        return findAll();
    }
}
