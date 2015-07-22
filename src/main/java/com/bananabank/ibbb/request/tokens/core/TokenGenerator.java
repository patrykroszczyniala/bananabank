/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.tokens.core;

import java.util.Random;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.bananabank.ibbb.request.tokens.entity.Token;

/**
 * Generate tokens used during authorization process.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 * @version $Id$
 */
public class TokenGenerator {

    /**
     * Generates random token/password.
     *
     * @return The generated random token
     */
    public Token generate(final String userId) {
        final String tokenString = Integer.toHexString(new Random().nextInt());
        return new Token(new Owner(userId), tokenString);
    }

}
