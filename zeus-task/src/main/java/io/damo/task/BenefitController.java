package io.damo.task;


import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableTransactionManagement
@Component
@RestController
@RequestMapping("BenefitController")
@Api(tags = "钱包job")
public class BenefitController {

    private static Logger logger = LoggerFactory.getLogger(BenefitController.class);



    /**
     * 重置红包状态 每天凌晨00:01:00点执行
     */
    /*
    @Scheduled(cron = "0 1 0 * * ?")
    @Transactional
    public synchronized void exeTask1() {
        userAccountInfoService.updateRedStatus();
    }
    */


}
