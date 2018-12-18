package io.damo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan(basePackages = {"io.damo.*.dao"})
public class ApiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApiApplication.class);
	}

	// main
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
