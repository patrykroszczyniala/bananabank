/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.history.core;

import java.math.BigDecimal;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bananabank.ibbb.request.balance.entity.BalanceRequest;
import com.bananabank.ibbb.request.history.entity.Transaction;
import com.bananabank.ibbb.request.history.entity.Transaction.Type;

/**
 * The history interceptor. Saves all increase and decrease opertations.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@Component
public class HistoryInterceptor implements MethodInterceptor {

    private HistoryService historyService;

    @Autowired
    public void setPersistence(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Type transactionType =
                invocation.getMethod().getAnnotation(Audited.class).type();
        String userId = (String) invocation.getArguments()[0];
        BalanceRequest balanceRequest = (BalanceRequest) invocation
                .getArguments()[1];
        BigDecimal requestedValue = balanceRequest.getValue();
        Transaction transaction = new Transaction(requestedValue, transactionType);

        Object methodResult = invocation.proceed();

        historyService.addHistoryEntry(userId, transaction);

        return methodResult;
    }
}
