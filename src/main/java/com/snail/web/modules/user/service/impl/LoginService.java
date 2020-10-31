package com.snail.web.modules.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.modules.user.dto.entity.Login;
import com.snail.web.modules.user.dto.entity.User;
import com.snail.web.modules.user.dto.request.UserRequest;
import com.snail.web.modules.user.mapper.LoginMapper;
import com.snail.web.modules.user.service.ILoginService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService  extends ServiceImpl<LoginMapper, Login> implements ILoginService {
    @Override
    public void insert(User user, String token) {
        Login  login = new Login();
        login.setToken(token);
        login.setUserId(user.getId());
        login.setCreatedBy(user.getId());
        Date nowTime = new Date();
        login.setId(IdWorker.getIdStr());
        login.setCreatedTime(nowTime);
        login.setUpdatedBy(user.getId());
        login.setUpdatedTime(nowTime);
        this.baseMapper.insert(login);
    }

    @Override
    public void update(UserRequest user, String userId) {
        Login  login = new Login();

       // this.baseMapper.update(login,"");
    }
}
