package io.damo.user.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import io.damo.common.utils.PageUtils;
import io.damo.user.entity.UserBasicInfoEntity;
import io.damo.user.form.LoginForm;
import io.damo.user.form.RegisterForm;

import java.util.Map;

/**
 * 用户基本信息表
 *
 * @author ives
 * @date 2018-06-11 14:46:53
 */
public interface UserBasicInfoService extends IService<UserBasicInfoEntity> {
    /**
     * 用户登录
     *
     * @param form 登录表单
     * @return 返回登录信息
     */
    Map<String, Object> login(LoginForm form);

    /**
     * 用户注册
     *
     * @param form 注册表单
     * @return 返回注册信息
     */
    boolean saveUserMessage(RegisterForm form);


}

