# defect-spring-data-mongodb-query
Simple testcase for a query bug in spring-data-mongodb `QueryMapper` with annotations and `PropertyValueConverter`.

The `@LocalDateAsString` annotation is used on selected `LocalDate` fields to have them mapped to `string` in mongo.

The `LocalDateToStringPropertyConverter` simply converts to/from `String`/`LocalDate`.

Everything is fine with mapping and simple queries but with eg "range" queries the `QueryMapper` calls the converter with a bson `Document` which obviously fails with a `ClassCastException`.

Testcase: `SpringDefectInQueryMapperTest`

spring-boot version 3.0.11
spring-data-mongodb version 4.0.10

Exception:

```
java.lang.ClassCastException: class org.bson.Document cannot be cast to class java.time.LocalDate (org.bson.Document is in unnamed module of loader 'app'; java.time.LocalDate is in module java.base of loader 'bootstrap')

	at com.udby.defect.spring.data.mongodb.converter.query.simple.LocalDateToStringPropertyConverter.write(LocalDateToStringPropertyConverter.java:10)
	at org.springframework.data.mongodb.core.convert.QueryMapper.getMappedValue(QueryMapper.java:450)
	at org.springframework.data.mongodb.core.convert.QueryMapper.getMappedObjectForField(QueryMapper.java:339)
	at org.springframework.data.mongodb.core.convert.QueryMapper.getMappedObject(QueryMapper.java:166)
	at org.springframework.data.mongodb.core.QueryOperations$QueryContext.getMappedQuery(QueryOperations.java:350)
	at org.springframework.data.mongodb.core.MongoTemplate.doFind(MongoTemplate.java:2422)
	at org.springframework.data.mongodb.core.MongoTemplate.doFind(MongoTemplate.java:2411)
	at org.springframework.data.mongodb.core.MongoTemplate.find(MongoTemplate.java:815)
	at org.springframework.data.mongodb.core.MongoTemplate.find(MongoTemplate.java:805)
	at com.udby.defect.spring.data.mongodb.converter.query.simple.SpringDefectInQueryMapperTest.fail_LocalDate_between(SpringDefectInQueryMapperTest.java:36)
...
```

https://github.com/spring-projects/spring-data-mongodb

https://github.com/spring-projects/spring-data-mongodb/issues/4510
