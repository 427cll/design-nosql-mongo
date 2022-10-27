package com.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document("student_course")
public class StudentCourse {
    @Id
    String id;
    String sid;
    String cid;
    Double score;
}
