package com.example.service.Impl;

import com.example.mapper.ClazzMapper;
import com.example.pojo.Clazz;
import com.example.pojo.ClazzQuaryParam;
import com.example.pojo.Emp;
import com.example.pojo.PageResult;
import com.example.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {


    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpServiceImpl empService;
    @Override
    public void addclazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        Integer masterId = clazz.getMasterId();
//        clazz.setMasterId(empService.findById(clazz.getMasterId()).getId());

        clazzMapper.addclazz(clazz);

    }

    @Override
    public PageResult<Clazz> findall(ClazzQuaryParam clazzQuaryParam) {

        //1. 设置PageHelper分页参数
        PageHelper.startPage(clazzQuaryParam.getPage(), clazzQuaryParam.getPageSize());
        //2. 执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQuaryParam);
        //3. 封装分页结果
        Page<Clazz> p = (Page<Clazz>)clazzList;
        return new PageResult(p.getTotal(), p.getResult());

    }

    @Override
    public Clazz findById(Integer id) {
        return clazzMapper.findById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void updateClazz(Clazz clazz) {
            clazz.setUpdateTime(LocalDateTime.now());
            clazzMapper.updateClazz(clazz);
    }

    @Override
    public List<Clazz> list() {
        return clazzMapper.listall();
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        clazzMapper.deleteByIds(ids);
    }
}
