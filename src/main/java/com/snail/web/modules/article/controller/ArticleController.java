package com.snail.web.modules.article.controller;

import com.snail.web.constants.BaseConstant;
import com.snail.web.dto.BaseResponse;
import com.snail.web.modules.article.dto.ArticleNew;
import com.snail.web.modules.article.service.ArticleNewService;
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
    ArticleNewService articleNewService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody ArticleNew articleNew, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return articleNewService.insert( articleNew,userId);
    }

    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody ArticleNew articleNew, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return articleNewService.delete( articleNew,userId);
    }


    @PostMapping("/update")
    public BaseResponse update(@RequestBody ArticleNew articleNew, HttpServletRequest request){
        String userId = (String)request.getAttribute(BaseConstant.USER_INFO);
        return articleNewService.update(articleNew,userId);
    }

    @PostMapping("/page")
    public BaseResponse page(@RequestBody ArticleNew articleNew, HttpServletRequest request){
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return articleNewService.page( articleNew,userId);
    }
}
