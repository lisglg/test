package io.damo.web.user;


import io.damo.user.service.UserAccountInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户账户表
 *
 * @author ives
 * @date 2018-06-11 14:46:53
 */
@RestController
@RequestMapping("/api/userAccountInfoController")
@Api(tags = "用户账户表")
public class UserAccountInfoController {
    @Autowired
    private UserAccountInfoService userAccountInfoService;

    private static Logger logger = LoggerFactory.getLogger(UserAccountInfoController.class);


}
