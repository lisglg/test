package io.damo.sms.dao;

import io.damo.sms.entity.NationalCodeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

/**
 * 国际区号信息
 * 
 * @author ives
 * @date 2018-03-07 14:14:47
 */
public interface NationalCodeDao extends BaseMapper<NationalCodeEntity> {

    List<NationalCodeEntity> getAll();

}
