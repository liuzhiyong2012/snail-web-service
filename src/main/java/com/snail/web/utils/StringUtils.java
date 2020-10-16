package com.snail.web.utils;

public class StringUtils {

	public static boolean isEmptyStr(String str) {
		return str == null || "".equals(str.trim());
	}

	public static boolean isNullStr(String str) {
		return str == null;
	}

	public static boolean isEmptyStr(String...strings) {
		for(int i = 0 ; i < strings.length ; i ++) {
			if(isEmptyStr(strings[i])) return true;
		}
		return false;
	}

	/**
	 * 当str相等于
	 * @param str
	 * @param objs
	 * @return
	 */
	public static boolean equalsString(String str, String...objs) {
		if (objs.length == 0) return true;

		for(int i = 0 ; i < objs.length ; i ++) {
			if (str.equals(objs)) return false;
		}

		return true;
	}
}
