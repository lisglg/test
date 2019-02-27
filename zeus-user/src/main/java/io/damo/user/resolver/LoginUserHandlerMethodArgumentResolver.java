package io.damo.user.resolver;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.damo.common.annotation.Login;
import io.damo.common.annotation.LoginUser;
import io.damo.common.exception.RRException;
import io.damo.user.entity.SysUserEntity;
import io.damo.user.entity.TokenEntity;
import io.damo.user.entity.UserBasicInfoEntity;
import io.damo.user.interceptor.AuthorizationInterceptor;
import io.damo.user.service.SysUserService;
import io.damo.user.service.TokenService;
import io.damo.user.service.UserBasicInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserBasicInfoService userService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private TokenService tokenService;
    private static Logger logger= LoggerFactory.getLogger(LoginUserHandlerMethodArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.getParameterType().isAssignableFrom(UserBasicInfoEntity.class) || parameter.getParameterType().isAssignableFrom(SysUserEntity.class))
                && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) {
        Login annotation;
        //获取用户ID
        Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if (object == null) {
            EntityWrapper<TokenEntity> entityWrapper=new EntityWrapper<>();
            logger.info("获取前端token请求参数:token:{},类型:{},方法名:{}", request.getHeader("token"),parameter.getMethod().getName());
            //从header中获取token
            String token = request.getHeader("token");
            logger.info("根据前端token查询响应结果:{}", token);
            entityWrapper.where("token={0}",token);
            //查询token信息
            TokenEntity tokenEntity =tokenService.selectOne(entityWrapper);
            if(null==tokenEntity){
                return null;
            }
            object=tokenEntity.getUserId();
        }
        if (parameter.getParameterType().isAssignableFrom(UserBasicInfoEntity.class)) {
          UserBasicInfoEntity userBasicInfoEntity =  userService.selectById(object.toString());
            if(userBasicInfoEntity.getState() == 1 ){
                tokenService.deleteById(userBasicInfoEntity.getId());
                throw new RRException("token不能为空",401);
            }else{
                return userBasicInfoEntity;
            }
        } else if (parameter.getParameterType().isAssignableFrom(SysUserEntity.class)) {
            //return sysUserService.getSysUserById(object.toString());
        }
        return null;
    }
}
