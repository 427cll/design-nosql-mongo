package com.mongo.controller;

import com.mongo.common.Result;
import com.mongo.entity.StudentCourse;
import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("select-course")
public class SelectCourseController {
    @Resource
    MongoTemplate mongoTemplate;

    @PostMapping("sid/{sid}/cid/{cid}")
    public Result selectCourse(@PathVariable String sid, @PathVariable String cid) {
        //在选课表中插入一条数据
        StudentCourse studentCourse = new StudentCourse(null,sid,cid,null);
        StudentCourse insert = mongoTemplate.insert(studentCourse);
        return Result.success();
    }

    @DeleteMapping("sid/{sid}/cid/{cid}")
    public Result cancelCourse(@PathVariable String sid, @PathVariable String cid){
        Query query = new Query(Criteria.where("sid").is(sid).and("cid").is(cid));
        DeleteResult remove = mongoTemplate.remove(query,StudentCourse.class);
        return Result.success();
    }

}
