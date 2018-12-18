package io.damo.web.rongyun;


import io.damo.common.aspect.OpLogger;
import io.damo.common.message.MessageUtil;
import io.damo.common.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@RestController
@RequestMapping("/api/LanguageController")
@Api(tags = "国际化")
public class LanguageController {


    private static MessageSource source;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(LanguageController.class);

    @GetMapping("/changeLanguage")
    @ApiOperation("切换语言")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "languageType", value = "语言类型(0.中文 1.英文)", required = true)})
    @OpLogger
    public CommonResponse<String> changeLanguage(HttpSession session, Integer languageType) {
        logger.info("切换语言：请求参数{}", languageType);
        Locale locale = null;

        // 中文

        if (languageType == 1) {
            locale = new Locale("en", "US");
        }else {
            locale = new Locale("zh", "CN");
        }

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/message");

        MessageUtil.locale = locale;
        MessageUtil.source = messageSource;

        return CommonResponse.success(MessageUtil.getMessage("change.success"));
    }
}
