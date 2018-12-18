package io.damo.common.utils;

import java.math.BigDecimal;

/**
 * Created by caishun on 2018/3/19.
 */
public class RankUtils {

    /**
     * 保存投訴圖片地址
     */
    public static final String BANNER_PATH = "static/imgupload/";

    /**
     * 管理等级对应M值
     */
    public static class USERRANKM {
        //主管合伙人
        public static final double DIRECTOR_M = 0.05;
        //经理合伙人
        public static final double MANAGER_M = 0.1;
        //董事合伙人
        public static final double TRUSTEE_M = 0.15;
    }

    /**
     * 分红奖
     */
    public static class BONUS {
        //奖励的现金分
        public static final double CONSUMPTION_POINT = 1000;
        //1W~3W
        public static final double TEN_THOUSAND = 10000;
        public static final double THIRTY_THOUSAND = 30000;
        //3W~10W
        public static final double ONE_HUNDRED_THOUSAND = 100000;
    }

    /**
     * 比率
     */
    public static class RATE {
        //1%
        public static final double PERCENTILE = 0.01;
        //5%
        public static final double FIVE_PERCENT = 0.05;
        //10%
        public static final double TEN_PERCENT = 0.1;
        //40%
        public static final double FORTY_PERCENT = 0.4;
        //45%
        public static final double FORTY_FIVE_PERCENT = 0.45;
        //50%
        public static final double FIFTY_PERCENT = 0.5;
        //55%
        public static final double FIFTY_FIVE_PERCENT = 0.55;
        //60%
        public static final double SIXTY_PERCENT = 0.6;
    }

    //领导奖
    public static class MANAGEMENT {
        //1%
        public static final double PERCENTILE = 0.01;
        //3%
        public static final double THREE_PERCENT = 0.03;
        //5%
        public static final double FIVE_PERCENT = 0.05;
    }

    //分销奖
    public static class DISTRIBUTION {
        //1%
        public static final double PERCENTILE = 0.1;
        //3%
        public static final double FIFTEEN_PERCENT = 0.15;
    }

    // 平台税比率
    public static String PLATFORM_TAX_CODE = "PLATFORM_TAX_CODE";

    // 重消分比率
    public static String ELIMINATION_RETA = "ELIMINATION_RETA";

    // 提现奖金分比率
    public static String WITHDRAW_BONUS_POINTS = "WITHDRAW_BONUS_POINTS";

    // 现金分人民币比率
    public static String RMB_CASH_RATIO = "RMB_CASH_RATIO";


    //加
    public static BigDecimal ADD(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    //减
    public static BigDecimal SUBTRACT(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    //乘
    public static BigDecimal MULTIPLY(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    //除
    public static BigDecimal DIVIDE(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }


}
