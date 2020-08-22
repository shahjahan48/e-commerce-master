package com.productheaven.generics;

import com.productheaven.entities.LoginInfo;
import org.apache.log4j.Logger;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository("JdbcTokenRepositoryImpl")
@Transactional
public class TokenRepository extends AbstractHibernateDao<LoginInfo, String> implements PersistentTokenRepository {
    static final Logger logger = Logger.getLogger(PersistentRememberMeToken.class);
    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        try{
            LoginInfo persistentLogin = new LoginInfo();
            persistentLogin.setUsername(persistentRememberMeToken.getUsername());
            persistentLogin.setSeries(persistentRememberMeToken.getSeries());
            persistentLogin.setToken(persistentRememberMeToken.getTokenValue());
            persistentLogin.setLastUsed(persistentRememberMeToken.getDate());
            create(persistentLogin);
        }catch (Exception e){
            logger.error("Token creation exception. Email: " + persistentRememberMeToken.getUsername());
        }
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        try{
            List<LoginInfo> persistentLogins = findAll();
            LoginInfo persistentLogin = persistentLogins.stream().filter(x-> x.getSeries().equals(seriesId))
                    .collect(Collectors.toList()).get(0);

            persistentLogin.setToken(tokenValue);
            persistentLogin.setLastUsed((Timestamp) lastUsed);
            update(persistentLogin);
        }catch (Exception e){
            logger.error("Update token Exception. Message: " + e.getMessage());
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            List<LoginInfo> persistentLogins = findAll();
            LoginInfo persistentLogin = persistentLogins.stream().filter(x-> x.getSeries().equals(seriesId))
                    .collect(Collectors.toList()).get(0);

            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLastUsed());
        } catch (Exception e) {
            logger.error("Exception in Remember token, message: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {
        try {
            List<LoginInfo> persistentLogins = findAll();
            persistentLogins = persistentLogins.stream().filter(x-> x.getUsername().equals(username))
                    .collect(Collectors.toList());
            if (persistentLogins.size() > 0) {
                logger.info("rememberMe was selected");
                persistentLogins.forEach(persistentLogin->{
                    delete(persistentLogin);
                });
            }
        }catch (Exception e){
            logger.error("Exception: " + e.getMessage());
        }
    }
}
