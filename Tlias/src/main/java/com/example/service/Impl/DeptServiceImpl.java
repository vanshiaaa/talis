package com.example.service.Impl;

import com.example.mapper.DeptMapper;
import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptmapper;
    @Override
    public List<Dept> findall() {
        return deptmapper.findall();
    }

    @Override
    public void deleteById(Integer id) {
        deptmapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptmapper.add(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptmapper.GetById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptmapper.update(dept);
    }


}
