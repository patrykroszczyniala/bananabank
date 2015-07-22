/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.history.entity;

import java.util.Objects;
import com.bananabank.ibbb.core.entity.Entity;
import com.bananabank.ibbb.request.balance.entity.Owner;

/**
 * The account history entity.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
public class AccountHistory implements Entity<Owner> {

    /** The owner of the account. */
    private final Owner owner;

    /** The historical transactions. */
    private final Transactions transactions;

    /**
     * Instantiates a new account history.
     *
     * @param owner the owner
     * @param transactions the transactions
     */
    public AccountHistory(final Owner owner, final Transactions transactions) {
        this.owner = owner;
        this.transactions = transactions;
    }

    @Override
    public Owner getId() {
        return owner;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AccountHistory other = (AccountHistory) obj;

        return Objects.equals(other.getId(), owner);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AccountHistory [owner=");
        sb.append(owner);
        sb.append(", transactions=");
        sb.append(transactions);
        sb.append("]");

        return sb.toString();
    }

}
