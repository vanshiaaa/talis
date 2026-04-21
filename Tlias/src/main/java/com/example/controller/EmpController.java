package com.example.controller;


import com.example.anno.LogOperation;
import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/emps")
public class EmpController {
    private static final Logger log = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    private EmpService empService;

    //分页查询所有员工
//    @GetMapping
//    public Result findall(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
//                          String name, Integer gender,
//                          @DateTimeFormat(pattern = "yyyy-MM-ss") LocalDate begin,
//                          @DateTimeFormat(pattern = "yyyy-MM-ss") LocalDate end){
//        log.info("分页查询所有员工");
//        PageResult<Emp> list = empService.findall(page, pageSize,name,gender,begin,end);
//        return Result.success(list);
//    }

    //分页查询所有员工 用实体类传递
    @GetMapping
    public Result findall(EmpQueryParam empQueryParam){
        log.info("分页查询所有员工");
        PageResult<Emp> list = empService.findall(empQueryParam);
        return Result.success(list);
    }

    @LogOperation
    @PostMapping
    public Result add(@RequestBody Emp emp){
        log.info("添加员工");
        empService.add(emp);
        return Result.success();
    }


    //批量删除员工
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("批量删除员工:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    //查询回显（根据id查询员工信息）
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询员工信息:{}", id);
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }

    //修改员工信息与工作经历
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工信息:{}", emp);
        empService.update(emp);
        return Result.success();
    }

    //查询所有员工
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有员工");
        List<Emp> list = empService.list();
        return Result.success(list);
    }



}
