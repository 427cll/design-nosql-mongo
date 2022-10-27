package com.mongo;

import com.mongo.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class MongoTemplateTest extends MongoApplicationTests {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoTemplateTest(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /*集合操作*/
    @Test
    public void testCreateCollection() {
        boolean exists = mongoTemplate.collectionExists("produvts");
        if (!exists) {
            mongoTemplate.createCollection("products");
        }
    }
    @Test
    public void testDropCollection(){
        mongoTemplate.dropCollection("products");

    }
    /*文档操作*/
    //创建文档
    @Test
    public void testInsert(){
        Student student = new Student("1", "202000300230", "小陈", "男", 20, "2001-12-26", "软件学院", 2022);
        mongoTemplate.insert(student);
    }
    //查询所有
    @Test
    public void testFind(){
        List<Student> all =
                mongoTemplate.findAll(Student.class);
        all.forEach(System.out::println);
    }
}
