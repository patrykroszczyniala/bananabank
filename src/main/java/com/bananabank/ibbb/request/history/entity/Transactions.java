/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.history.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores all historical transactions.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
public class Transactions {

    /** The stored historical transactions. */
    private final List<Transaction> transactions;

    /** Instantiates a new empty transactions object. */
    public Transactions() {
        transactions = new ArrayList<Transaction>();
    }

    /**
     * Instantiates a new transactions.
     *
     * @param transactions the transactions
     */
    public Transactions(final List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void add(final Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public String toString() {
        return "Transactions [transactions=" + transactions + "]";
    }

}
