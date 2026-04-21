package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.JobOption;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

//    /**
//     *     查询总记录数
//     */
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id;")
//    public long count();
//
//    /**
//     * 分页查询
//     */
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start,Integer pageSize);

    /**
     * 查询所有的员工及其对应的部门名称
     */
    //@Select("select e.*, d.name deptName from emp as e left join dept as d on e.dept_id = d.id")
    public List<Emp> list(EmpQueryParam empQueryParam);


    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void add(Emp emp);


    void deleteBatch(List<Integer> ids);

    Emp findById(Integer id);


    void update(Emp emp);

    //返回职位统计信息
    @MapKey("pos")
    List<Map<String,Object>> countByJob();

//    List<Map<String, Object>> countByGender();

    @MapKey("name")
    List<Map> countByGender();


    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);

    @Select("select * from emp where job=1")
    List<Emp> alllist();


}
