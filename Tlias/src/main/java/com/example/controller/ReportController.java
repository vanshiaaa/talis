package com.example.controller;


import com.example.pojo.JobOption;
import com.example.pojo.Result;
import com.example.pojo.StudentCount;
import com.example.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {
    public static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;


    //统计员工职位人数
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        logger.info("获取员工职位数据");
        JobOption data = reportService.getEmpJobData();
        return Result.success(data);
    }

    //统计员工性别人数
    @GetMapping("/empGenderData")
    public Result getGenderData() {
        logger.info("统计员工性别信息");
        List<Map> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    //班级人数统计
    @GetMapping("/studentCountData")
    public Result getStudentCountData() {
        logger.info("获取班级人数统计数据");
        StudentCount studentCountList = reportService.getStudentCountData();
        return Result.success(studentCountList);
    }
    //学员学历统计
    @GetMapping("/studentDegreeData")
    public Result getDegreeData(){
        logger.info("统计学员学历信息");
        List<Map> degreeList =reportService.getStudentDegree();
        return Result.success(degreeList);
    }
}
