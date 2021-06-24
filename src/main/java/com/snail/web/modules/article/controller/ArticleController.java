package com.snail.web.modules.article.controller;

import com.snail.web.constants.BaseConstant;
import com.snail.web.dto.BaseResponse;
import com.snail.web.modules.article.dto.entity.Article;
import com.snail.web.modules.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody Article article, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return articleService.insert( article,userId);
    }

    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody Article article, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return articleService.delete( article,userId);
    }


    @PostMapping("/update")
    public BaseResponse update(@RequestBody Article article, HttpServletRequest request){
        String userId = (String)request.getAttribute(BaseConstant.USER_INFO);
        return articleService.update(article,userId);
    }

    @PostMapping("/page")
    public BaseResponse page(@RequestBody Article article, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return articleService.page( article,userId);
    }
}
