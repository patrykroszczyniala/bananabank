package com.bananabank.ibbb.unit.core.authorization;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.bananabank.ibbb.core.authorization.AuthorizationService;
import com.bananabank.ibbb.core.persistence.Persistence;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.bananabank.ibbb.request.tokens.core.TokenDao;
import com.bananabank.ibbb.request.tokens.entity.Token;

public class AuthorizationServiceTest {

    private TokenDao tokenDao;
    private AuthorizationService authorizationService;

    @Before
    public void setUp() {
        tokenDao = new TokenDao(new Session<>(new Persistence()));
        authorizationService = new AuthorizationService(tokenDao);
    }

    @Test
    public void shouldBeAuthorizedForValidToken() {
        // given
        String userId = "johny";
        String password = "johny";
        Token token = new Token(new Owner(userId), password);
        tokenDao.saveOrUpdate(token);
        // when
        boolean authorized = authorizationService.authorized(userId, password);
        // then
        Assert.assertTrue(authorized);

    }

    @Test
    public void shouldBeAuthorizedForValidTokenOnlyOnce() {
        // given
        String userId = "johny";
        String password = "johny";
        Token token = new Token(new Owner(userId), password);
        tokenDao.saveOrUpdate(token);
        Assert.assertTrue(authorizationService.authorized(userId, password));
        // when
        boolean authorized = authorizationService.authorized(userId, password);
        // then
        Assert.assertFalse(authorized);

    }

    @Test
    public void shouldBeUnuthorizedForInvalidToken() {
        // given
        String userId = "johny";
        String password = "wrongPassword";
        // when
        boolean authorized = authorizationService.authorized(userId, password);
        // then
        Assert.assertFalse(authorized);

    }

}
