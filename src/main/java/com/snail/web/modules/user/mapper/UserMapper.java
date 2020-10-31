package com.snail.web.modules.user.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.user.dto.entity.User;
import com.snail.web.modules.user.dto.request.UserRequest;
import com.snail.web.modules.user.dto.response.UserReponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select 1")
    public List<Map> heartBeat();

    public Integer count(UserRequest userRequest);

    public List<UserReponse> page(UserRequest userRequest);

    public UserReponse autoLogin(UserRequest userRequest);

}
