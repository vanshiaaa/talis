package com.example.service;

import com.example.pojo.JobOption;
import com.example.pojo.StudentCount;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map> getEmpGenderData();

    StudentCount getStudentCountData();

    List<Map> getStudentDegree();
}
