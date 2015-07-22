/**
 * Copyright (c) 2015, Patryk Roszczyniała
 */
package com.bananabank.ibbb.request.balance.entity;

import java.math.BigDecimal;
import javax.validation.constraints.Min;

/**
 * The entity used in balance requests (increase, decrease).
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
public class BalanceRequest implements Authorizable {

    /** The requested value. */
    @Min(value = 0)
    private BigDecimal value;

    /** The token used during authorization. */
    private String token;

    public BigDecimal getValue() {
        return value;
    }

    // public void setValue(BigDecimal value) {
    // this.value = value;
    // }

    @Override
    public String getToken() {
        return token;
    }

    // public void setToken(String token) {
    // this.token = token;
    // }

}
