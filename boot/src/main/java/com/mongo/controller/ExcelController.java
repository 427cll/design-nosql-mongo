package com.mongo.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.mongo.common.Result;
import com.mongo.entity.Course;
import com.mongo.entity.Student;
import com.mongo.entity.Teacher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping("excel")
public class ExcelController {
    @Resource
    MongoTemplate mongoTemplate;

    //excel导入
    @PostMapping("upload/student")
    public Result uploadStudents(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);

        List<List<Object>> list = reader.read(1);
        List<Student> students = CollUtil.newArrayList();

        for (List<Object> row : list) {
            Student student = new Student();
            student.setSid(row.get(0).toString());
            student.setName(row.get(1).toString());
            student.setSex(row.get(2).toString());
            student.setAge(Integer.parseInt(row.get(3).toString()));
            student.setBirthday(row.get(4).toString());
            student.setDname(row.get(5).toString());
            student.setClas(Integer.parseInt(row.get(6).toString()));
            students.add(student);
        }

        mongoTemplate.insert(students, Student.class);
        return Result.success();
    }

    @PostMapping("upload/teacher")
    public Result uploadTeachers(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);

        List<List<Object>> list = reader.read(1);
        List<Teacher> teachers = CollUtil.newArrayList();

        for (List<Object> row : list) {
            Teacher teacher = new Teacher();
            teacher.setTid(row.get(0).toString());
            teacher.setName(row.get(1).toString());
            teacher.setSex(row.get(2).toString());
            teacher.setAge(Integer.parseInt(row.get(3).toString()));
            teacher.setDname(row.get(4).toString());
            teachers.add(teacher);
        }

        mongoTemplate.insert(teachers, Teacher.class);
        return Result.success();
    }

    @PostMapping("upload/course")
    public Result uploadCourses(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);

        List<List<Object>> list = reader.read(1);
        List<Course> courses = CollUtil.newArrayList();

        for (List<Object> row : list) {
            Course course = new Course();
            course.setCid(row.get(0).toString());
            course.setName(row.get(1).toString());
            course.setCredit(Double.parseDouble(row.get(2).toString()));
            course.setFcid(row.get(3).toString());
            courses.add(course);
        }

        mongoTemplate.insert(courses, Course.class);
        return Result.success();
    }

}
