package com.udby.defect.spring.data.mongodb.converter.query.simple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@DataMongoTest
public class SpringDefectInQueryMapperTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        mongoTemplate.remove(new Query(), TestEntity.class);
    }

    @Test
    void fail_LocalDate_between() {
        final var testEntity = new TestEntity();
        testEntity.date = LocalDate.parse("2023-09-21");

        mongoTemplate.save(testEntity);

        final var query = Query.query(where("date").gte(LocalDate.parse("2023-09-20")).lte(LocalDate.parse("2023-09-22")));

        final var testEntities = mongoTemplate.find(query, TestEntity.class);

        assertThat(testEntities).hasSize(1).extracting("date").asString().contains("2023-09-21");
    }

    @Document("TestEntity")
    static class TestEntity {
        @Id
        String id;

        @LocalDateAsString
        LocalDate date;
    }
}
