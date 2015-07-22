/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.tokens.core;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bananabank.ibbb.core.persistence.Dao;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.bananabank.ibbb.request.tokens.entity.Token;

/**
 * Dao for token handling.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@Component
public class TokenDao implements Dao<Owner, Token> {

    /** The session. */
    private Session<Owner, Token> session;

    /**
     * Instantiates a new token dao.
     *
     * @param session the session
     */
    @Autowired
    public TokenDao(final Session<Owner, Token> session) {
        this.session = session;
    }

    @Override
    public Token get(final Owner owner) {
        Optional<Token> persistedToken = session.get(Token.class, owner);
        if (persistedToken.isPresent()) {
            return persistedToken.get();
        }
        return new Token();
    }

    @Override
    public void saveOrUpdate(Token entity) {
        session.saveOrUpdate(entity);
    }

    @Override
    public void delete(final Token token) {
        session.delete(token);
    }

    @Override
    public Collection<Token> getAll() {
        return session.getAll(Token.class);
    }

}
