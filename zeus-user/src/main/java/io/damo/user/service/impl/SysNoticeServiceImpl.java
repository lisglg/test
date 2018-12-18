package io.damo.user.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.damo.user.dao.SysNoticeDao;
import io.damo.user.entity.SysNoticeEntity;
import io.damo.user.service.SysNoticeService;
import org.springframework.stereotype.Service;

@Service("sysNoticeService")
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeDao, SysNoticeEntity> implements SysNoticeService {

}
