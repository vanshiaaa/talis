package com.example.controller;


import com.example.pojo.*;
import com.example.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Slf4j
public class StuController {

    @Autowired
    private StuService stuService;

    //查询所有学生
    @GetMapping()
    public Result list(StuQuaryParam stuQuaryParam){
        log.info("查询所有学生");
        PageResult<Clazz> list = stuService.findall(stuQuaryParam);
        return Result.success(list);


    }

    //新增学生
    @PostMapping()
    public Result addStu(@RequestBody Student student){
        log.info("新增学生");
        stuService.addStu(student);
        return Result.success();
    }
    //根据id查询学生
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询学生");
        Student student = stuService.findById(id);
        return Result.success(student);
    }

     //根据id修改学生
    @PutMapping
    public Result updateById(@RequestBody Student student){
        log.info("根据id修改学生");
        stuService.updateStu(student);
        return Result.success();
     }
     //批量删除学生
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        log.info("批量删除学生");
        stuService.deleteByIds(ids);
        return Result.success();
     }
     //违纪扣分处理
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,@PathVariable Integer score){
//        if(score  == null) {
//            score = "0";
//        }
        log.info("违纪扣分处理");
        stuService.violation(id,score);
        return Result.success();
     }
}
