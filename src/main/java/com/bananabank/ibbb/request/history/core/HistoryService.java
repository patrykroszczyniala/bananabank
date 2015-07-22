/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.history.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.bananabank.ibbb.request.history.entity.AccountHistory;
import com.bananabank.ibbb.request.history.entity.Transaction;
import com.bananabank.ibbb.request.history.entity.Transactions;

/**
 * The history service.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@Service
public class HistoryService {

    /** The history dao. */
    private HistoryDao dao;

    @Autowired
    public HistoryService(HistoryDao dao) {
        this.dao = dao;
    }

    /**
     * Returns the history of user transactions.
     *
     * @param userId The user for which history should be returned.
     * @return the historical transactions
     */
    public Transactions transactionsHistory(final String userId) {
        return dao.getTransactions(new Owner(userId));
    }

    /**
     * Adds the transaction to the history.
     *
     * @param userId the user id for which transaction should be audited
     * @param transaction the audited transaction
     */
    public void addHistoryEntry(final String userId,
            final Transaction transaction) {
        Transactions transactinos = transactionsHistory(userId);
        transactinos.add(transaction);
        AccountHistory accountHistory =
                new AccountHistory(new Owner(userId), transactinos);

        dao.saveOrUpdate(accountHistory);
    }

}
