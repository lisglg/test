package io.damo.user.interceptor;

import io.damo.common.annotation.Login;
import io.damo.common.exception.RRException;
import io.damo.common.message.MessageUtil;
import io.damo.common.utils.StringUtil;
import io.damo.sms.utils.HttpUtil;
import io.damo.user.entity.TokenEntity;
import io.damo.user.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 *
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenService tokenService;
    private static Logger logger= LoggerFactory.getLogger(AuthorizationInterceptor.class);

    public static final String USER_KEY = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Login annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        }else{
            return true;
        }

        if(annotation == null){
            return true;
        }
        logger.info("获取前端token请求参数:token:{},类型:{},请求ip：{}", request.getHeader("token"),annotation.type(), HttpUtil.getIpAdrress(request));
        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtil.isEmpty(token)){
            token = request.getParameter("token");
        }

        //token为空
        if(StringUtil.isEmpty(token)){
            throw new RRException("token不能为空",401);
        }
        logger.info("根据前端token查询响应结果:{}", token,annotation.type());
        //查询token信息
        TokenEntity tokenEntity = tokenService.queryByToken(token,annotation.type());
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new RRException(MessageUtil.getMessage("token.error.one"),401);
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(USER_KEY, tokenEntity.getUserId());

        return true;
    }
}
