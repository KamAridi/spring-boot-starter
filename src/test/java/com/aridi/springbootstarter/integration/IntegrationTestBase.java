package com.aridi.springbootstarter.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.testcontainers.containers.PostgreSQLContainer;

//@Sql({
//        "classpath:sql/data.sql"
//})
//@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
public abstract class IntegrationTestBase {

    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:14.6");

//    @BeforeAll
//    static void runContainer(){
//        container.start();
//    }

//    @DynamicPropertySource
//    static void postgresProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//    }
}
