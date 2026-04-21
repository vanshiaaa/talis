package com.example.mapper;

import com.example.pojo.StuQuaryParam;
import com.example.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StuMapper {


    List<Student> list(StuQuaryParam stuQuaryParam);

    void addStu(Student student);

    Student findById(Integer id);

    void updateStu(Student student);

    void deleteByIds(List<Integer> ids);
}
