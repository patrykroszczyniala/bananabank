package com.bananabank.ibbb.unit.core.persistence;

import java.util.Map;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import com.bananabank.ibbb.core.persistence.Persistence;

@RunWith(MockitoJUnitRunner.class)
public class PersistenceTest {

    @InjectMocks
    private Persistence persistence;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnEmptyObjectForNonExistingEntry() {
        // given
        final String id = "1";
        final Class<String> type = String.class;
        // when
        Optional<Object> returned = persistence.get(type, id);
        // then
        Assert.assertTrue(!returned.isPresent());
    }

    @Test
    public void shouldReturnEmptyMapForNonExistingItem() {
        // given
        final Class<String> type = String.class;
        // when
        Map<Object, Object> returned = persistence.getAll(type);
        // then
        Assert.assertTrue(returned.isEmpty());
    }

    @Test
    public void shouldAddItemToPersistence() {
        // given
        final String id = "1";
        final String entity = "some data";
        // when
        persistence.put(id, entity);
        // then
        Optional<Object> returned = persistence.get(entity.getClass(), id);
        Assert.assertEquals(entity, returned.get());
    }

    @Test
    public void shouldOverwriteExistingItem() {
        // given
        final String id = "1";
        final String newEntity = "some data";
        final String overwriteEntity = "overwritten data";
        // when
        persistence.put(id, newEntity);
        persistence.put(id, overwriteEntity);
        // then
        Optional<Object> returned = persistence.get(String.class, id);
        Assert.assertEquals(overwriteEntity, returned.get());
    }

    @Test
    public void shouldDeleteItemFromPersistence() {
        // given
        final String id = "1";
        final String entity = "some data";
        // when
        persistence.put(id, entity);
        Optional<Object> returned = persistence.get(String.class, id);
        Assert.assertTrue(returned.isPresent());
        persistence.remove(entity);
        // then
        returned = persistence.get(String.class, id);
        Assert.assertFalse(returned.isPresent());
    }
}
