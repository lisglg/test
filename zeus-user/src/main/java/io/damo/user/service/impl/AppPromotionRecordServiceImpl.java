package io.damo.user.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.damo.user.dao.AppPromotionRecordDao;
import io.damo.user.entity.AppPromotionRecordEntity;
import io.damo.user.service.AppPromotionRecordService;
import org.springframework.stereotype.Service;

@Service("appPromotionRecordService")
public class AppPromotionRecordServiceImpl extends ServiceImpl<AppPromotionRecordDao, AppPromotionRecordEntity> implements AppPromotionRecordService {

}
