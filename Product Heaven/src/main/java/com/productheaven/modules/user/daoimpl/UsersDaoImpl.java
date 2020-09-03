package com.productheaven.modules.user.daoimpl;

import com.productheaven.entities.Users;
import com.productheaven.generics.AbstractHibernateDao;
import com.productheaven.modules.user.dao.UsersDao;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("usersDao")
public class UsersDaoImpl extends AbstractHibernateDao<Users, Long> implements UsersDao {

    public Users findById(long id) {
        return findOne(id);
    }

    public Users findByEmailAddress(String email) {
        List<Users> usersList=findAll();
        Optional<Users> user = usersList.stream().filter(x->x.getEmailAddress().equals(email)).findFirst();
        if(!user.isPresent())
            return null;
        return user.get();
    }

    public Users save(Users user) {
        create(user);
        return user;
    }

    public Users update(Users user){
        super.update(user);
        return user;
    }

    public void delete(Users user){
        super.delete(user);
    }

    public void deleteById(long id){
        super.deleteById(id);
    }

    public void deleteByEmail(String email) {
        delete(findAll().stream().filter(x->x.getEmailAddress().equals(email)).findAny().get());
    }

    public List<Users> findAllUsers() {
        return findAll();
    }
}
