package com.mongo.controller;

import com.mongo.common.Result;
import com.mongo.controller.dto.StudentQueryDTO;
import com.mongo.entity.Student;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @Resource
    MongoTemplate mongoTemplate;

    @GetMapping
    public Result listStudents() {
        List<Student> studentList = mongoTemplate.findAll(Student.class);
        return Result.success(studentList);
    }

    @PostMapping("query")
    public Result listStudents(@RequestBody StudentQueryDTO dto) {

        String dname = dto.getDname();
        Integer ageStart = dto.getAgeStart();
        Integer ageEnd = dto.getAgeEnd();
        Query query = new Query();
        if (!ObjectUtils.isEmpty(ageStart)&&!ObjectUtils.isEmpty(ageEnd)) {
            Criteria gte= Criteria.where("age").gte(ageStart);
            Criteria lte= Criteria.where("age").lte(ageEnd);
            query.addCriteria(new Criteria().andOperator(gte,lte));
        }else if(!ObjectUtils.isEmpty(ageStart)){
            Criteria gte= Criteria.where("age").gte(ageStart);
            query.addCriteria(new Criteria().andOperator(gte));
        }else if (!ObjectUtils.isEmpty(ageEnd)){
            Criteria lte= Criteria.where("age").lte(ageEnd);
            query.addCriteria(new Criteria().andOperator(lte));
        }

        if (!ObjectUtils.isEmpty(dname)) {
            Criteria c3 = Criteria.where("dname").is(dname);
            query.addCriteria(c3);
        }

        List<Student> studentList = mongoTemplate.find(query, Student.class, "student");
        return Result.success(studentList);
    }

    @PostMapping
    public Result saveStudent(@RequestBody Student student){
        Student save = mongoTemplate.save(student,"student");
        return Result.success();
    }

}
