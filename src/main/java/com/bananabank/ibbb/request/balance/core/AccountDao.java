/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.balance.core;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.bananabank.ibbb.core.persistence.Dao;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.entity.Account;
import com.bananabank.ibbb.request.balance.entity.Owner;

/**
 * Dao for account/balance handling.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@Component
@Scope("request")
public class AccountDao implements Dao<Owner, Account> {

    /**
     * The session.
     */
    private Session<Owner, Account> session;

    /**
     * Instantiates a new account dao.
     *
     * @param session the session
     */
    @Autowired
    public AccountDao(final Session<Owner, Account> session) {
        this.session = session;
    }

    @Override
    public void saveOrUpdate(Account entity) {
        session.saveOrUpdate(entity);
    }

    @Override
    public Account get(Owner owner) {
        Optional<Account> account = session.get(Account.class, owner);
        if (account.isPresent()) {
            return account.get();
        }
        return new Account(owner);
    }

    @Override
    public void delete(final Account account) {
        session.delete(account);
    }

    @Override
    public Collection<Account> getAll() {
        return session.getAll(Account.class);
    }

}
