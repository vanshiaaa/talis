package com.example.mapper;


import com.example.pojo.Dept;
import com.example.pojo.Result;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id,name,create_time,update_time from dept order by update_time desc")
    List<Dept> findall();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);


    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    @Select("select id,name,create_time,update_time from dept where id = #{id}")
    Dept GetById(Integer id);

    @Insert("update dept set name=#{name},update_time=#{updateTime} where id = #{id}")
    void update(Dept dept);
}
