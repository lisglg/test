package io.damo.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.damo.user.dao.TokenDao;
import io.damo.user.entity.TokenEntity;
import io.damo.user.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service("tokenService")
public class TokenServiceImpl extends ServiceImpl<TokenDao, TokenEntity> implements TokenService {

    /**
     * 12小时后过期
     */
    private final static int EXPIRE = 3600 * 12;

    @Override
    public TokenEntity queryByToken(String token, Long tokenType) {
        return this.selectOne(new EntityWrapper<TokenEntity>().eq("token", token).eq("token_type", tokenType));
    }

    @Override
    public TokenEntity createToken(String userId, Long tokenType) {
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //生成token
        String token = generateToken();

        //保存或更新用户token
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        tokenEntity.setUpdateTime(now);
        tokenEntity.setExpireTime(expireTime);
        tokenEntity.setTokenType(tokenType);
        this.insertOrUpdate(tokenEntity);

        return tokenEntity;
    }

    @Override
    public void expireToken(String userId){
        Date now = new Date();

        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setUpdateTime(now);
        tokenEntity.setExpireTime(now);
        this.insertOrUpdate(tokenEntity);
    }

    private String generateToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }


}
