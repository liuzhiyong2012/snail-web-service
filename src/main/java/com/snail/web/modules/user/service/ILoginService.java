package com.snail.web.modules.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.snail.web.modules.user.dto.entity.Login;
import com.snail.web.modules.user.dto.entity.User;
import com.snail.web.modules.user.dto.request.UserRequest;

public interface ILoginService extends IService<Login> {

    public void insert(User user, String token);

    public void update(UserRequest user, String userId);

}


