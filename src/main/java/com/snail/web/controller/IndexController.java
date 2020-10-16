package com.snail.web.controller;

import com.snail.web.entity.User;
import com.snail.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/hello",method= {RequestMethod.GET,RequestMethod.POST})
    public User sayHello(){
        User user = this.userService.getUserById("1");
        String name = user.getName();
        String name2 = user.getName();
        return user;
    }

    @RequestMapping(value="/test",method={RequestMethod.GET})
    public User test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("service");
        context.refresh();
        for (String s : context.getBeanDefinitionNames()) {
            System.out.println(s);
        }
        UserService userService = (UserService) context.getBean("userService");
        userService.getUserById("1");
        return new User();
    }


}
