/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.tokens.entity;

import java.util.Objects;
import com.bananabank.ibbb.core.entity.Entity;
import com.bananabank.ibbb.request.balance.entity.Owner;

/**
 * Token used for authorization in increase and decrease account balance.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
public class Token implements Entity<Owner> {

    /** The token id (owner). */
    private final Owner id;

    /** The token/password. */
    private final String token;

    /**
     * Instantiates a new empty token.
     */
    public Token() {
        id = new Owner();
        token = "";
    }

    /**
     * Instantiates a new token.
     *
     * @param owner the owner
     * @param token the token
     */
    public Token(Owner owner, String token) {
        this.id = owner;
        this.token = token;
    }

    @Override
    public Owner getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token);
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
        Token other = (Token) obj;
        return Objects.equals(other.getId(), id)
                && Objects.equals(other.getToken(), token);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Token [owner=");
        sb.append(id);
        sb.append(", token=");
        sb.append(token);
        sb.append("]");

        return sb.toString();
    }

}
