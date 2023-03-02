package com.yupi.usercenter;

import org.junit.Assert;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
//@RunWith(SpringRunner.class)//如果用的是org.junit.Test这个包，就得使用这个注解才能自定义运行springboot项目;或者换个@Test不同的包org.junit.jupiter.api.Test;
public class SampleTest {

//    @Resource
//    private UserMapper userMapper;


//    @Test
//    public void testSelect() {
//        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
//        //断言，就是我觉得（我预期的结果，必须是这样，不然就报错，用来判断实际的操作跟你预期的结果是否一样）
//        Assert.assertEquals(5, userList.size());
//        userList.forEach(System.out::println);
//    }

}