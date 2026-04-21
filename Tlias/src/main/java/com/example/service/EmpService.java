package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.LoginInfo;
import com.example.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageResult<Emp> findall(EmpQueryParam enpQueryParam);

    void add(Emp emp);

    void delete(List<Integer> ids);

    Emp findById(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);

    List<Emp> list();
}
