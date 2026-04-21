package com.example.controller;


import com.example.pojo.*;
import com.example.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    public static final Logger log = LoggerFactory.getLogger(ClazzController.class);


    @Autowired
    private ClazzService clazzService;


    //查询所有班级
    @GetMapping()
    public Result findall(ClazzQuaryParam clazzQuaryParam){
        log.info("分页查询所有班级");
        PageResult<Clazz> list = clazzService.findall(clazzQuaryParam);
        return Result.success(list);

    }

    //添加班级信息
    @PostMapping()
    public Result addClazz(@RequestBody Clazz clazz){
        log.info("添加班级信息，clazz="+clazz);
        clazzService.addclazz(clazz);
        return Result.success();

    }



    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询班级信息:{}", id);
        Clazz clazz = clazzService.findById(id);
        return Result.success(clazz);
    }


    //修改班级信息
    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz) {
        log.info("修改班级信息:{}", clazz);
        clazzService.updateClazz(clazz);
        return Result.success();
    }

    //查询所有班级
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有班级");
        List<Clazz> list = clazzService.list();
        return Result.success(list);
    }
    //批量删除班级
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids) {
        log.info("批量删除班级:{}", ids);
        clazzService.deleteByIds(ids);
        return Result.success();
    }
}
