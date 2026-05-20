package com.obviously20.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.obviously20.mapper.EmpMapper;
import com.obviously20.pojo.Emp;
import com.obviously20.pojo.EmpQueryParam;
import com.obviously20.pojo.PageResult;
import com.obviously20.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

//    @Override
//    public PageResult<Emp> page(Integer pageNum, Integer pageSize) {
//        //查询总记录数
//        Long count = empMapper.count();
//        //查询对应页数+每页显示条数的员工数据
//        //计算开始索引
//        Integer start = (pageNum - 1) * pageSize;
//        List<Emp> list = empMapper.list(start, pageSize);
//
//        return new PageResult<Emp>(count,list);
//    }

    /*
    * todo 使用pageHelper分页查询
    *  注意：- PageHelper实现分页查询时，SQL语句的结尾一定一定一定不要加分号(;).。
    *       - PageHelper只会对紧跟在其后的第一条SQL语句进行分页处理（如果再后面有其他SQL语句，不会进行分页处理）。
    *   底层原理：PageHelper会在执行查询前，先将查询参数（如pageNum、pageSize等）封装到Page对象中，然后执行查询。
    *       它会拿到原始的SQL语句进行改造：
    *       原始SQL语句：select emp.*,dept.name as deptName from emp left join dept on emp.dept_id = dept.id order by emp.update_time desc
    *       改造后查询总条数SQL语句：select count(*) from emp left join dept on emp.dept_id = dept.id
    *       改造后查询对应页数+每页显示条数的员工数据的SQL语句：select emp.*,dept.name as deptName from emp left join dept on emp.dept_id = dept.id order by emp.update_time desc limit #{page},#{pageSize}
    * */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //1.声明pageHelper分页查询：设定分页查询参数
//        PageHelper.startPage(page, pageSize);
//
//        //2.执行查询
//        List<Emp> list = empMapper.list(name,gender,begin,end);
//
//        //3.pageHelper会将查询结果封装到Page对象中，而Page对象中包含了分页信息total和data
//        Page<Emp> p = (Page<Emp>) list;
//
//        //4.返回分页结果
//        return new PageResult<Emp>(p.getTotal(),p.getResult());
//    }

    //优化1：上面的请求参数太多了，太复杂了，所以这里可以定义一个EmpQueryParam类，来接收用户输入的查询参数
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.声明pageHelper分页查询：设定分页查询参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2.执行查询
        List<Emp> list = empMapper.list(empQueryParam);

        //3.pageHelper会将查询结果封装到Page对象中，而Page对象中包含了分页信息total和data
        Page<Emp> p = (Page<Emp>) list;

        //4.返回分页结果
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    @Override
    public void save(Emp emp) {
        //1.保存员工基本信息
        //设置创建时间和修改时间为当前时间
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //执行插入操作
        empMapper.insert(emp);
        //2.保存员工的工作经历信息

    }

}
