/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.core.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bananabank.ibbb.core.entity.Entity;
import com.google.common.base.Optional;

/**
 * The Session class that provides methods to work with persistence.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 * 
 * @param <K> The type of he identifier
 * @param <T> the type of the entity
 */
@Component
public class Session<K, T extends Entity<K>> {

    /** The persiscence. */
    private Persistence persiscence;

    @Autowired
    public Session(Persistence persiscence) {
        this.persiscence = persiscence;
    }

    /**
     * Saves or updates entity;
     *
     * @param entity the entity to be saved or updated.
     */
    public void saveOrUpdate(T entity) {
        persiscence.put(entity.getId(), entity);
    }

    /**
     * List s all entities of specified type.
     *
     * @param type The type of objects that should be listed.
     * @return The collection of found objects
     */
    @SuppressWarnings("unchecked")
    public Collection<T> getAll(Class<T> type) {
        Map<Object, Object> objects = persiscence.getAll(type);
        List<T> filtered = new ArrayList<>();
        for (Entry<Object, Object> entry : objects.entrySet()) {
            if (type.isInstance(entry.getValue())) {
                filtered.add((T) entry.getValue());
            }
        }

        return filtered;
    }

    /**
     * Returns the entity from persistence.
     *
     * @param type The type of the entity to look for
     * @param id The id of the entity
     * @return the optional object if found
     */
    @SuppressWarnings("unchecked")
    public Optional<T> get(Class<T> type, K id) {
        return (Optional<T>) persiscence.get(type, id);
    }

    /**
     * Deletes entity from persistence.
     *
     * @param entity the entity
     */
    public void delete(T entity) {
        persiscence.remove(entity);
    }
}
