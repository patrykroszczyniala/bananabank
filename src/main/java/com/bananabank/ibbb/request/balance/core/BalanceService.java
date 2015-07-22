/**
 * Copyright (c) 2015, Patryk Roszczyniała
 */
package com.bananabank.ibbb.request.balance.core;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.bananabank.ibbb.core.valicadion.BadRequestException;
import com.bananabank.ibbb.request.balance.entity.Account;
import com.bananabank.ibbb.request.balance.entity.Balance;
import com.bananabank.ibbb.request.balance.entity.Owner;

/**
 * The Class BalanceService.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 * @version $Id$
 */
@Service
@Scope("request")
public class BalanceService {

    private BalanceDao dao;

    @Autowired
    public BalanceService(BalanceDao dao) {
        this.dao = dao;
    }

    /**
     * Increases the balance.
     *
     * @param userId The user id for which balance should be increased
     * @param value The value by which balance should be increased
     */
    public void increase(final String userId, final BigDecimal value) {
        Account persistedAccount = dao.get(new Owner(userId));
        Balance updatedBalance = persistedAccount.getBalance().add(value);
        Account updatedAccount =
                new Account(updatedBalance, persistedAccount.getOwner());
        dao.persist(updatedAccount);
    }

    /**
     * Decreases the balance.
     *
     * @param userId The user id for which balance should be decreased
     * @param value The value by which balance should be decreased
     */
    public void decrease(final String userId, final BigDecimal value) {
        Account persistedAccount = dao.get(new Owner(userId));
        Balance updatedBalance = persistedAccount.getBalance().subtract(value);
        Account updatedAccount =
                new Account(updatedBalance, persistedAccount.getOwner());
        // TODO should be moved to some validation point
        if (updatedAccount.getBalance().getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException();
        }
        dao.persist(updatedAccount);
    }

    /**
     * Gets the balance current balance.
     *
     * @param userId The user id for which balance should be returned
     * @return The current balance
     */
    public Balance getBalance(final String userId) {
        return dao.get(new Owner(userId)).getBalance();
    }
}
