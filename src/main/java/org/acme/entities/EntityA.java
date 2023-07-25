package org.acme.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import org.acme.types.StringWrapper;
import org.acme.usertypes.StringWrapperUserType;
import org.hibernate.annotations.Type;

@Entity
public class EntityA {
    @Id
    @Type(StringWrapperUserType.class)
    private StringWrapper id;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<EntityB> entityBSet;

    public EntityA() {
    }

    public EntityA(final StringWrapper id) {
        this.id = id;
    }

    public StringWrapper getId() {
        return id;
    }

    public Set<EntityB> getEntityBSet() {
        return entityBSet;
    }

    public void setEntityBSet(final Set<EntityB> entityBSet) {
        this.entityBSet = entityBSet;
    }
}
