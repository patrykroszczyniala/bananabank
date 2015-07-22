/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.core.entity;

import com.bananabank.ibbb.request.balance.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Should be used by entities that are seved in persistence, eg. {@link Account}
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 * 
 * @param <T> the entity identifier
 */
@JsonIgnoreProperties(value = "id")
public interface Entity<T> {

    /**
     * Returns the entity identifier.
     *
     * @return the entity id
     */
    T getId();
}
