package com.snail.web.test;


import com.snail.web.dao.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;


    public void testSelect() {

    }

}