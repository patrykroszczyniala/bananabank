/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bananabank.ibbb.request.RequestUrl;
import com.bananabank.ibbb.request.history.core.HistoryService;
import com.bananabank.ibbb.request.history.entity.Transactions;

/**
 * The main entry point for the history requests.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@RestController
@Scope("request")
public class HistoryController {

    /** The transaction service. */
    private HistoryService transactionService;

    @Autowired
    public HistoryController(final HistoryService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Returns the history of user account.
     *
     * @param userId The user id for which transactions history should be
     *        returned.
     * @return the transactions history
     */
    @RequestMapping(value = RequestUrl.CHECK_HISTORY_URL, method = RequestMethod.GET)
    public Transactions history(@PathVariable final String userId) {
        return transactionService.transactionsHistory(userId);
    }

}
