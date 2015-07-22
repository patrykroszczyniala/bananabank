package com.bananabank.ibbb.request.history.core;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.bananabank.ibbb.request.history.entity.AccountHistory;
import com.bananabank.ibbb.request.history.entity.Transactions;

// TODO create dao for audited data
@Component
public class HistoryDao {

    private Session<Owner, AccountHistory> session;

    @Autowired
    public HistoryDao(final Session<Owner, AccountHistory> session) {
        this.session = session;
    }

    public void saveOrUpdate(AccountHistory entity) {
        session.saveOrUpdate(entity);
    }

    // TODO move to TransactionsDao ?
    public Transactions getTransactions(Owner owner) {
        Collection<AccountHistory> all = session.getAll(AccountHistory.class);
        for (AccountHistory ah : all) {
            if (owner.equals(ah.getId())) {
                return ah.getTransactions();
            }
        }
        return new Transactions(new ArrayList<>());
    }
}
