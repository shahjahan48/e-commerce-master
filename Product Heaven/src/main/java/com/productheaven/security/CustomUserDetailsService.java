package com.productheaven.security;

import com.productheaven.entities.UserRoles;
import com.productheaven.entities.Users;
import com.productheaven.modules.user.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final LoginDao loginDao;

    @Autowired
    public CustomUserDetailsService(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Users user = loginDao.findUserByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
        User principalUser = new User(
                user.getEmailAddress(),
                user.getPassword(),
                user.isActive(),
                true,
                true,
                true,
                getGrantedAuthorities(user)
        );
        return principalUser;
    }


    private List<GrantedAuthority> getGrantedAuthorities(Users user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(UserRoles userRoles : user.getUserRoles()){

            authorities.add(new SimpleGrantedAuthority("ROLE_"+userRoles.getRole().getRoleName()));
        }
        return authorities;
    }
}
