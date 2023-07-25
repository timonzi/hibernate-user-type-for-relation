package org.acme.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.acme.types.StringWrapper;
import org.acme.usertypes.StringWrapperUserType;
import org.hibernate.annotations.Type;

@Entity
public class EntityB {
    @Id
    @Type(StringWrapperUserType.class)
    public StringWrapper id;

    public EntityB() {
    }

    public EntityB(final StringWrapper id) {
        this.id = id;
    }

    public StringWrapper getId() {
        return id;
    }
}
