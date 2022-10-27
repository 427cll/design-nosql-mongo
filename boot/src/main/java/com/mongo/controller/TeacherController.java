package com.mongo.controller;

import com.mongo.common.Result;
import com.mongo.controller.dto.StudentQueryDTO;
import com.mongo.entity.Teacher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("teachers")
public class TeacherController {

    @Resource
    MongoTemplate mongoTemplate;

    @GetMapping
    public Result listTeachers() {
        List<Teacher> teacherList = mongoTemplate.findAll(Teacher.class);
        return Result.success(teacherList);
    }

    @PostMapping("query")
    public Result listTeachers(@RequestBody StudentQueryDTO dto) {

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

        List<Teacher> teacherList = mongoTemplate.find(query, Teacher.class, "teacher");
        return Result.success(teacherList);
    }

    @PostMapping
    public Result saveTeacher(@RequestBody Teacher teacher) {
        Teacher save = mongoTemplate.save(teacher, "teacher");
        return Result.success();
    }

}
