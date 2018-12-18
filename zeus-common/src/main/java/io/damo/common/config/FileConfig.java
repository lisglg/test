package io.damo.common.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**  
 * <p>Title: FileConfig.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: 深圳天晟虹网络科技</p>  
 * @author zhangyubing  
 * @date 2018年5月6日  
 * @version 1.0  
 */
@Configuration
public class FileConfig {
	@Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("10MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("20MB");
        return factory.createMultipartConfig();
    }
}
