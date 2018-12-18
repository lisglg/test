
package io.damo.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 金额单位转换
 * @author qq
 *
 */
public class MoneyUnitChange {
	private static final Logger logger = LoggerFactory.getLogger(MoneyUnitChange.class);
	private static final String UNIT = "万千佰拾亿千佰拾万千佰拾元角分";
	private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";
	private static final double MAX_VALUE = 9999999999999.99D;
	private static final int scale = 20;



	/**
	 * 获得转换前的报单金额
	 * long 除以 100
	 */
	public static BigDecimal longDivideHundred(long l) {
		return new BigDecimal(l).divide(new BigDecimal(100), scale, BigDecimal.ROUND_UP);
	}

	public static BigDecimal longDivideHundred(BigDecimal b) {
		return b.divide(new BigDecimal(100), scale, BigDecimal.ROUND_UP);
	}

	/**
	 * 转换报单金额为整型类型用于存放在数据库中
	 * long 乘以 100
	 *
	 * @param l
	 * @return
	 */
	public static BigDecimal longMultiplyHundred(double l) {
		return new BigDecimal(l).multiply(new BigDecimal(100));
	}

	public static BigDecimal longMultiplyHundred(BigDecimal b) {
		return b.multiply(new BigDecimal(100));
	}

	/**
	 * 保留小数位的金额（供前端使用）
	 * @param amount
	 * @param rate
	 * @return
	 */
	public static BigDecimal amount(long amount,long rate){
		return new BigDecimal(amount).divide(new BigDecimal(rate), 20, BigDecimal.ROUND_UP);
	}


	/**
	 * 分转元
	 */
	public static Double fenToYuan(Long amount){
		if(amount == null){
			return 0d;
		}
		return new BigDecimal(amount).divide(new BigDecimal(100)).setScale(2).doubleValue();
	}

	/**
	 * 分转元
	 */
	public static BigDecimal fenToYuanFormat(Long amount){
		if(amount == null){
			return BigDecimal.ZERO;
		}
		return new BigDecimal(amount).divide(new BigDecimal(100)).setScale(2);
	}
	/**
	 * 元转分
	 */
	public static long yuanTofen(Double amount){
    	if(amount == null){
 			return 0L;
 		}
		return new BigDecimal(String.valueOf(amount)).multiply(BigDecimal.valueOf(100)).longValue();
	}

	/**
	 * 分转万元
	 */
	public static BigDecimal fenToWanYuan(Long amount){
		if(amount == null){
			return BigDecimal.ZERO;
		}
		return new BigDecimal(amount).divide(new BigDecimal(1000000)).setScale(4,BigDecimal.ROUND_UP);
	}

	/**
	 * 万元转分
	 */
	public static long wanYuanTofen(Double amount){
		if(amount == null){
			return 0L;
		}
		return new BigDecimal(String.valueOf(amount)).multiply(BigDecimal.valueOf(1000000)).longValue();
	}

	/**
	 * 分转十万元
	 */
	public static BigDecimal fenToShiWanYuan(Long amount){
		if(amount == null){
			return BigDecimal.ZERO;
		}
		return new BigDecimal(amount).divide(new BigDecimal(10*10000*100)).setScale(5);
	}

	/**
	 * 十万元转分
	 */
	public static long shiWanYuanTofen(Double amount){
		if(amount == null){
			return 0L;
		}
		return new BigDecimal(String.valueOf(amount)).multiply(BigDecimal.valueOf(10*10000*100)).longValue();
	}

    /**
     * 百分比转换
     */
    public static String percentFormat(BigDecimal rate){

		return new DecimalFormat("0.0#").format(rate);
    }
     
	public static String percentFormat2(BigDecimal rate){
    	 
     	return new DecimalFormat("0.00").format(rate);
    }

	/**
	 * 金额格式化 如：888888888.91 转换为：888,888,888.91
	 * @param amt
	 * @author
	 * @return
	 */
	public static String formart(Object amt){
		DecimalFormat df = new DecimalFormat("###,##0.00");
		return df.format(amt);
	}

	/**
	 * 小写金额转为大写金额
	 * @param v
	 * @author
	 * @return
	 */
	public static String change(double v) {
		if (v < 0 || v > MAX_VALUE){
			return "参数非法!";
		}
		long l = Math.round(v * 100);
		if (l == 0){
			return "零元整";
		}
		String strValue = l + "";
		// i用来控制数
		int i = 0;
		// j用来控制单位
		int j = UNIT.length() - strValue.length();
		String rs = "";
		boolean isZero = false;
		for (; i < strValue.length(); i++, j++) {
			char ch = strValue.charAt(i);
			if (ch == '0') {
				isZero = true;
				if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万' || UNIT.charAt(j) == '元') {
					rs = rs + UNIT.charAt(j);
					isZero = false;
				}
			} else {
				if (isZero) {
					rs = rs + "零";
					isZero = false;
				}
				rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);
			}
		}
		if (!rs.endsWith("分")) {
			rs = rs + "整";
		}
		rs = rs.replaceAll("亿万", "亿");
		return rs;
	}
}