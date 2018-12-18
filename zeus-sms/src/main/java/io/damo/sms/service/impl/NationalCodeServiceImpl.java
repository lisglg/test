package io.damo.sms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import io.damo.sms.dao.NationalCodeDao;
import io.damo.sms.entity.NationalCodeEntity;
import io.damo.sms.service.NationalCodeService;


@Service("nationalCodeService")
public class NationalCodeServiceImpl extends ServiceImpl<NationalCodeDao, NationalCodeEntity> implements NationalCodeService {

    @Autowired
    private NationalCodeDao nationalCodeDao;

    @Override
    public List<NationalCodeEntity> getAll() {
        return nationalCodeDao.getAll();
    }

}
