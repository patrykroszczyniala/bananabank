package com.bananabank.ibbb.request.balance.core;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.entity.Account;
import com.bananabank.ibbb.request.balance.entity.Balance;
import com.bananabank.ibbb.request.balance.entity.Owner;

// TODO make it generic
// TODO create interface for BalanceDAO and TransactionDAO
@Component
@Scope("request")
public class BalanceDao {

    private Session<Owner, Account> session;

    @Autowired
    public BalanceDao(final Session<Owner, Account> session) {
        this.session = session;
    }

    // @Override
    public void persist(Account entity) {
        session.saveOrUpdate(entity);
        // session.saveOrUpdate(entity);
    }

    // @Override
    public Account get(Owner owner) {
        Optional<Account> account = session.get(Account.class, owner);
        if (account.isPresent()) {
            return account.get();
        }
        return new Account(new Balance(new BigDecimal(0)), owner);
    }
}
