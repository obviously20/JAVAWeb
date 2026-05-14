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

//	@Test
//	public void testInsert() {
//		User user = new User(null,"zhugeliang","654321","诸葛亮",30);
//		Integer rows = userMapper.insert(user);
//		System.out.println(rows);
//	}


//	@Test
//	public void testUpdate() {
//		User user = new User(4,"guanyu","654321","关羽",33);
//		Integer rows = userMapper.update(user);
//		System.out.println(rows);
//	}

	@Test
	public void testFindByNameAndPassword() {
		User user = userMapper.findByNameAndPassword("关羽","654321");
		System.out.println(user);
	}

	@Test
	public void testUpdateById() {
		Integer rows = userMapper.updateById("66888","关羽",32,4);
		System.out.println(rows);
	}

}
