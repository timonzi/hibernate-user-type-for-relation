package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTypeTest {

    @Inject
    UserTypeTestBean testBean;

    @Test
    @Order(0)
    void createEntity() {
        testBean.createEntity();
    }

    @Test
    @Order(1)
    void findEntities() {
        testBean.findEntities();
    }

    @Test
    @Order(2)
    void queryEntities() {
        testBean.queryEntities();
    }

}