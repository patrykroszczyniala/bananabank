package com.bananabank.ibbb.request.tokens.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.entity.Owner;
import com.bananabank.ibbb.request.tokens.entity.Token;
import com.google.common.base.Optional;

//@Service
// TODO should be request scoped !
// TODO make it generic
// TODO create dao for audited data
// TODO create interface for BalanceDAO and TransactionDAO
//@Scope("request")
@Component
// @Scope("request")
public class TokenDao {

    private Session<Owner, Token> session;

    @Autowired
    public TokenDao(final Session<Owner, Token> session) {
        this.session = session;
    }

    public Optional<Token> get(final Owner owner) {
        return session.get(Token.class, owner);
    }

    public void persist(Token entity) {
        session.saveOrUpdate(entity);
    }

    public void delete(final Token token) {
        session.delete(token);
    }
}
