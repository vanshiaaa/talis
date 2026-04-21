package com.example.service;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzQuaryParam;
import com.example.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    void addclazz(Clazz clazz);

    PageResult<Clazz> findall(ClazzQuaryParam clazzQuaryParam);

    Clazz findById(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> list();

    void deleteByIds(List<Integer> ids);
}
