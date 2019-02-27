package io.damo.user.service;


import com.baomidou.mybatisplus.service.IService;
import io.damo.user.entity.UserBasicInfoEntity;
import io.damo.user.form.LoginForm;
import io.damo.user.form.RegisterForm;
import io.damo.user.form.UpdatePasswordForm;

import java.util.Map;

/**
 * 用户基本信息表
 *
 * @author ives
 * @date 2018-06-11 14:46:53
 */
public interface UserBasicInfoService extends IService<UserBasicInfoEntity> {

    /**
     * 根据手机号码查询用户
     *
     * @param phone
     * @return
     */
    UserBasicInfoEntity selectByPhone(String phone);

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
    void saveUserMessage(RegisterForm form);

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId
     * @return
     */
    UserBasicInfoEntity selectByUserId(String userId);

    /**
     * 修改密码
     *
     * @param updatePasswordForm
     */
    void updatePassword(UpdatePasswordForm updatePasswordForm);

}

