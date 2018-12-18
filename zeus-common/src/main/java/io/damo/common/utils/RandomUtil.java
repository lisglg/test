package io.damo.common.utils;

import java.util.Date;
import java.util.Random;

/**
 * Created by pinshiern on 2017/7/31.
 */
public class RandomUtil {
    public RandomUtil() {
    }

    public static String getCode(int length) {
        if(length < 1 || length > 10) {
            return null;
        } else {
            int max = (int)Math.pow(10.0D, (double)length);
            int rand = (new Random()).nextInt(max);
            return String.valueOf(max + rand).substring(1);
        }
    }

    public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 生成钱包地址 格式：//4位固定数 wallet＋年月日时分秒毫秒＋5位随机数
     * @return
     */
    public static String ValletUrl(){
        Random ne=new Random();
        int x = ne.nextInt(99999-10000+1)+10000;
        return DateUtils.format( new Date(), DateUtils.FULL_READ_INDENT_PATTERN)+x;
    }

    /**
     * 生成美佳id 格式：//4位固定数 wallet＋年月日时分秒毫秒＋5位随机数
     * @return
     */
    public static String getMJSysUUID(){
        Random ne=new Random();
        int x = ne.nextInt(99999-10000+1)+10000;
        return "walletSys"+DateUtils.format( new Date(), DateUtils.FULL_READ_INDENT_PATTERN)+x;
    }
    /**
     * 生成美佳会员ID 格式：//4位固定数 mj＋年月日时分秒毫秒＋5位随机数
     * @return
     */
    public static String generatorUserId(){
        Random ne=new Random();
        int x = ne.nextInt(99999-10000+1)+10000;
        return "wallet"+DateUtils.format( new Date(), DateUtils.FULL_READ_INDENT_PATTERN)+x;
    }


}
