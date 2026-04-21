package com.example.service;

import com.example.pojo.Clazz;
import com.example.pojo.PageResult;
import com.example.pojo.StuQuaryParam;
import com.example.pojo.Student;

import java.util.List;

public interface StuService {
    PageResult<Clazz> findall(StuQuaryParam stuQuaryParam);

    void addStu(Student student);

    Student findById(Integer id);

    void updateStu(Student student);

    void deleteByIds(List<Integer> ids);

    void violation(Integer id, Integer score);
}
