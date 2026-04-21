package com.example.service.Impl;

import com.example.mapper.StuMapper;
import com.example.pojo.Clazz;
import com.example.pojo.PageResult;
import com.example.pojo.StuQuaryParam;
import com.example.pojo.Student;
import com.example.service.StuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;
    @Override
    public PageResult<Clazz> findall(StuQuaryParam stuQuaryParam) {
        //1.设置分页参数
        PageHelper.startPage(stuQuaryParam.getPage(), stuQuaryParam.getPageSize());
        //2.执行查询
        List<Student> stulist = stuMapper.list(stuQuaryParam);
        Page<Student> p = (Page<Student>) stulist;

        //3.封装结果
        return new PageResult(p.getTotal(), p.getResult());
    }

    //新增学生
    @Override
    public void addStu(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.addStu(student);
    }

    @Override
    public Student findById(Integer id) {
        return stuMapper.findById(id);
    }

    @Override
    public void updateStu(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.updateStu(student);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        stuMapper.deleteByIds(ids);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void violation(Integer id, Integer score) {
        Student stu = stuMapper.findById(id);
        //1.查看扣分是否合法
        if (score < 0 || score > 100) {
            throw new RuntimeException("扣分不合法");
        }
        //2.执行扣分操作
        short newscore = (short)(stu.getViolationScore() + score);
        stu.setViolationScore(newscore);
        //3.更新违纪次数
        short violationCount = (short)(stu.getViolationCount() + 1);
        stu.setViolationCount(violationCount);



        stuMapper.updateStu(stu);
    }
}
