/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.balance.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The Balance of the account entity.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
public class Balance {

    /** The current value of the balance. */
    private BigDecimal value;

    /**
     * Instantiates a new empty balance.
     */
    public Balance() {
        value = BigDecimal.ZERO;
    }

    /**
     * Instantiates a new balance.
     *
     * @param value the value
     */
    public Balance(final BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    /**
     * Subtract the number from the balance.
     *
     * @param subtrahend the subtrahend
     * @return the updated balance
     */
    public Balance subtract(final BigDecimal subtrahend) {
        return new Balance(this.value.subtract(subtrahend));
    }

    /**
     * Adds the number to the balance.
     *
     * @param augend the augend
     * @return the updated balance
     */
    public Balance add(final BigDecimal augend) {
        return new Balance(this.value.add(augend));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Balance other = (Balance) obj;

        return Objects.equals(other.getValue(), value);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Balance [value=");
        sb.append(value);
        sb.append("]");

        return sb.toString();
    }

}
