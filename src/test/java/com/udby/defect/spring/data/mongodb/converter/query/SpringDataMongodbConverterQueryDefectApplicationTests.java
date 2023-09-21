package com.udby.defect.spring.data.mongodb.converter.query;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class SpringDataMongodbConverterQueryDefectApplicationTests {
	@Test
	void contextLoads() {
		assertThat(1 == 1).isTrue();
	}
}
