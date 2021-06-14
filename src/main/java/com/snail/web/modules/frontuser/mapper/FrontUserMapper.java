package com.snail.web.modules.frontuser.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.frontuser.dto.entity.FrontUser;
import com.snail.web.modules.frontuser.dto.request.FrontUserRequest;
import com.snail.web.modules.frontuser.dto.response.FrontUserReponse;

import java.util.List;

public interface FrontUserMapper extends BaseMapper<FrontUser> {

   /* @Select("select 1")
    public List<Map> heartBeat();*/

  /*  public Integer count(UserRequest userRequest);*/

    List<FrontUserReponse> page(FrontUserRequest frontUserRequest);

    Integer count(FrontUserRequest request);
}
