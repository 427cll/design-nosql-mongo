package com.mongo.controller;

import com.mongo.common.Result;
import com.mongo.controller.dto.CourseQueryDTO;
import com.mongo.entity.Course;
import com.mongo.entity.Student;
import com.mongo.entity.StudentCourse;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("courses")
public class CourseController {
    @Resource
    MongoTemplate mongoTemplate;

    @GetMapping
    public Result listCourses() {
        List<Course> courseList = mongoTemplate.findAll(Course.class);
        return Result.success(courseList);
    }

    @GetMapping("sid/{sid}")
    public Result listCoursesBySid(@PathVariable String sid) {
        Query query = new Query(new Criteria().and("sid").is(sid));
        List<StudentCourse> studentCourseList = mongoTemplate.find(query, StudentCourse.class);
        List<String> cids = studentCourseList.stream().map(StudentCourse::getCid).collect(Collectors.toList());
        Query query1 = new Query(new Criteria().and("cid").in(cids));
        List<Course> courseList = mongoTemplate.find(query1, Course.class);
        return Result.success(courseList);
    }

    @PostMapping("query")
    public Result listCoursesByQuery(@RequestBody CourseQueryDTO dto) {
        String name = dto.getName();
        List<Course> courseList = null;
        Query query = new Query();
        if (!ObjectUtils.isEmpty(name))
            query.addCriteria(Criteria.where("name").regex(".*?" + name + ".*?"));
        if (!ObjectUtils.isEmpty(dto.getFcid()))
            query.addCriteria(Criteria.where("fcid").is(dto.getFcid()));
        courseList = mongoTemplate.find(query, Course.class);
        return Result.success(courseList);
    }

    @PostMapping
    public Result saveCourse(@RequestBody Course course){
         mongoTemplate.save(course,"course");
        return Result.success();
    }
}
