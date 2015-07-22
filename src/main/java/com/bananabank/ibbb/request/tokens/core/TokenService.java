/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.tokens.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.bananabank.ibbb.request.tokens.entity.Token;

/**
 * Token service.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@Service
@Scope("request")
public class TokenService {

    /** The token dao. */
    private TokenDao dao;

    @Autowired
    public TokenService(TokenDao dao) {
        this.dao = dao;
    }

    /**
     * Generates and persists new token (old userId token is overwritten).
     *
     * @param userId The user id for which token should be generated
     * @return the generater token
     */
    public Token generate(String userId) {
        Token token = new TokenGenerator().generate(userId);
        dao.saveOrUpdate(token);
        return token;
    }
}
