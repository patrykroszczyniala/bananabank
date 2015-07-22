/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.history.entity;

import java.math.BigDecimal;

/**
 * Decrease transaction.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
public class DecreaseTransaction extends Transaction {

    public DecreaseTransaction(Integer id, BigDecimal value) {
        super(value, Type.DECREASE);
    }

}
