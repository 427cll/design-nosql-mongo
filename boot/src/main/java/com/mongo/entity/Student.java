package com.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("student")
public class Student {
    @Id
    String id;
    String sid;
    String name;
    String sex;
    Integer age;
    String birthday;
    String dname;
    @Field("class")
    Integer clas;

}
