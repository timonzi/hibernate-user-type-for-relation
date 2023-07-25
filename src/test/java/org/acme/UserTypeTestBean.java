package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import org.acme.entities.EntityA;
import org.acme.entities.EntityB;
import org.acme.types.StringWrapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApplicationScoped
@Transactional
public class UserTypeTestBean {

    @Inject
    EntityManager em;


    void createEntity() {
        final var entityA = new EntityA(new StringWrapper("ID_A_001"));
        final var entityB1 = new EntityB(new StringWrapper("ID_B_001"));
        final var entityB2 = new EntityB(new StringWrapper("ID_B_002"));

        final var entityBSet = new HashSet<EntityB>();
        entityBSet.add(entityB1);
        entityBSet.add(entityB2);

        entityA.setEntityBSet(entityBSet);

        em.persist(entityA);
    }


    void findEntities() {
        final var entityA = em.find(EntityA.class, new StringWrapper("ID_A_001"));
        assertNotNull(entityA);

        assertNotNull(em.find(EntityB.class, new StringWrapper("ID_B_001")));
        assertNotNull(em.find(EntityB.class, new StringWrapper("ID_B_002")));

        assertEquals(2, entityA.getEntityBSet().size());
    }


    void queryEntities() {
        final var queryA = em.getCriteriaBuilder().createQuery(EntityA.class);
        queryA.from(EntityA.class);
        final var resultListA = em.createQuery(queryA).getResultList();
        assertEquals(1, resultListA.size());
        assertEquals(2, resultListA.get(0).getEntityBSet().size());
    }

}
