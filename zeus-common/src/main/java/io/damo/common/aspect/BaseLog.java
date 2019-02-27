package io.damo.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect //定义一个切面
@Component
@Slf4j
public class BaseLog {

	@Before("execution(* io.damo.web..*(..)) || execution(* io.damo.systemConfig..*(..))")
	public void before(JoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		String fullName = className + "." + methodName;
		log.info("*******请求方法{}开始*******", fullName);
		analysis(joinPoint);
		log.info("*******请求方法{}结束*******", fullName);
	}

	public void analysis(JoinPoint joinPoint) {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		StringBuffer sb = new StringBuffer();
		if (!Objects.isNull(attributes)) {
			HttpServletRequest request = attributes.getRequest();
			sb.append("[IP:" + request.getRemoteAddr() + "]");
			sb.append("[METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "]");
			//获取目标方法的参数信息
			Object[] obj = joinPoint.getArgs();
			if (!(obj == null || obj.length == 0)) {
				for (Object o : obj) {
					sb.append("[");
					if (!Objects.isNull(o)) {
						sb.append(o.toString());
					}
					sb.append("]");
				}
			}
			log.info(sb.toString());
		}
	}
}
