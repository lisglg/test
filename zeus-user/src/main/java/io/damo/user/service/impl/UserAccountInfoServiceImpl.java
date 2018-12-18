package io.damo.user.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.damo.user.dao.UserAccountInfoDao;
import io.damo.user.entity.UserAccountInfoEntity;
import io.damo.user.service.UserAccountInfoService;
import org.springframework.stereotype.Service;

@Service("userAccountInfoService")
public class UserAccountInfoServiceImpl extends ServiceImpl<UserAccountInfoDao, UserAccountInfoEntity> implements UserAccountInfoService {


}
