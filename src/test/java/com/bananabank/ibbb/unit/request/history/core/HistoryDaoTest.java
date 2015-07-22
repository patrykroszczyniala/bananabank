package com.bananabank.ibbb.unit.request.history.core;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.bananabank.ibbb.core.persistence.Persistence;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.bananabank.ibbb.request.history.core.HistoryDao;
import com.bananabank.ibbb.request.history.entity.AccountHistory;
import com.bananabank.ibbb.request.history.entity.Transaction;
import com.bananabank.ibbb.request.history.entity.Transaction.Type;
import com.bananabank.ibbb.request.history.entity.Transactions;
import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class HistoryDaoTest {

    private Session<Owner, AccountHistory> session;
    private HistoryDao historyDao;

    @Before
    public void setUp() {
        session = new Session<>(new Persistence());
        historyDao = new HistoryDao(session);
    }

    @Test
    public void shouldPersistNewEntityEntity() {
        // given
        Owner owner = new Owner("patryk");
        AccountHistory entity =
                new AccountHistory(
                        owner,
                        new Transactions(Lists.newArrayList(
                                new Transaction(BigDecimal.TEN, Type.INCREASE),
                                new Transaction(BigDecimal.ONE, Type.DECREASE))));
        // when
        historyDao.saveOrUpdate(entity);
        // then
        Optional<AccountHistory> persistedHistory =
                session.get(AccountHistory.class, owner);
        Assert.assertEquals(entity, persistedHistory.get());
    }

    @Test
    public void shouldUpdatePersistedEntity() {
        // given
        Owner owner = new Owner("patryk");
        AccountHistory entity =
                new AccountHistory(
                        owner,
                        new Transactions(Lists.newArrayList(
                                new Transaction(BigDecimal.TEN, Type.INCREASE),
                                new Transaction(BigDecimal.ONE, Type.DECREASE))));
        historyDao.saveOrUpdate(entity);
        // when
        entity.getTransactions()
                .add(new Transaction(BigDecimal.ONE, Type.INCREASE));
        historyDao.saveOrUpdate(entity);
        // then
        Optional<AccountHistory> persistedHistory =
                session.get(AccountHistory.class, owner);
        Assert.assertEquals(entity, persistedHistory.get());
        Assert.assertEquals(3, persistedHistory.get().getTransactions()
                .getTransactions().size());
    }

    @Test
    public void shouldReturnPersistedEntity() {
        // given
        Owner owner = new Owner("patryk");
        AccountHistory entity =
                new AccountHistory(
                        owner,
                        new Transactions(Lists.newArrayList(
                                new Transaction(BigDecimal.TEN, Type.INCREASE),
                                new Transaction(BigDecimal.ONE, Type.DECREASE))));
        historyDao.saveOrUpdate(entity);
        // when
        AccountHistory foundHistory = historyDao.get(owner);
        // then
        Assert.assertEquals(entity, foundHistory);
    }

    @Test
    public void shouldDeletePersistedEntity() {
        // given
        Owner owner = new Owner("patryk");
        AccountHistory entity =
                new AccountHistory(
                        owner,
                        new Transactions(Lists.newArrayList(
                                new Transaction(BigDecimal.TEN, Type.INCREASE),
                                new Transaction(BigDecimal.ONE, Type.DECREASE))));
        historyDao.saveOrUpdate(entity);
        // when
        historyDao.delete(entity);
        // then
        AccountHistory foundHistory = historyDao.get(owner);
        Assert.assertEquals(owner, foundHistory.getId());
        Assert.assertTrue(
                foundHistory.getTransactions().getTransactions().isEmpty());
    }

}
