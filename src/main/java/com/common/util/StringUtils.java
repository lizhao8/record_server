package com.common.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * 判断List<String>是否为空
	 * 
	 * @param strList
	 * @return
	 */
	public static boolean arrayIsNull(List<String> strList) {
		if (strList == null) {
			return true;
		}
		if (strList.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串为空判断
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNull(String string) {
		if (string == null)
			return true;
		string = string.trim();
		if ("".equals(string))
			return true;
		return false;
	}

	/**
	 * 判断字符数组是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String[] str) {
		if (str == null || str.length == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 字符串非空判断
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNotNull(String string) {
		return !isNull(string);
	}

	/**
	 * 字符串检查 字符串每位字符均相等返回空字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String phoneCheck(String src) {
		if (src == null) {
			return "";
		}
		src = src.trim();
		String value = src.replace("-", "");
		if (value.length() == 1) {
			return src;
		}
		if (value.length() == 0) {
			return "";
		}
		for (int i = 1; i < value.length(); i++) {
			if (value.charAt(i) != value.charAt(0)) {
				return src;
			}
		}
		return "";
	}

	/**
	 * 检查2个字符串是否脱敏匹配，脱敏字符*
	 * 
	 * @param src
	 * @param tar
	 * @return 字符长度一致，交叉匹配 or 返回true
	 */
	public static boolean checkSame(String src, String tar) {
		// xx若
		String regex = "[`~!@#$%^&*()+=\\|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？x-]";
		src = src.replaceAll(regex, ".*");
		tar = tar.replaceAll(regex, ".*");
		Pattern pattern1 = Pattern.compile(src);
		Matcher matcher1 = pattern1.matcher(tar);
		boolean flag1 = matcher1.matches();
		Pattern pattern2 = Pattern.compile(tar);
		Matcher matcher2 = pattern2.matcher(src);
		boolean flag2 = matcher2.matches();
		return flag1 || flag2;
	}

	public static String divide_100(String value) {
		// logger.info("输入：" + value);
		if (value == null || "".equals(value)) {
			return value;
		}
		BigDecimal bigDecimal = new BigDecimal("0");
		try {
			bigDecimal = new BigDecimal(value);
		} catch (Exception e) {
			// logger.info("输入异常：" + e);
			return value;
		}
		BigDecimal bigDecimal_100 = new BigDecimal("100");
		bigDecimal = bigDecimal.divide(bigDecimal_100);
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		// logger.info("输出" + bigDecimal);
		return bigDecimal.toPlainString();
	}

	/**
	 * Unicode转String
	 * 
	 */
	public static String unicodeToString(String unicode) {
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;

		while ((i = unicode.indexOf("\\u", pos)) != -1) {
			sb.append(unicode.substring(pos, i));
			if (i + 5 < unicode.length()) {
				pos = i + 6;
				sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
			}
			if ((unicode.indexOf("\\u", pos)) == -1) {
				sb.append(unicode.substring(pos, unicode.length()));
			}
		}

		return sb.toString();
	}


	/**
	 * 当浮点型数据位数超过10位之后，数据变成科学计数法显示。用此方法可以使其正常显示。
	 * 
	 * @param value
	 * @return Sting
	 */
	public static String formatFloatNumber(double value) {
		if (value != 0.00) {
			java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
			return df.format(value);
		} else {
			return "0.00";
		}
	}

	public static String downFirstStr(String string) {
		return string.replaceFirst(string.substring(0, 1), string.substring(0, 1).toLowerCase());
	}

	public static String getForMat(String str) {
		String s5 = null;
		if (str.length() > 6) {
			String s2 = str.substring(0, 4);
			String s3 = str.substring(4, 6);
			String s4 = str.substring(6);
			s5 = s2 + "-" + s3 + "-" + s4;
		}
		return s5;
	}

	/**
	 * 替换空格 单引号
	 * 
	 * @param src
	 * @return
	 */
	public static String replaceSplace(String src) {
		if (isNotNull(src)) {
			src = src.replace(" ", "");
			src = src.replace("'", "");
			return src;
		} else {
			return "";
		}
	}

	public static String replaceLast(String src, String regex, String replacement) {
		if (isNotNull(src)) {
			int pos = src.lastIndexOf(regex);
			if (pos > -1) {
				return src.substring(0, pos) + replacement
						+ src.substring(pos + regex.length(), src.length());
			} else {
				return src;
			}
		} else {
			return src;
		}
	}

	public static double parseDouble(String string) {
		try {
			return Double.parseDouble(string);
		} catch (Exception e) {

		}
		return 0;
	}

	public static int parseInt(String string) {
		try {
			return Integer.parseInt(string);
		} catch (Exception e) {

		}
		return 0;
	}

	public static double max(double[] arrays) {
		double max = arrays[0];
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i] > max) {
				max = arrays[i];
			}
		}
		return max;
	}

	public static double max(List<Double> list) {
		double max = list.get(0);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) > max) {
				max = list.get(i);
			}
		}
		return max;
	}

	public static int max(int[] arrays) {
		int max = arrays[0];
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i] > max) {
				max = arrays[i];
			}
		}
		return max;
	}

	public static boolean isNotNull(List list) {
		return !isNull(list);
	}

	public static boolean isNull(List<?> list) {
		if (list == null) {
			return true;
		}
		if (list.size() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 四舍五入保留decimal位小数
	 * 
	 * @param value
	 * @param decimal
	 * @return
	 */
	public static double doubleFormat(double value, int decimal) {
		BigDecimal bigDecimal = new BigDecimal(value);
		value = bigDecimal.setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
		return value;
	}

	/**
	 * 四舍五入保留decimal位小数
	 * 
	 * @param value
	 * @param
	 * @return
	 */
	public static double doubleFormat_1(double value) {
		return doubleFormat(value, 1);
	}
}
