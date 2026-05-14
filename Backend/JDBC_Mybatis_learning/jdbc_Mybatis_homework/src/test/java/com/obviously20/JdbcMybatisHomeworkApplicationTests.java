package com.obviously20;

import com.obviously20.mapper.EmpMapper;
import com.obviously20.pojo.emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class JdbcMybatisHomeworkApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    void findByGenderAndJob() {
        List<emp> empList = empMapper.findByGenderAndJob(1, 2);
        for (emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    void findByEntryDateAndIdDesc() {
        List<emp> empList = empMapper.findByEntryDateAndIdDesc();
        for (emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    public void findByGenderAndSalary() {
        List<emp> empList = empMapper.findByGenderAndSalary(1, 6000);
        for (emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    public void findByGenderAndEntryDate() {
        List<emp> empList = empMapper.findByGenderAndEntryDate(1, LocalDate.parse("2005-10-01"), LocalDate.parse("2018-10-01"));
        for (emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    public void findByNameAndEntryDate() {
        //-- 5. 查询姓 '阮' 且 在 '2010-10-01' 之后入职的员工, 并根据入职时间进行升序排序, 并对结果分页操作, 展示第1页员工数据, 每页展示5条记录
        //页数（第几页）
        Integer pageNo = 1;
        //每页多少展示条
        Integer pageNum = 5;
        Integer pageIndex = (pageNo-1)*pageNum;
        List<emp> empList = empMapper.findByNameAndEntryDate("阮", LocalDate.parse("2005-10-01"), pageIndex, pageNum);
        for (emp emp : empList) {
            System.out.println(emp);
        }
    }

//    @Test
//    public void insert() {
//        emp emp = new emp(null,"ruanxin","1234567","阮鑫",1,"13800000000",2,6000,"111.jpg",LocalDate.parse("2026-05-01"),LocalDateTime.now(), LocalDateTime.now());
//        empMapper.insert(emp);
//    }

//    @Test
//    public void deleteById() {
//        Integer rows = empMapper.deleteById(1);
//        System.out.println(rows);
//    }

    @Test
    public void updatePasswordByIdAndUsername() {
        Integer rows = empMapper.updatePasswordByIdAndUsername(34, "ruanxin", "791355", LocalDateTime.now());
        System.out.println(rows);
    }

}
