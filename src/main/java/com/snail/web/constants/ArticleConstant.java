package com.snail.web.constants;

import java.util.HashMap;
import java.util.Map;

public class ArticleConstant {
    public final static String HEAD_ARTICLE_CODE="headeArticle";
    public final static String IMFORMATION_CODE="information";
    public final static String SHOP_CODE="shop";

    public final static String ARTICLCE_TYPE_TEXT="1";//编辑富文本
    public final static String ARTICLCE_TYPE_LINK="2"; //关联用户的贴子
    public final static String ARTICLCE_TYEP_URL="3";  //输入可访问链接


    public final static Map<String,String> IMFORMATION_CRAWLER_MAP= new HashMap(){
        {   put("index", "shop");
            put("news", "information");
            put("duanzi", "information");
            put("xueyuan", "information");
        }
    };




}
