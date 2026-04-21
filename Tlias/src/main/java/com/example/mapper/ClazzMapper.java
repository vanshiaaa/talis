package com.example.mapper;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzQuaryParam;
import com.example.pojo.PageResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {



    //添加班级
//    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject) values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject})")
    void addclazz(Clazz clazz);



    List<Clazz> list(ClazzQuaryParam clazzQuaryParam);

    @Select("select * from clazz c LEFT JOIN emp e ON c.master_id = e.id where c.id =#{id}")
    Clazz findById(Integer id);

    void updateClazz(Clazz clazz);

    @Select("select * from clazz")
    List<Clazz> listall();

    void deleteByIds(List<Integer> ids);

    @MapKey("clazzList")
    List<Map<String, Object>> countStudentByClazz();

    List<Map> countByDegree();
}
