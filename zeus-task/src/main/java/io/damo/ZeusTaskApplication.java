package io.damo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@MapperScan(basePackages = {"io.damo.*.dao"})
public class ZeusTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZeusTaskApplication.class, args);
	}
}
