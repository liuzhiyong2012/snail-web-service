package com.snail.web.modules.article.controller;

import com.snail.web.constants.BaseConstant;
import com.snail.web.dto.BaseResponse;
import com.snail.web.modules.article.dto.entity.Article;
import com.snail.web.modules.article.dto.entity.ArticleType;
import com.snail.web.modules.article.service.ArticleService;
import com.snail.web.modules.frontuser.dto.entity.FrontUser;
import com.snail.web.modules.frontuser.service.FrontUserService;
import com.snail.web.utils.ResponseUtils;
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

    @Autowired
    FrontUserService frontUserService;


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

    @PostMapping("/getNewlatestArticle")
    public BaseResponse getNewlatestArticle(@RequestBody ArticleType articleType, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return articleService.getNewlatestArticle(articleType,userId);
    }

    /*@Auth*/
    @PostMapping("/getArticleByUserToken")
    public BaseResponse getArticleByUserToken(@RequestBody Article article, HttpServletRequest request){
        //获取用户的token
        FrontUser fontUser = frontUserService.getLoginUserByToken(request);
        if(fontUser == null){
            return ResponseUtils.errorMsg(ResponseUtils.CODE_TOKEN_EXPIRTE,"用户登录信息已经失效");
        }else{
            article.setCreatedBy(fontUser.getId());
            String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
            return articleService.page( article,userId);
        }
    }

    @PostMapping("/insertByUserToken")
    public BaseResponse insertByUserToken(@RequestBody Article article, HttpServletRequest request){
        FrontUser fontUser = frontUserService.getLoginUserByToken(request);
        if(fontUser == null){
            return ResponseUtils.errorMsg(ResponseUtils.CODE_TOKEN_EXPIRTE,"用户登录信息已经失效");
        }else{
            String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
            article.setCreatedBy(fontUser.getId());
            return articleService.insert( article,userId);
        }


    }

    @PostMapping("/deleteByUserToken")
    public BaseResponse deleteByUserToken(@RequestBody Article article, HttpServletRequest request){
        FrontUser fontUser = frontUserService.getLoginUserByToken(request);
        if(fontUser == null){
            return ResponseUtils.errorMsg(ResponseUtils.CODE_TOKEN_EXPIRTE,"用户登录信息已经失效");
        }else{
            String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
            return articleService.delete( article,userId);
        }
    }


    @PostMapping("/updateByUserToken")
    public BaseResponse updateByUserToken(@RequestBody Article article, HttpServletRequest request){
        FrontUser fontUser = frontUserService.getLoginUserByToken(request);
        if(fontUser == null){
            return ResponseUtils.errorMsg(ResponseUtils.CODE_TOKEN_EXPIRTE,"用户登录信息已经失效");
        }else{
            String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
            article.setUpdatedBy(fontUser.getId());
            return articleService.update(article,userId);
        }


    }




}
