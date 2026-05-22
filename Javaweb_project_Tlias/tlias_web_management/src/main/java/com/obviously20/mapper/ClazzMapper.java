package com.obviously20.mapper;

import com.obviously20.pojo.Clazz;
import com.obviously20.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 添加班级:该方法用于添加班级信息
     */
//    @Insert("insert into clazz (name,room,begin_date,end_date,master_id,subject,create_time,update_time) values " +
//            "(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    /**
     * 查询所有班级:该方法用于查询所有班级信息
     * */
    List<Clazz> selectAll();

    /**
     * 班级列表查询
     * */
    List<Clazz> selectClazz(ClazzQueryParam clazzQueryParam);

    /**
     * 查询班级中学生数量
     * */
    @Select("select count(*) from student where clazz_id = #{id}")
    Integer selectStudentCount(Integer id);

    /**
     * 删除班级:该方法用于删除班级信息
     * */
    @Delete("delete from clazz where id = #{id}")
    void delete(Integer id);


    /**
     * 根据ID查询班级信息
     * */
    @Select("select id,name,room,begin_date,end_date,master_id,subject,create_time,update_time from clazz where id = #{id}")
    Clazz selectById(Integer id);

    /**
     * 修改班级信息
     * */
    void update(Clazz clazz);
}
