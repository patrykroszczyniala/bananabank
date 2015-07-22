package com.bananabank.ibbb.unit.core.persistence;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import com.bananabank.ibbb.core.entity.Entity;
import com.bananabank.ibbb.core.persistence.Persistence;
import com.bananabank.ibbb.core.persistence.Session;

@RunWith(MockitoJUnitRunner.class)
public class SessionTest {

    @Spy
    private Persistence persistence;

    @InjectMocks
    private Session<String, TestEntity> session;

    @Test
    public void shouldSaveNewObjectInPersistence() {
        // given
        final String id = "1";
        final TestEntity entity = new TestEntity(id, "patryk");
        // when
        session.saveOrUpdate(entity);
        // then
        Optional<Object> foundEntity = persistence.get(TestEntity.class, id);
        Assert.assertEquals(entity, foundEntity.get());
    }

    @Test
    public void shouldUpdatePersistedEntity() {
        // given
        final String id = "1";
        // when
        session.saveOrUpdate(new TestEntity(id, "patryk"));
        session.saveOrUpdate(new TestEntity(id, "john"));
        // then
        TestEntity expected = new TestEntity(id, "john");
        Optional<Object> foundEntity = persistence.get(TestEntity.class, id);
        Assert.assertEquals(expected, foundEntity.get());
    }

    @Test
    public void shouldListAllPersistedEntities() {
        // given
        TestEntity e1 = new TestEntity("1", "patryk");
        TestEntity e2 = new TestEntity("2", "john");
        TestEntity e3 = new TestEntity("3", "john");
        // when
        session.saveOrUpdate(e1);
        session.saveOrUpdate(e2);
        session.saveOrUpdate(e3);
        // then
        Map<Object, Object> foundEntity = persistence.getAll(TestEntity.class);
        Assert.assertEquals(e1, foundEntity.get(e1.getId()));
        Assert.assertEquals(e2, foundEntity.get(e2.getId()));
        Assert.assertEquals(e3, foundEntity.get(e3.getId()));
    }

    @Test
    public void shouldDeletePersistedEntity() {
        // given
        TestEntity e1 = new TestEntity("1", "patryk");
        TestEntity e2 = new TestEntity("2", "john");
        session.saveOrUpdate(e1);
        session.saveOrUpdate(e2);
        // when
        session.delete(e1);
        // then
        Optional<TestEntity> foundE1 =
                session.get(TestEntity.class, e1.getId());
        Optional<TestEntity> foundE2 =
                session.get(TestEntity.class, e2.getId());
        Assert.assertFalse(foundE1.isPresent());
        Assert.assertTrue(foundE2.isPresent());
    }

    private class TestEntity implements Entity<String> {

        private final String id;
        private final String value;

        public TestEntity(final String id, final String value) {
            this.id = id;
            this.value = value;
        }

        @Override
        public String getId() {
            return id;
        }

        public String getValue() {
            return value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, value);
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
            TestEntity other = (TestEntity) obj;

            return Objects.equals(other.getId(), id) &&
                    Objects.equals(other.getValue(), value);
        }

        @Override
        public String toString() {
            return "TestEntity [id=" + id + ", value=" + value + "]";
        }

    }

}
