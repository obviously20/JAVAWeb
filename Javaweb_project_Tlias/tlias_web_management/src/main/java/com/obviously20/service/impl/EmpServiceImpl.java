package com.obviously20.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.obviously20.mapper.EmpExprMapper;
import com.obviously20.mapper.EmpMapper;
import com.obviously20.pojo.*;
import com.obviously20.service.EmpLogService;
import com.obviously20.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;



@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

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

    //todo @Transactional:就是在当前这个方法执行开始之前来开启事务，方法执行完毕之后提交事务。如果在这个方法执行的过程当中出现了异常，就会进行事务的回滚操作。
    //位置：业务层的方法上、类上、接口上
    //  - 方法上：当前方法交给spring进行事务管理(如果一个方法中包含了多个数据库操作，那么就建议在方法上添加@Transactional注解，来开启事务)
    //  - 类上：当前类中所有的方法都交由spring进行事务管理
    //  - 接口上：接口下所有的实现类当中所有的方法都交给spring 进行事务管理

    //todo rollbackFor:指定在哪些异常出现时，才会进行事务的回滚操作。默认值是RuntimeException.class(运行时异常)
    // 注意：如果在方法中出现了非运行时异常，那么就不会进行事务的回滚操作。
    // 所以，设置rollbackFor = {Exception.class}，就可以在方法中出现任何异常时，都进行事务的回滚操作。
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            //1.保存员工基本信息
            //设置创建时间和修改时间为当前时间
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            //执行插入操作
            empMapper.insert(emp);

            int i = 1/0;

            //2.保存员工的工作经历信息
            Integer empId = emp.getId();
            //先获取员工的工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            //看获取到的工作经历信息是否为空
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历工作经历列表，将每个工作经历的员工ID设置为当前员工的ID
                exprList.forEach(expr -> expr.setEmpId(empId));
                //执行插入操作
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);
        }
    }

}
