package com.obviously20;

import com.obviously20.mapper.UserMapper;
import com.obviously20.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest//SpringBoot测试注解-当前测试类的测试方法运行时，会自动加载SpringBoot项目->就会有IOC容器(直接从IOC容器中获取Bean)
class SpringBootMybatisDemoApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testFindAll() {
		List<User> users = userMapper.findAll();
		users.forEach(System.out::println);
	}

	@Test
	public void testDeleteById() {
		Integer rows = userMapper.deleteById(5);
		System.out.println(rows);
	}

}
