package com.productheaven.modules.user.serviceimpl;

import com.productheaven.entities.Users;
import com.productheaven.modules.user.dao.UsersDao;
import com.productheaven.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private final UsersDao usersDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UsersDao usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users findById(long id) {
        return usersDao.findById(id);
    }

    @Override
    public Users findByEmail(String email) {
        return usersDao.findByEmailAddress(email);
    }

    @Override
    public Users saveUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedDate(new Date());
        usersDao.save(user);
        return user;
    }

    @Override
    public Users updateUser(Users user) {
        Users entity = usersDao.findById(user.getId());
        if(null != entity){
            entity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        usersDao.update(user);
        return user;
    }

    @Override
    public void deleteUserByEmail(String email) {
        usersDao.deleteByEmail(email);
    }

    @Override
    public List<Users> findAllUsers() {
        return usersDao.findAllUsers();
    }

    @Override
    public boolean isUserEmailUnique(Long id, String email) {
        Users user = findByEmail(email);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }
}
