package cn.dubbo.test;

import cn.dubbo.common.bean.Blog;
import cn.dubbo.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicaitonTest {

    @Autowired
    BlogService blogService;

    @Test
    public void test1(){
        List<Blog> list = blogService.getALLBlog();
        System.out.println(list.size());
    }
}
