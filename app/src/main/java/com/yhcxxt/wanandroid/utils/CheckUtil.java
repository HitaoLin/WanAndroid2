package com.yhcxxt.wanandroid.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc:一些用到的验证.
 * 
 */

public class CheckUtil {

	/*
	 * 验证手机号 ,
	 */
	public static boolean isMobileNO(String mobiles) {
//		String regExp = "^[1]([3][0-9]{1}|53|59|81|80|85|86|88|89|87|55|56|50|51|52|58|57|82|47)[0-9]{8}$";
		String regExp = "^[1]([3][0-9]{1}|53|59|81|80|85|86|88|89|87|55|56|50|51|52|58|57|82|47|[0-9]{2})[0-9]{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobiles);
		boolean ismobi = m.find();
		return ismobi;
	}

	/**
	 * 验证邮箱.
	 */
	public static boolean isEmail(String strEmail) {
		String regExp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}

	/**
	 * 判断是否全是数字
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

}
