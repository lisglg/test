package io.damo.common.annotation;

import java.lang.annotation.*;

/**
 * 登录效验
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
	/**
	 * token类型(1:普通用户token 2:系统用户token)
	 * @return
	 */
	long type() default 1;
}
