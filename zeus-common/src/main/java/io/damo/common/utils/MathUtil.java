package io.damo.common.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

public class MathUtil {

    public static final int scale = 2;

    public static void main(String[] args) {
      /*  try {*/
            System.out.println(decimalFormat(20));
       /* } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 数字格式化为百分比
     * @param
     * @return
     */
    public static String percentFormat(double value) {
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        return percent.format(value);
    }


    /**
     * 百分比格式化数字
     * @param baifenbi 百分比的值 100%
     * @return
     */
    public static Number percentFormat(String baifenbi) throws ParseException {
        NumberFormat nf = NumberFormat.getPercentInstance();
        return nf.parse(baifenbi);
    }

    /**
     * 百分比转为BigDecimal小数
     * @param amount
     * @return
     */
    public static BigDecimal decimalFormat(double amount){
        Double f = Double.valueOf(amount) / 100;
        BigDecimal decimal = new BigDecimal(f);
        return decimal.setScale(scale,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 波比率
     * @return
     */
    public static String dialingRatio(BigDecimal amount,BigDecimal amount1){
        BigDecimal a1 = new BigDecimal(amount + "").setScale(7, BigDecimal.ROUND_HALF_UP);
        BigDecimal a2 = new BigDecimal(amount1 + "").setScale(7, BigDecimal.ROUND_HALF_UP);
        BigDecimal r = a1.divide(a2, 4, BigDecimal.ROUND_HALF_EVEN).setScale(7, BigDecimal.ROUND_HALF_UP);
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        return percent.format(r.doubleValue());
    }


    //保留两位小数
    public static BigDecimal substr(double amount){
        BigDecimal d = new BigDecimal(Double.toString(amount));
        return d.setScale(scale,BigDecimal.ROUND_HALF_UP);
    }

    //两个数加法
    public static String add(double v1,double v2){
        BigDecimal d1 = new BigDecimal(Double.toString(v1));
        BigDecimal d2 = new BigDecimal(Double.toString(v2));
        return d1.add(d2).toString();
    }

    public static String add(String v1,String v2){
        BigDecimal d1 = new BigDecimal(v1);
        BigDecimal d2 = new BigDecimal(v2);
        return d1.add(d2).toString();
    }


    //三个数加法
    public static String add(String v1,String v2,String v3){
        BigDecimal d1 = new BigDecimal(v1);
        BigDecimal d2 = new BigDecimal(v2);
        BigDecimal d3 = new BigDecimal(v3);
        return d1.add(d2).add(d3).toString();
    }


    //乘法
    public static String mul(double v1,double v2){
        BigDecimal d1 = new BigDecimal(Double.toString(v1));
        BigDecimal d2 = new BigDecimal(Double.toString(v2));
        return d1.multiply(d2).setScale(scale,BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String mul3(double v1,double v2){
        BigDecimal d1 = new BigDecimal(Double.toString(v1));
        BigDecimal d2 = new BigDecimal(Double.toString(v2));
        return d1.multiply(d2).setScale(scale,BigDecimal.ROUND_HALF_UP).toString();
    }

    public static int mul2int(double v1,double v2){
        BigDecimal d1 = new BigDecimal(Double.toString(v1));
        BigDecimal d2 = new BigDecimal(Double.toString(v2));
        return d1.multiply(d2).setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
    }

    public static String mul(String v1,String v2){
        BigDecimal d1 = new BigDecimal(v1);
        BigDecimal d2 = new BigDecimal(v2);
        return d1.multiply(d2).setScale(scale,BigDecimal.ROUND_HALF_UP).toString();
    }


    public static String movePointRight(String v1,int scale){
        BigDecimal d1 = new BigDecimal(v1).movePointRight(scale);
        return d1.toString();
    }

    public static String movePointLeft(String v1,int scale){
        BigDecimal d1 = new BigDecimal(v1).movePointLeft(scale);
        return d1.setScale(scale).toString();
    }

    public static Boolean compareTo(String enableAmount, String retunrMoney) {
        BigDecimal a = new BigDecimal(enableAmount);
        BigDecimal b = new BigDecimal(retunrMoney);
        if(a.compareTo(b)==0)
        {
            return true;
        }
        else return a.compareTo(b) == 1;
    }



}
