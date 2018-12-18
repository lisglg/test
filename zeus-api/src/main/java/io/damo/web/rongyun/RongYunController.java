package io.damo.web.rongyun;


import io.damo.common.annotation.Login;
import io.damo.common.annotation.LoginUser;
import io.damo.common.aspect.OpLogger;
import io.damo.common.message.MessageUtil;
import io.damo.common.response.CommonResponse;
import io.damo.common.utils.U;
import io.damo.user.entity.UserBasicInfoEntity;
import io.rong.RongCloud;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/RongYunController")
@Api(tags = "融云")
public class RongYunController {

    private static final String APP_KEY = "25wehl3u2sp4w";
    private static final String APP_SECRET = "of6lMfv3z5v";
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(RongYunController.class);

    @Login
    @GetMapping("/getRongYunTOken")
    @ApiOperation("获取融云token")
    @OpLogger
    public CommonResponse<String> getRongYunTOken(@ApiIgnore @LoginUser UserBasicInfoEntity userBasicInfoEntity) {
        logger.info("获取融云token：请求参数：用户id:{},用户名称:{}", userBasicInfoEntity.getId(), userBasicInfoEntity.getNickName());
        UserModel userModel = new UserModel();
        userModel.setId(userBasicInfoEntity.getId());
        userModel.setName(userBasicInfoEntity.getNickName());
        //获取融云需要头像url
        userModel.setPortrait("url");
        RongCloud rongCloud = RongCloud.getInstance(APP_KEY, APP_SECRET);
        try {
            //获取token
            TokenResult tokenResul = rongCloud.user.register(userModel);
            if (U.isBlank(tokenResul)) {
                return CommonResponse.fail(MessageUtil.getMessage("service.did.not.return.information"));
            } else if (tokenResul.getCode() == 200) {
                return CommonResponse.success("",tokenResul.getToken());
            } else {
                return CommonResponse.fail(tokenResul.msg);
            }
        } catch (Exception e) {
            logger.info("获取融云token异常：{}", e.getMessage());
            return CommonResponse.fail(MessageUtil.getMessage("get.the.cloud.token.exception"));
        }
    }


}
