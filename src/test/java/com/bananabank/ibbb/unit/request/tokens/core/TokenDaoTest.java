package com.bananabank.ibbb.unit.request.tokens.core;

import java.util.Collection;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.bananabank.ibbb.core.persistence.Persistence;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.bananabank.ibbb.request.tokens.core.TokenDao;
import com.bananabank.ibbb.request.tokens.entity.Token;
import com.google.common.collect.Lists;

public class TokenDaoTest {

    private Session<Owner, Token> session;
    private TokenDao tokenDao;

    @Before
    public void setUp() {
        session = new Session<>(new Persistence());
        tokenDao = new TokenDao(session);
    }

    @Test
    public void shouldPersistNewEntityEntity() {
        // given
        final String userId = "patryk";
        Token entity = new Token(new Owner(userId), "1");
        // when
        tokenDao.saveOrUpdate(entity);
        // then
        Optional<Token> actual = session.get(Token.class, new Owner(userId));
        Token expected = new Token(new Owner(userId), "1");
        Assert.assertEquals(expected, actual.get());
    }

    @Test
    public void shouldUpdatePersistedEntity() {
        // given
        final String userId = "patryk";
        Token e1 = new Token(new Owner(userId), "1");
        tokenDao.saveOrUpdate(e1);
        // when
        Token updatedE1 = new Token(new Owner(userId), "123");
        tokenDao.saveOrUpdate(updatedE1);
        // then
        Optional<Token> actual = session.get(Token.class, new Owner(userId));
        Token expected = new Token(new Owner(userId), "123");
        Assert.assertEquals(expected, actual.get());
    }

    @Test
    public void shouldReturnPersistedEntity() {
        // given
        final String userId = "patryk";
        Token entity = new Token(new Owner(userId), "1");
        session.saveOrUpdate(entity);
        // when
        Token actual = tokenDao.get(new Owner(userId));
        // then
        Token expected = new Token(new Owner(userId), "1");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeletePersistedEntity() {
        // given
        final String userId = "patryk";
        Token entity = new Token(new Owner(userId), "1");
        session.saveOrUpdate(entity);
        // when
        Token deleted = new Token(new Owner(userId), "1");
        tokenDao.delete(deleted);
        // then
        Optional<Token> expected = session.get(Token.class, new Owner(userId));
        Assert.assertFalse(expected.isPresent());
    }

    @Test
    public void shouldReturnAllPersistedEntities() {
        // given
        final String u1 = "patryk";
        final String u2 = "anna";
        Token e1 = new Token(new Owner(u1), "1");
        Token e2 = new Token(new Owner(u2), "121");
        session.saveOrUpdate(e1);
        session.saveOrUpdate(e2);
        // when
        Collection<Token> actual = tokenDao.getAll();
        // then
        Collection<Token> expected =
                Lists.newArrayList(
                        new Token(new Owner(u1), "1"),
                        new Token(new Owner(u2), "121"));
        Assert.assertEquals(expected, actual);
    }
}
