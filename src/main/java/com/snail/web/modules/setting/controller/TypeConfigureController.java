package com.snail.web.modules.setting.controller;

import com.snail.web.constants.BaseConstant;
import com.snail.web.dto.BaseResponse;
import com.snail.web.modules.article.dto.entity.ArticleType;
import com.snail.web.modules.article.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/type")
public class TypeConfigureController {
    @Autowired
    ArticleTypeService articleTypeService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody ArticleType articleType, HttpServletRequest request){
        String userId = (String)request.getAttribute(BaseConstant.USER_INFO);
        return articleTypeService.insert(articleType,userId);
    }

    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody  ArticleType articleType, HttpServletRequest request){
        String userId = (String)request.getAttribute(BaseConstant.USER_INFO);
        return articleTypeService.delete(articleType,userId);
    }


    @PostMapping("/update")
    public BaseResponse update(@RequestBody  ArticleType articleType, HttpServletRequest request){
        String userId = (String)request.getAttribute(BaseConstant.USER_INFO);
        return articleTypeService.update(articleType,userId);
    }

    @PostMapping("/page")
    public BaseResponse page(@RequestBody  ArticleType articleType, HttpServletRequest request){
        String userId = (String)request.getAttribute(BaseConstant.USER_INFO);
        return articleTypeService.page(articleType,userId);
    }

}
