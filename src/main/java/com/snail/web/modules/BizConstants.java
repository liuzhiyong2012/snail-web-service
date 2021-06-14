package com.snail.web.modules;


/**
 * 业务常量
 *
 * @author Holinc
 */
public interface BizConstants {
	/**
	 * 文章类型
	 */
	interface ArtitleType {
		String NEWS = "news";
		String DUAN_ZI = "duanzi";
		String XUE_YUAN = "xueyuan";
	}

	/**
	 * 新闻二级类别
	 */
	interface NewsSubType {
		/**
		 * 行业热点
		 */
		String HYRD = "hyrd";
		/**
		 * 行业动态
		 */
		String NEWSH = "newsh";

		/**
		 * 原创专区
		 */
		String NEWST = "newst";

		/**
		 * 品牌报道
		 */
		String NEWSP = "newsp";

		/**
		 * 微商达人分享
		 */
		String WSDR = "wsdr";

		/**
		 * 微商博览会
		 */
		String NEWSW = "newsw";
	}

	/**
	 * 段子二级类别
	 */
	interface StorySubType {
		/**
		 * 微商段子
		 */
		String DSH = "dsh";
		/**
		 * 微博段子
		 */
		String DBO = "dbo";
		/**
		 * 朋友圈段子
		 */
		String DPENG = "dpeng";
		/**
		 * 内涵段子
		 */
		String DNEI = "dnei";
	}

	/**
	 * 学院二级类别
	 */
	interface CollegeSubType {
		/**
		 * 微商代理起步技巧
		 */
		String WQIBU = "wqibu";
		/**
		 * 微商怎么找客源
		 */
		String WKEYUAN = "wkeyuan";
		/**
		 * 微商怎么找货源
		 */
		String WHUOY = "whuoy";
		/**
		 * 微信营销
		 */
		String WYX = "wyx";
		/**
		 * 微博营销
		 */
		String WBOKE = "wboke";
		/**
		 * 其他推广方法
		 */
		String WOTH = "woth";
	}
}
