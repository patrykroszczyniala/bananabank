package com.bananabank.ibbb.unit.request.balance.core;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.bananabank.ibbb.core.persistence.Persistence;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.core.AccountDao;
import com.bananabank.ibbb.request.balance.entity.Account;
import com.bananabank.ibbb.request.balance.entity.Balance;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class AccountDaoTest {

    private Session<Owner, Account> session;
    private AccountDao accountDao;

    @Before
    public void setUp() {
        session = new Session<>(new Persistence());
        accountDao = new AccountDao(session);
    }

    @Test
    public void shouldPersistNewEntityEntity() {
        // given
        String userId = "patryk";
        Account entity =
                new Account(new Balance(new BigDecimal(3)), new Owner(userId));
        // when
        accountDao.saveOrUpdate(entity);
        // then
        Optional<Account> actual =
                session.get(Account.class, new Owner(userId));
        Account expected =
                new Account(new Balance(new BigDecimal(3)), new Owner(userId));
        Assert.assertEquals(expected, actual.get());
        Assert.assertEquals(expected.getBalance(), actual.get().getBalance());
    }

    @Test
    public void shouldUpdatePersistedEntity() {
        // given
        String userId = "patryk";
        Account entity =
                new Account(new Balance(new BigDecimal(3)), new Owner(userId));
        Account updated =
                new Account(new Balance(new BigDecimal(5)), new Owner(userId));
        // when
        accountDao.saveOrUpdate(entity);
        accountDao.saveOrUpdate(updated);
        // then
        Optional<Account> actual =
                session.get(Account.class, new Owner(userId));
        Account expected =
                new Account(new Balance(new BigDecimal(5)), new Owner(userId));
        Assert.assertEquals(expected, actual.get());
        Assert.assertEquals(expected.getBalance(), actual.get().getBalance());
    }

    @Test
    public void shouldReturnPersistedEntity() {
        // given
        String userId = "patryk";
        Account entity =
                new Account(new Balance(new BigDecimal(3)), new Owner(userId));
        // when
        session.saveOrUpdate(entity);
        // then
        Account actual = accountDao.get(new Owner(userId));
        Account expected =
                new Account(new Balance(new BigDecimal(3)), new Owner(userId));
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected.getBalance(), actual.getBalance());
    }

    @Test
    public void shouldDeletePersistedEntity() {
        // given
        String userId = "patryk";
        Account entity =
                new Account(new Balance(new BigDecimal(3)), new Owner(userId));
        session.saveOrUpdate(entity);
        // when
        Account deleted =
                new Account(new Balance(new BigDecimal(3)), new Owner(userId));
        accountDao.delete(deleted);
        // then
        Optional<Account> actual =
                session.get(Account.class, new Owner(userId));
        Assert.assertFalse(actual.isPresent());
    }

    @Test
    public void shouldReturnAllPersistedEntities() {
        // given
        String u1 = "patryk";
        String u2 = "anna";
        Account e1 =
                new Account(new Balance(new BigDecimal(1)), new Owner(u1));
        Account e2 =
                new Account(new Balance(new BigDecimal(2)), new Owner(u2));
        session.saveOrUpdate(e1);
        session.saveOrUpdate(e2);
        // when
        Collection<Account> all = accountDao.getAll();
        // then
        Collection<Account> expected =
                Lists.newArrayList(
                        new Account(new Balance(new BigDecimal(1)), new Owner(
                                u1)),
                        new Account(new Balance(new BigDecimal(2)), new Owner(
                                u2))
                        );
        Assert.assertEquals(expected, all);
    }

}
