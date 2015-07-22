/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.history.core;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bananabank.ibbb.core.persistence.Dao;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.bananabank.ibbb.request.history.entity.AccountHistory;

/**
 * Dao for history handling.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@Component
public class HistoryDao implements Dao<Owner, AccountHistory> {

    /** The session. */
    private Session<Owner, AccountHistory> session;

    @Autowired
    public HistoryDao(final Session<Owner, AccountHistory> session) {
        this.session = session;
    }

    @Override
    public void saveOrUpdate(AccountHistory entity) {
        session.saveOrUpdate(entity);
    }

    @Override
    public AccountHistory get(Owner id) {
        Optional<AccountHistory> persistedAccount =
                session.get(AccountHistory.class, id);
        if (persistedAccount.isPresent()) {
            return persistedAccount.get();
        }
        return new AccountHistory(id);
    }

    @Override
    public void delete(final AccountHistory entity) {
        session.delete(entity);
    }

    @Override
    public Collection<AccountHistory> getAll() {
        return session.getAll(AccountHistory.class);
    }
}
