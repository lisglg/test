package io.damo.common.config;

import io.damo.common.filter.DomainFilter;
import io.damo.common.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

/**
 * Filter配置
 */
@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean xssFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setDispatcherTypes(DispatcherType.REQUEST);
		registration.setFilter(new XssFilter());
		registration.addUrlPatterns("/api/*");
		// 剔除特殊请求路径
//		StringBuffer excludedUriStr = new StringBuffer();
//		excludedUriStr.append("damo-web/web/noticeManagerController/publishNotice");
//		excludedUriStr.append(",");
//		excludedUriStr.append("damo-web/web/noticeManagerController/updateNotice");
//		registration.addInitParameter("excludedUri", excludedUriStr.toString());
		registration.setName("xssFilter");
		return registration;
	}

	@Bean
	public FilterRegistrationBean domainFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new DomainFilter());
		registration.addUrlPatterns("/*");
		registration.setName("domainFilter");
		return registration;
	}

}
