package io.damo.common.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

@Aspect //定义一个切面
@Component
public class BaseLog {

    @Before("execution(* io.damo.web..*(..)) || execution(* io.damo.systemConfig..*(..))|| execution(* io.damo.mall..*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("****************************controller 开始****************************");
        analysis(joinPoint);
        System.out.println("****************************controller 结束****************************");
    }

    public void analysis(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(!Objects.isNull(attributes)){
            HttpServletRequest request = attributes.getRequest();
            // 记录下请求内容
            System.out.println("URL : " + request.getRequestURL().toString());//请求路径
            System.out.println("HTTP_METHOD : " + request.getMethod());//请求方法类型
            System.out.println("IP : " + request.getRemoteAddr());//
            //aop 切点的方法
            System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
            //获取目标方法的参数信息
            Object[] obj = joinPoint.getArgs();
            for (Object o : obj) {
                System.out.println(o);
            }
        }
    }
}
