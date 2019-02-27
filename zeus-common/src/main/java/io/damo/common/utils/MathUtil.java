package io.damo.common.utils;

import io.damo.common.exception.RRException;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * 数字工具类
 */
public class MathUtil {

	public static final int SCALE_2 = 2;

	public static final int SCALE_8 = 8;

	/**
	 * 数字格式化为百分比
	 *
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
	 *
	 * @param baifenbi 百分比的值 100%
	 * @return
	 */
	public static Number percentFormat(String baifenbi) throws ParseException {
		NumberFormat nf = NumberFormat.getPercentInstance();
		return nf.parse(baifenbi);
	}

	/**
	 * 百分比转为BigDecimal小数
	 *
	 * @param amount
	 * @return
	 */
	public static BigDecimal decimalFormat(double amount) {
		Double f = Double.valueOf(amount) / 100;
		BigDecimal decimal = new BigDecimal(f);
		return decimal.setScale(SCALE_2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * BigDecimal 等于
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean equalForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null) {
			throw new RRException("Illegal parameters");
		}
		int i = d1.compareTo(d2);
		if (i == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * BigDecimal 大于
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean greatForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null) {
			throw new RRException("Illegal parameters");
		}
		int i = d1.compareTo(d2);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * BigDecimal 大于等于
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean greatOrEqualForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null) {
			throw new RRException("Illegal parameters");
		}
		int i = d1.compareTo(d2);
		if (i >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * BigDecimal 小于
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean lessForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null) {
			throw new RRException("Illegal parameters");
		}
		int i = d1.compareTo(d2);
		if (i < 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * BigDecimal 小于等于
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean lessOrEqualForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null) {
			throw new RRException("Illegal parameters");
		}
		int i = d1.compareTo(d2);
		if (i <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * BigDecimal 加法
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal add2pForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null) {
			throw new RRException("Illegal parameters");
		}
		return d1.add(d2).setScale(SCALE_2, BigDecimal.ROUND_DOWN);

	}

	/**
	 * BigDecimal 减法
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal subtract2pForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null) {
			throw new RRException("Illegal parameters");
		}
		return d1.subtract(d2).setScale(SCALE_2, BigDecimal.ROUND_DOWN);

	}

	/**
	 * BigDecimal乘法
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal multiply2pForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null)
			throw new RRException("Illegal parameters");
		return d1.multiply(d2).setScale(SCALE_2, BigDecimal.ROUND_DOWN);
	}

	/**
	 * BigDecimal 除法
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal divide2pForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null)
			throw new RRException("Illegal parameters");
		return d1.divide(d2, SCALE_2, BigDecimal.ROUND_DOWN);
	}

	/**
	 * BigDecimal 加法
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal add8pForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null) {
			throw new RRException("Illegal parameters");
		}
		return d1.add(d2).setScale(SCALE_8, BigDecimal.ROUND_DOWN);

	}

	/**
	 * BigDecimal 减法
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal subtract8pForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null) {
			throw new RRException("Illegal parameters");
		}
		return d1.subtract(d2).setScale(SCALE_8, BigDecimal.ROUND_DOWN);

	}

	/**
	 * BigDecimal乘法
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal multiply8pForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null)
			throw new RRException("Illegal parameters");
		return d1.multiply(d2).setScale(SCALE_8, BigDecimal.ROUND_DOWN);
	}

	/**
	 * BigDecimal 除法
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal divide8pForBg(BigDecimal d1, BigDecimal d2) {
		if (d1 == null || d2 == null)
			throw new RRException("Illegal parameters");
		return d1.divide(d2, SCALE_8, BigDecimal.ROUND_DOWN);
	}

}
