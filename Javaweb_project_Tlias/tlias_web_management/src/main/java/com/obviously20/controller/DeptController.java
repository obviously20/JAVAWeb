package com.obviously20.controller;

import com.obviously20.pojo.Dept;
import com.obviously20.pojo.Result;
import com.obviously20.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*todo lombok中提供的@Slf4j注解，可以简化定义日志记录器这步操作。添加了该注解，就相当于在类中定义了日志记录器，就下面这句代码：
    private static Logger log = LoggerFactory. getLogger(Xxx. class);*/
@Slf4j
/*我们会发现，我们在 DeptController 中所定义的方法，所有的请求路径，都是 /depts 开头的，只要操作的是部门数据，请求路径都是 /depts 开头。
那么这个时候，我们其实是可以把这个公共的路径 /depts 抽取到类上的，那在各个方法上，就可以省略了这个 /depts 路径*/
@RequestMapping("/depts")//todo 一个完整的请求路径，应该是类上的 @RequestMapping 的value属性 + 方法上的 @RequestMapping的value属性。
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /*
    * 部门列表查询
    * */
//    @RequestMapping(value = "/depts",method = RequestMethod.GET)//限定请求方法为GET
    //@GetMapping("/depts")//上面的简洁版本，限定请求方法为GET（此外，还可以使用@PostMapping、@DeleteMapping、@PutMapping等）
    @GetMapping
    public Result deptList(){
//        System.out.println("部门列表查询");//输出日志
        log.info("部门列表查询");//输出日志
        List<Dept> deptList = deptService.findDeptList();
        return Result.success(deptList);
    }


    //todo 获取简单参数的方法

//    /*
//     * 删除部门：根据id删除部门
//     * */
//    //todo 方法1：用HttpServletRequest获取请求参数--这种方案实现较为繁琐，而且还需要进行手动类型转换。【项目开发很少用】
//    @DeleteMapping("/depts")
//    public Result deptDelete(HttpServletRequest request){
//        System.out.println("删除部门");//输出日志
//        String idStr = request.getParameter("id");//.getParameter()方法获取请求参数id的值，返回值为String类型
//        Integer id = Integer.parseInt(idStr);//将String类型转换为Integer类型(手动)
////        deptService.deleteDeptById(id);
//        System.out.println("删除部门id为："+id);//输出日志
//        return Result.success();
//    }

//    /*
//     * 删除部门：根据id删除部门
//     * */
//    //todo 方法2：通过Spring提供的 @RequestParam 注解，将请求参数绑定给方法形参 --@RequestParam 注解的value属性，需要与前端传递的参数名保持一致 。
//    //todo @RequestParam注解required属性默认为true，代表该参数必须传递，如果不传递将报错。 如果参数可选，可以将属性设置为false。
//    //todo eg:@RequestParam("id",required = false)
//    @DeleteMapping("/depts")
//    public Result deptDelete(@RequestParam("id") Integer id){
//        System.out.println("删除部门");//输出日志
////        deptService.deleteDeptById(id);
//        System.out.println("删除部门id为："+id);//输出日志
//        return Result.success();
//    }

    /*
     * 删除部门：根据id删除部门
     * */
    //todo 方法3（推荐）：如果请求参数名与形参变量名相同，直接定义方法形参即可接收。（省略@RequestParam）
//    @DeleteMapping("/depts")
    @DeleteMapping
    public Result deptDelete(Integer id){
//        System.out.println("删除部门");//输出日志
        log.info("删除部门");//输出日志
        deptService.deleteDeptById(id);
//        System.out.println("删除部门id为："+id);//输出日志
        log.info("删除部门id为：{}",id);//输出日志
        return Result.success();
    }

    /*
     * 添加部门
     * */
    //todo 当前端传递的参数为JSON格式时（一般为POST请求、PUT请求等），
    // 需要使用一个实例对象接收参数（要求前端传递的JSON格式参数（key值）与实例对象的属性名保持一致）
    // 并且需要使用RequestBody注解，将请求体中的JSON格式参数绑定到对应的实例对象中的属性上。
//    @PostMapping("/depts")
    @PostMapping
    public Result deptAdd(@RequestBody Dept dept){
//        System.out.println("添加部门");//输出日志
//        System.out.println("添加部门信息为："+dept);//输出日志
        log.info("添加部门信息为：{}",dept);//输出日志
        deptService.addDept(dept);
        return Result.success();
    }

    // ----------------------------------------------------------------

    /**
     * 更新部门（查询回显+更新数据）
     */

    /**
     * 查询回显
     * 查询部门详情：根据id查询部门详情
     * */
    /*todo 路径参数接收
        /depts/1，/depts/2 这种在url中传递的参数，我们称之为路径参数。 那么如何接收这样的路径参数呢 ？
            路径参数：通过请求URL直接传递参数，使用{…}来标识该路径参数,{路径参数名}
            当然也可以接多个路径参数，多个路径参数之间用/隔开，例如：/depts/{id}/{name}
            用 @PathVariable获取路径参数（@PathVariable注解的value属性，需要与路径传递的参数名保持一致）。
     */
//    @GetMapping("/depts/{id}")
//    public Result getById(@PathVariable("id") Integer deptId){
//        System.out.println("查询部门详情");//输出日志
//        System.out.println("查询部门id为："+deptId);//输出日志
//        Dept dept = deptService.getById(deptId);
//        return Result.success(dept);
//    }

    //跟简洁版本:只要参数名和路径参数名一致，就可以直接省略PathVariable注解的value属性
//    @GetMapping("/depts/{id}")
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
//        System.out.println("查询部门详情");//输出日志
//        System.out.println("查询部门id为："+id);//输出日志
        log.info("查询部门id为：{}",id);//输出日志
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门数据
     * */
//    @PutMapping("/depts")
    @PutMapping
    public Result deptUpdate(@RequestBody Dept dept){
//        System.out.println("修改部门");//输出日志
//        System.out.println("将部门信息修改为："+dept);//输出日志
        log.info("将部门信息修改为：{}",dept);//输出日志
        deptService.updateDept(dept);
        return Result.success();
    }




















}
