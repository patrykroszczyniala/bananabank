package com.bananabank.ibbb.unit.request.history.core;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.bananabank.ibbb.core.persistence.Persistence;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.bananabank.ibbb.request.history.core.HistoryDao;
import com.bananabank.ibbb.request.history.core.HistoryService;
import com.bananabank.ibbb.request.history.entity.AccountHistory;
import com.bananabank.ibbb.request.history.entity.Transaction;
import com.bananabank.ibbb.request.history.entity.Transaction.Type;
import com.bananabank.ibbb.request.history.entity.Transactions;

@RunWith(MockitoJUnitRunner.class)
public class HistoryServiceTest {

    private HistoryDao historyDao;
    private HistoryService historyService;

    @Before
    public void setUp() {
        historyDao = new HistoryDao(new Session<>(new Persistence()));
        historyService = new HistoryService(historyDao);
    }

    @Test
    public void shouldReturnHistoricalTransaction() {
        // given
        String userId = "patryk";
        Owner owner = new Owner(userId);
        Transactions transactions = new Transactions();
        transactions.add(new Transaction(BigDecimal.TEN, Type.INCREASE));
        transactions.add(new Transaction(BigDecimal.ONE, Type.DECREASE));
        AccountHistory persistedHistory =
                new AccountHistory(owner, transactions);
        historyDao.saveOrUpdate(persistedHistory);
        // when
        Transactions foundTransactions = historyService.getHistory(userId);
        // then
        Assert.assertEquals(
                persistedHistory.getTransactions(),
                foundTransactions);
    }

    @Test
    public void shouldAddHistoryEntry() {
        // given
        String userId = "patryk";
        Owner owner = new Owner(userId);
        Transaction t1 = new Transaction(BigDecimal.TEN, Type.INCREASE);
        Transaction t2 = new Transaction(BigDecimal.ONE, Type.DECREASE);
        // when
        historyService.addHistoryEntry(userId, t1);
        historyService.addHistoryEntry(userId, t2);
        // then
        AccountHistory foundHistory = historyDao.get(owner);
        Assert.assertEquals(
                t1,
                foundHistory.getTransactions().getTransactions().get(0));
        Assert.assertEquals(
                t2,
                foundHistory.getTransactions().getTransactions().get(1));
    }

}
