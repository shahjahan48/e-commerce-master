package com.productheaven.modules.user.serviceimpl;

import com.productheaven.entities.Users;
import com.productheaven.modules.user.dao.LoginDao;
import com.productheaven.modules.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
    private final LoginDao loginDao;
    @Autowired
    public LoginServiceImpl(LoginDao loginDao){
        this.loginDao = loginDao;
    }

    @Override
    public Users loadUserByUsername(String email) {
        return loginDao.findUserByEmail(email);
    }
}
