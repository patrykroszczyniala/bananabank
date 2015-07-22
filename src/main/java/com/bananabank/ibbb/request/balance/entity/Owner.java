/**
 * Copyright (c) 2015, Patryk Roszczyniała
 */
package com.bananabank.ibbb.request.balance.entity;

import java.util.Objects;

/**
 * The owner entity.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
public class Owner {

    /** The name of the owner. */
    private final String name;

    /** Instantiates a new empty owner. */
    public Owner() {
        name = "";
    }

    /**
     * Instantiates a new owner.
     *
     * @param name The name of the owner.
     */
    public Owner(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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
        Owner other = (Owner) obj;

        return Objects.equals(other.getName(), name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Owner [name=");
        sb.append(name);
        sb.append("]");

        return sb.toString();
    }

}
