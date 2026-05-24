package com.obviously20.mapper;

import com.obviously20.pojo.Student;
import com.obviously20.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {


    /**
     * 分页查询学生
     * */
    List<Student> select(StudentQueryParam studentQueryParam);

    /**
     * 删除学员
     * */
    void delete(List<Integer> ids);


    /**
     * 添加学员
     * */
    void insert(Student student);


    /**
     * 根据ID查询
     * */
    @Select("SELECT id, name, no, gender, phone, id_card, is_college, address, degree, " +
            "graduation_date, clazz_id, violation_count, violation_score, create_time, update_time " +
            "FROM student WHERE id = #{id}")
    Student selectById(Integer id);


    /**
     * 修改学员
     * */
    void update(Student student);

    /**
     * 违纪处理
     * */
    void violation(Integer id, Integer score);



    /**
     * 统计学员学历
     * */
    @MapKey("name")
    List<Map> countDegreeNum();

    /**
     * 统计班级人数
     * */
    @MapKey("clazz")
    List<Map<String, Object>> countClazzNum();
}
