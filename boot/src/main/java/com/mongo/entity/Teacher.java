package com.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("teacher")
public class Teacher {
    @Id
    String id;
    String tid;
    String name;
    String sex;
    Integer age;
    String dname;
}
