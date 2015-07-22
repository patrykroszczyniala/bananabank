/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.balance.entity;

/**
 * Marks entity as one that needs authorization to be accessed by the request.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
public interface Authorizable {

    /**
     * Returns the password.
     *
     * @return the token that is used during authorization
     */
    String getToken();

}
