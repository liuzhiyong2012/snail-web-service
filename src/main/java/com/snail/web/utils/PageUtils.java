package com.snail.web.utils;

public class PageUtils {

	public static Integer countTotalPage(Integer totalRecord, Integer pageSize) {
		Double dou = Math.ceil(totalRecord / pageSize);

		return dou.intValue();
	}
}
