/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.balance.entity;

import java.util.Objects;
import com.bananabank.ibbb.core.entity.Entity;

/**
 * The User account entity.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
public class Account implements Entity<Owner> {

    /** The current balance. */
    private final Balance balance;

    /** The owner of the account. */
    private final Owner owner;

    public Account(final Balance balance, final Owner owner) {
        this.balance = balance;
        this.owner = owner;
    }

    @Override
    public Owner getId() {
        return owner;
    }

    public Balance getBalance() {
        return balance;
    }

    public Owner getOwner() {
        return owner;
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
        Account other = (Account) obj;

        return Objects.equals(other.getOwner(), owner);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Account [owner=");
        sb.append(owner);
        sb.append(", balance=");
        sb.append(balance);
        sb.append("]");

        return sb.toString();
    }

}
