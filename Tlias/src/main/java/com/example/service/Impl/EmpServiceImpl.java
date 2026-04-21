package com.example.service.Impl;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpLogMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.*;
import com.example.service.EmpLogService;
import com.example.service.EmpService;
import com.example.utils.JWTUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
    @Override
    public PageResult<Emp> findall(EmpQueryParam empQueryParam) {
//        //1.调用mapper查询总记录数
//        long total = empMapper.count();
//
//        //2.调用mapper的分页查询方法，传入page和pageSize参数
//        Integer start = (page - 1) * pageSize;
//        List<Emp> list = empMapper.list(start, pageSize);
//
//        //3.返回查询结果
//        return new PageResult<Emp>(total, list);

        //1. 设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;

        //3. 封装结果
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Transactional
    @Override
    public void add(Emp emp) {
        try {
            //1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.add(emp);

            //2.保存员工工作经历
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);


        }
    }

    @Transactional
    @Override
    public void delete(List<Integer> ids) {
        //1.删除员工基本信息
        empMapper.deleteBatch(ids);

        //2.删除员工工作经历
        empExprMapper.deleteBatchByEmpIds(ids);
    }

    @Override
    public Emp findById(Integer id) {
        return empMapper.findById(id);

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
            //1.更新员工基本信息
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.update(emp);

            //2.删除原有的工作经历
            empExprMapper.deleteBatchByEmpIds(Arrays.asList(emp.getId()));

            //3.保存新的工作经历
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        if(empLogin != null){
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("id", empLogin.getId());
            dataMap.put("username", empLogin.getUsername());

            String jwt = JWTUtils.generateJwt(dataMap);
            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
            return loginInfo;
        }
        return null;
    }

    @Override
    public List<Emp> list() {
        List<Emp> allempList = empMapper.alllist();
        return allempList;
    }
}
