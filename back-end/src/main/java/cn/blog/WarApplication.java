package cn.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//命令行启动（到项目文件夹中）：mvn spring-boot:run		关闭：ctrl+C
@SpringBootApplication
//@EnableScheduling
@EnableAsync
@EnableTransactionManagement		//声明式事务
@EnableCaching      //声明式缓存
@Controller
@MapperScan("cn.blog.dao")
public class WarApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarApplication.class, args);
	}

}