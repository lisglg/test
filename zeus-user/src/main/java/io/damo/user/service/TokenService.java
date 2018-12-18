package io.damo.user.service;

import com.baomidou.mybatisplus.service.IService;
import io.damo.user.entity.TokenEntity;

/**
 * 用户Token
 *
 * @author ives
 * @date 2018-06-07 11:38:28
 */
public interface TokenService extends IService<TokenEntity> {
    TokenEntity queryByToken(String token, Long tokenType);

    /**
     * 生成token
     * @param userId  用户ID
     * @return        返回token信息
     */
    TokenEntity createToken(String userId, Long tokenType);

    /**
     * 设置token过期
     * @param userId 用户ID
     */
    void expireToken(String userId);
}

