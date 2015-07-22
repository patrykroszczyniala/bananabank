/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.core.persistence;

import java.util.Collection;
import com.bananabank.ibbb.core.entity.Entity;

/**
 * The base interface for dao implementations.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 * 
 * @param <K> the key type (entity id for which dao has been created)
 * @param <T> the entity for which dao has been created
 */
public interface Dao<K, T extends Entity<K>> {

    /**
     * Saves or update entity.
     *
     * @param entity The entity to be saved or updated
     */
    void saveOrUpdate(T entity);

    /**
     * Gets the entity from persistence.
     *
     * @param id The id of the entity
     * @return the found entity
     */
    T get(final K id);

    /**
     * Gets the all entities from persistence.
     *
     * @return the all found entities
     */
    Collection<T> getAll();

    /**
     * Deletes the entity.
     *
     * @param entity The entity to be deleted
     */
    void delete(final T entity);

}
