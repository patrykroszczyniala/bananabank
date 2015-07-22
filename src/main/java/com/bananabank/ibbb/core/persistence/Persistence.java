/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.core.persistence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Primitive version of a persistence used to store application data.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@Component
@Scope("singleton")
public class Persistence {

    /**
     * The list of saved objects.<br/>
     * Class<?> - type of object stored in map <br/>
     * Map<Object, Object> - map with id and saved contents (Map<Id, Content>)
     */
    private Map<Class<?>, Map<Object, Object>> objects = new HashMap<>();

    /**
     * Returns the list of saved objects.
     *
     * @param type the type
     * @return the objects
     */
    public Map<Object, Object> getAll(Class<?> type) {
        if (objects.get(type) == null) {
            objects.put(type, new HashMap<>());
        }
        return objects.get(type);
    }

    /**
     * Gets the object from persisence.
     *
     * @param type the type of entity to return
     * @param id the id of the object
     * @return the object
     */
    public Optional<Object> get(Class<?> type, Object id) {
        Map<Object, Object> objects = getAll(type);
        for (Entry<Object, Object> entry : objects.entrySet()) {
            if (entry.getKey().equals(id)) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    /**
     * Removes the entity from persistence.
     *
     * @param entity The entity to be removed
     */
    public void remove(Object entity) {
        Map<Object, Object> objects = getAll(entity.getClass());
        Iterator<Entry<Object, Object>> iterator =
                objects.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Object, Object> obj = iterator.next();
            if (entity.equals(obj.getValue())) {
                iterator.remove();
                return;
            }
        }
    }

    /**
     * Adds the entity to persistence. If persistence contained passed entity,
     * the old value is replaced by the entity.
     *
     * @param id The id of the passed entity
     * @param entity The entity that should be saved
     */
    public void put(Object id, Object entity) {
        getAll(entity.getClass()).put(id, entity);
    }

    /**
     * Removes all adta from persistence.
     */
    public void clear() {
        this.objects.clear();
    }

}
