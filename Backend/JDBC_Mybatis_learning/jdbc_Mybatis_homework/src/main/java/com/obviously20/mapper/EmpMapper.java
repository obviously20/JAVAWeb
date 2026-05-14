package com.obviously20.mapper;

import com.obviously20.pojo.emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmpMapper {

    //-- 1. 查询所有的性别为男(gender 为 1)的 讲师 (job 为 2) , 并根据入职时间, 对员工进行升序排序
    public List<emp> findByGenderAndJob(@Param("gender") Integer gender,@Param("job") Integer job);

    //-- 2. 根据 入职时间 对公司的员工进行 升序排序 ， 入职时间相同 , 再按照 ID 进行降序排序
    public List<emp> findByEntryDateAndIdDesc();

    //-- 3. 查询性别为男(gender 为 1) 且 薪资大于 6000 的员工, 并根据 入职时间 对公司的员工进行 升序排序 ， 入职时间相同 , 再按照 ID 进行降序排序
    public List<emp> findByGenderAndSalary(@Param("gender") Integer gender,@Param("salary") Integer salary);

    //-- 4. 查询性别为男(gender 为 1)的员工 且 在 '2005-10-01' 至 '2018-10-01' 之间入职的员工, 并根据 入职时间 对公司的员工进行 升序排序 ， 入职时间相同 , 再按照 ID 进行降序排序
    public List<emp> findByGenderAndEntryDate(@Param("gender") Integer gender, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    //-- 5. 查询姓 '阮' 且 在 '2010-10-01' 之后入职的员工, 并根据入职时间进行升序排序, 并对结果分页操作, 展示第1页员工数据, 每页展示5条记录
    public List<emp> findByNameAndEntryDate(@Param("surname") String surname, @Param("startDate") LocalDate startDate, @Param("pageIndex") Integer pageIndex, @Param("pageNum") Integer pageNum);

    //6.增加员工
    public Integer insert(emp emp);

    //7.根据ID删除员工
    public Integer deleteById(@Param("id") Integer id);

    //8.根据ID和用户名修改员工密码和最后修改时间
    public Integer updatePasswordByIdAndUsername(@Param("id") Integer id, @Param("username") String username, @Param("password") String password, @Param("update_time") LocalDateTime update_time);
}
