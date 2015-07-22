/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.balance;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bananabank.ibbb.core.authorization.Authorized;
import com.bananabank.ibbb.request.RequestUrl;
import com.bananabank.ibbb.request.balance.core.BalanceService;
import com.bananabank.ibbb.request.balance.entity.Balance;
import com.bananabank.ibbb.request.balance.entity.BalanceRequest;
import com.bananabank.ibbb.request.history.core.Audited;
import com.bananabank.ibbb.request.history.entity.Transaction.Type;

/**
 * The main entry point for the balance requests.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@RestController
@Scope("request")
public class BalanceController {

    /** The balance service. */
    private BalanceService balanceService;

    @Autowired
    public BalanceController(final BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    /**
     * Increases the balance.
     *
     * @param userId The user id for which balance should be increased
     * @param balanceRequest The balance request required to process it
     */
    @Authorized
    @Audited(type = Type.INCREASE)
    @RequestMapping(value = RequestUrl.INCREASE_BALANCE_URL, method = RequestMethod.POST)
    public void increase(
            @PathVariable final String userId,
            @Valid @RequestBody final BalanceRequest balanceRequest) {
        balanceService.increase(userId, balanceRequest.getValue());
    }

    /**
     * Decreases the balance.
     *
     * @param userId The user id for which balance should be decreased
     * @param balanceRequest The balance request required to process it
     */
    @Authorized
    @Audited(type = Type.DECREASE)
    @RequestMapping(value = RequestUrl.DECREASE_BALANCE_URL, method = RequestMethod.POST)
    public void decrease(
            @PathVariable final String userId,
            @Valid @RequestBody final BalanceRequest balanceRequest) {
        balanceService.decrease(userId, balanceRequest.getValue());
    }

    /**
     * Returns current balance.
     *
     * @param userId The user id for which balance should be checked
     */
    @RequestMapping(value = RequestUrl.CHECK_BALANCE_URL, method = RequestMethod.GET)
    public Balance check(@PathVariable final String userId) {
        return balanceService.getBalance(userId);
    }

}
