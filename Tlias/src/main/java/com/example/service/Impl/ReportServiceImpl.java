package com.example.service.Impl;

import com.example.mapper.ClazzMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.JobOption;
import com.example.pojo.StudentCount;
import com.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public JobOption getEmpJobData() {
        //1.调用mapper查询员工职位数据
        List<Map<String, Object>> list = empMapper.countByJob();

        //2.封装成JobOption对象并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);

    }

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countByGender();
    }

    @Override
    public StudentCount getStudentCountData() {
        //1.调用mapper查询班级人数统计数据
        List<Map<String, Object>> list = clazzMapper.countStudentByClazz();
        //2.封装成StudentCount对象并返回
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazzList")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("dataList")).toList();
        return new StudentCount(clazzList, dataList);
    }

    @Override
    public List<Map> getStudentDegree() {
        return clazzMapper.countByDegree();
    }
}
