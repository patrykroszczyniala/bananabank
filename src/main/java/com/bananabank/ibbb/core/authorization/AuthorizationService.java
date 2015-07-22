/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.core.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.bananabank.ibbb.request.tokens.core.TokenDao;
import com.bananabank.ibbb.request.tokens.entity.Token;
import com.google.common.base.Preconditions;

/**
 * Authorization service.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@Service
public class AuthorizationService {

    private TokenDao tokenDao;

    @Autowired
    public AuthorizationService(final TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    /**
     * Checks if user is authorized.
     *
     * @param userId The user to be authorized
     * @param password The user password used for authorization
     * @return true, if user is authorized
     */
    public boolean authorized(final String userId, final String password) {
        Preconditions.checkNotNull(password);
        Preconditions.checkArgument(!password.isEmpty());
        Token token = tokenDao.get(new Owner(userId));
        boolean isEqual = password.equals(token.getToken());
        if (isEqual) {
            tokenDao.delete(token);
        }
        return isEqual;
    }

}
