package com.example.controller;


import com.example.anno.LogOperation;
import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
public class DeptController {

    //创建日志对象
    public static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;


    @LogOperation
    @RequestMapping("/depts")
    public Result list(){
        log.info(LocalDateTime.now() + " : 查询部门列表...");
        List<Dept> Depts = deptService.findall();

        return Result.success(Depts);
    }

    @LogOperation
    @DeleteMapping("/depts")
    public Result delete(Integer id){
        log.info(LocalDateTime.now() + " : 删除部门，id="+id);
        deptService.deleteById(id);
        return Result.success();
    }

    @LogOperation
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info(LocalDateTime.now() + " : 添加部门，dept="+dept);
        deptService.add(dept);
        return Result.success();
    }

    @LogOperation
    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable Integer id){
        log.info(LocalDateTime.now() + " : 根据id查询部门，id="+id);
        Dept dept= deptService.getById(id);
        return Result.success(dept);
    }

    @LogOperation
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info(LocalDateTime.now() + " : 更新部门，dept="+dept);
        deptService.update(dept);
        return Result.success();
    }
}
