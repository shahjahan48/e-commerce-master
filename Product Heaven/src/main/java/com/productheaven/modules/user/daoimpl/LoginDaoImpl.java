package com.productheaven.modules.user.daoimpl;

import com.productheaven.entities.Users;
import com.productheaven.generics.AbstractHibernateDao;
import com.productheaven.modules.user.dao.LoginDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("loginDao")
@Transactional
public class LoginDaoImpl extends AbstractHibernateDao<Users, Long> implements LoginDao {
    @Override
    public Users findUserByEmail(String email) {
        Users user = new Users();
        List<Users> res = super.findAll();
        Optional usrOpt = res.stream().filter(x->x.getEmailAddress().equals(email)).findAny();
        if(usrOpt.isPresent()){
            user = (Users) usrOpt.get();
        }
        return user;
    }
}
