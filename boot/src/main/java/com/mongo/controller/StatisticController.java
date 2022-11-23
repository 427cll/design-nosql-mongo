package com.mongo.controller;

import cn.hutool.log.Log;
import com.alibaba.fastjson.JSON;
import com.mongo.entity.Course;
import com.mongo.entity.Student;
import com.mongo.entity.StudentCourse;
import com.mongo.entity.Tmp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("statistic")
public class StatisticController {

    @Resource
    MongoTemplate mongoTemplate;



    @GetMapping("course-selected")
    public List<String> findCourseSelected() {
        List<StudentCourse> studentCourses = mongoTemplate.findAll(StudentCourse.class);
        List<String> cids = studentCourses.stream().map(StudentCourse::getCid).distinct().toList();

        Query query = new Query(Criteria.where("cid").in(cids));
        List<Course> courses = mongoTemplate.find(query, Course.class);
        return courses.stream().map(Course::getName).toList();
    }

    @GetMapping("avg-grade-top10")
    public List<String> findGoodStudents() {

        Aggregation aggregation1 = Aggregation.newAggregation(Aggregation.group("cid").count().as("selectedCounts"));
        AggregationResults<BasicDBObject> outputTypeCount1 =
                mongoTemplate.aggregate(aggregation1, "student_course", BasicDBObject.class);
        List<Tmp> tmpList = new ArrayList<>();

        for (Iterator<BasicDBObject> iterator = outputTypeCount1.iterator(); iterator.hasNext(); ) {
            DBObject obj = iterator.next();
            String jsonString = JSON.toJSONString(obj);
            System.out.println(jsonString);
            Tmp parse = JSON.parseObject(jsonString,Tmp.class);
            tmpList.add(parse);
        }

        List<Tmp> top10 = tmpList.stream().sorted(Comparator.comparing(Tmp::getSelectedCounts).reversed()).limit(10).toList();
        List<String> cids = top10.stream().map(Tmp::get_id).toList();
        Query query = new Query(Criteria.where("cid").in(cids));
        List<Course> courses = mongoTemplate.find(query, Course.class);

        List<String> courseTop10 = courses.stream().map(item -> item.getName()).toList();
        return courseTop10;
    }


}
