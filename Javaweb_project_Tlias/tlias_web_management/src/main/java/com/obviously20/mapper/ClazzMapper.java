package com.obviously20.mapper;

import com.obviously20.pojo.Clazz;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
