package io.damo.sms.service;

import com.baomidou.mybatisplus.service.IService;
import io.damo.sms.entity.NationalCodeEntity;
import java.util.List;

/**
 * 国际区号信息
 *
 * @author ives
 * @date 2018-03-07 14:14:47
 */
public interface NationalCodeService extends IService<NationalCodeEntity> {

    List<NationalCodeEntity> getAll();

}

