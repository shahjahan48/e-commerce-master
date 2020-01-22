package com.productheaven.generics;

import com.productheaven.entities.LoginInfo;
import com.productheaven.generics.AbstractHibernateDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository("tokenRepositoryDao")
@Transactional
public class TokenRepository extends AbstractHibernateDao<LoginInfo, String> implements PersistentTokenRepository {
    static final Logger logger = LoggerFactory.getLogger(TokenRepository.class);
    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        logger.info("Creating Token for user : {}", persistentRememberMeToken.getUsername());
        LoginInfo persistentLogin = new LoginInfo();
        persistentLogin.setUserName(persistentRememberMeToken.getUsername());
        persistentLogin.setSeries(persistentRememberMeToken.getSeries());
        persistentLogin.setToken(persistentRememberMeToken.getTokenValue());
        persistentLogin.setLastUsed(persistentRememberMeToken.getDate());
        create(persistentLogin);
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        logger.info("Updating Token for seriesId : {}", seriesId);
        List<LoginInfo> persistentLogins = findAll();
        LoginInfo persistentLogin = persistentLogins.stream().filter(x-> x.getSeries().equals(seriesId))
                .collect(Collectors.toList()).get(0);

        persistentLogin.setToken(tokenValue);
        persistentLogin.setLastUsed((Timestamp) lastUsed);
        update(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        logger.info("Fetch Token if any for seriesId : {}", seriesId);
        try {
            List<LoginInfo> persistentLogins = findAll();
            LoginInfo persistentLogin = persistentLogins.stream().filter(x-> x.getSeries().equals(seriesId))
                    .collect(Collectors.toList()).get(0);

            return new PersistentRememberMeToken(persistentLogin.getUserName(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLastUsed());
        } catch (Exception e) {
            logger.info("Token not found...");
            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {
        logger.info("Removing Token if any for user : {}", username);
        List<LoginInfo> persistentLogins = findAll();
        persistentLogins = persistentLogins.stream().filter(x-> x.getUserName().equals(username))
                .collect(Collectors.toList());
        if (persistentLogins.size() > 0) {
            logger.info("rememberMe was selected");
            persistentLogins.forEach(persistentLogin->{
                delete(persistentLogin);
            });
        }
    }
}
