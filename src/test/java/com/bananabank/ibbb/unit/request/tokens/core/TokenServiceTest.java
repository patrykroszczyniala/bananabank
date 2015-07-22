package com.bananabank.ibbb.unit.request.tokens.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.bananabank.ibbb.core.persistence.Persistence;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.bananabank.ibbb.request.tokens.core.TokenDao;
import com.bananabank.ibbb.request.tokens.core.TokenService;
import com.bananabank.ibbb.request.tokens.entity.Token;

@RunWith(MockitoJUnitRunner.class)
public class TokenServiceTest {

    private TokenDao tokenDao;
    private TokenService tokenService;

    @Before
    public void setUp() {
        tokenDao = new TokenDao(new Session<>(new Persistence()));
        tokenService = new TokenService(tokenDao);
    }

    @Test
    public void shouldGenerateToken() {
        // given
        String userId = "anna";
        // when
        Token generated = tokenService.generate(userId);
        // then
        Token persisted = tokenDao.get(new Owner(userId));
        Assert.assertEquals(generated, persisted);
    }
}
