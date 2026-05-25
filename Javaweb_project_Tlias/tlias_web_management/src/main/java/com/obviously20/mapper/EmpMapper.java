package com.obviously20.mapper;

import com.obviously20.pojo.Emp;
import com.obviously20.pojo.EmpQueryParam;
import com.obviously20.pojo.EmpVo;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    //-------------------------------原始分页查询-------------------------------
//    /**
//     * 查询出来的结果的总记录数
//     * */
//    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
//    public Long count();
//
//    /**
//     * 查询对应页数+每页显示条数的员工数据
//     * */
//    @Select("select emp.*,dept.name as deptName from emp left join dept on emp.dept_id = dept.id order by emp.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    //-------------------------------pageHelper分页查询-------------------------------
    /**
     * 查询对应页数+每页显示条数的员工数据
     * */
//    @Select("select emp.*,dept.name as deptName from emp left join dept on emp.dept_id = dept.id order by emp.update_time desc ")
    //SQL语句太复杂时，使用xml配置
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
    //优化1：上面的请求参数太多了，太复杂了，所以这里可以定义一个EmpQueryParam类，来接收用户输入的查询参数
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 添加员工
     * */
    //@Options(useGeneratedKeys = true, keyProperty = "id"): 自动获取主键值
    //todo 主键返回：@Options(useGeneratedKeys = true, keyProperty = "id")
    //  useGeneratedKeys: 是否自动获取主键值，默认值为false，即不自动获取主键值
    //  keyProperty: 主键值的属性名，默认值为null，即不自动获取主键值
    //  由于稍后，我们在保存工作经历信息的时候，需要记录是哪位员工的工作经历。 所以，保存完员工信息之后，是需要获取到员工的ID的，那这里就需要通过Mybatis中提供的主键返回功能来获取。
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) " +
            "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    /**
     * 查询全部员工
     * */
    @Select("select id,username,password,name,gender,image,job,salary,entry_date,dept_id,create_time,update_time from emp")
    List<EmpVo> selectList();


    /**
     * 批量删除员工（根据id列表）
     * */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工详情
     * */
    Emp selectById(Integer id);

    /**
     * 更新员工基本信息
     * */
    void updateById(Emp emp);

    /**
     * 员工职位人数统计
     * */
    @MapKey("pos")
    List<Map<String,Object>> countPosNum();

    /**
     * 员工性别人数统计
     * */
    @MapKey("name")
    List<Map> countGenderNum();

    /**
     * 根据用户名和密码查询员工是否存在
     * */
    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
