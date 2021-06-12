package com.snail.web.modules.user.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.user.dto.entity.UserRole;
import com.snail.web.modules.user.dto.request.UserRoleRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by Mei on 2019/7/22.
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    public Integer getTotalRecord(UserRoleRequest ur);

    public List<UserRole> page(UserRoleRequest ur);

    public List<Map> getPrividage(UserRole ur);

    public List<UserRole> getRoleByGreaterWeight(UserRole ur);

    public UserRole getUserRoleById(Long id);

}
