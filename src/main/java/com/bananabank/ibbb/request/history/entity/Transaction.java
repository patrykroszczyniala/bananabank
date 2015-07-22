/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.history.entity;

import java.math.BigDecimal;

/**
 * Stores information about transaction.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
public class Transaction {

    /**
     * The type of the transaction.
     *
     * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
     */
    public enum Type {

        /** Used for null-object. */
        EMPTY,

        /** The increase transaction type */
        INCREASE,

        /** The decrease transaction type */
        DECREASE;

    }

    /**
     * The type of the transaction.
     */
    private final Type type;

    /** The value of the transaction. */
    private final BigDecimal value;

    /**
     * Instantiates a new empty transaction.
     */
    public Transaction() {
        this.value = BigDecimal.ZERO;
        this.type = Type.EMPTY;
    }

    /**
     * Instantiates a new transaction.
     *
     * @param value the value
     * @param type the type
     */
    public Transaction(BigDecimal value, Type type) {
        this.value = value;
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Transaction [type=");
        sb.append(type);
        sb.append(", value=");
        sb.append(value);
        sb.append("]");

        return sb.toString();
    }

}
