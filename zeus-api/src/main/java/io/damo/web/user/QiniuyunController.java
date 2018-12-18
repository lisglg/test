package io.damo.web.user;


import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import io.damo.common.aspect.OpLogger;
import io.damo.common.response.CommonResponse;
import io.damo.common.utils.DateUtils;
import io.damo.common.utils.QiniuUtil;
import io.damo.user.vo.QiniuTokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/qiniuyunController")
@Api(tags = "七牛云")
public class QiniuyunController {

    private static Logger logger = LoggerFactory.getLogger(QiniuyunController.class);
    @GetMapping("/getToken")
    @ApiOperation("获取凭证")
    @OpLogger
    public CommonResponse<QiniuTokenVo> getToken(@RequestParam("urlName") String urlName) {
        logger.info("获取凭证：图片名：{}",urlName);
        Auth auth = Auth.create(QiniuUtil.ACCESS_KEY, QiniuUtil.SECRET_KEY);
        StringMap putPolicy = new StringMap();
        putPolicy.put("callbackUrl", "http://182.61.23.57:9090/damo-api/api/qiniuyunController/callback");
        putPolicy.put("callbackBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        putPolicy.put("callbackBodyType", "application/json");
        String uuid = DateUtils.format(DateUtils.now(), DateUtils.FULL_READ_INDENT_PATTERN);
        urlName = "GKpay/" +uuid+urlName;
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(QiniuUtil.BUCKET, urlName, expireSeconds, putPolicy);
        QiniuTokenVo qiniuTokenVo = new QiniuTokenVo();
        qiniuTokenVo.setToken(upToken);
        qiniuTokenVo.setUrlName(urlName);
        return CommonResponse.success(qiniuTokenVo);
    }

    @PostMapping("/callback")
    @ApiOperation("回调")
    @OpLogger
    public void callback(HttpServletRequest request, HttpServletResponse response) {
        logger.info("七牛云回调");
        Auth auth = Auth.create(QiniuUtil.ACCESS_KEY, QiniuUtil.SECRET_KEY);
        //回调地址
        String callbackUrl = "http://182.61.23.57:9090/damo-api/api/qiniuyunController/callback";
        //定义回调内容的组织格式，与上传策略中的callbackBodyType要保持一致
        String callbackBodyType = "application/json";
        //通过获取请求的HTTP头部Authorization字段获得
        String callbackAuthHeader = request.getHeader("Authorization");
        //通过读取回调POST请求体获得，不要设置为null
        byte[] callbackBody = new byte[1024];
        try {
            //这里是最重要的，接收byte[]
            request.getInputStream().read(callbackBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将byte[]转化为字符串
          String callbackBodyStr = new String(callbackBody);
        logger.info("七牛云回调：callbackBodyStr：{}",callbackBodyStr);
        //检查是否为七牛合法的回调请求
        boolean validCallback = auth.isValidCallback(callbackAuthHeader, callbackUrl, callbackBody, callbackBodyType);
        logger.info("检查是否为七牛合法的回调请求：validCallback：{}",validCallback);
        if (!validCallback) {
            response.setStatus(500);
        }
    }
}
