package com.snail.web.modules.user.service;


import com.baomidou.mybatisplus.service.IService;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.user.dto.entity.UserRole;
import com.snail.web.modules.user.dto.request.UserRoleRequest;

/**
 * Created by Mei on 2019/7/22.
 */
public interface UserRoleService extends IService<UserRole> {
    public PageBaseResponse getPage(UserRoleRequest ur);

    public PageBaseResponse getAll(UserRoleRequest ur);

    public BaseResponse insertData(UserRole ur, String userId);

    public BaseResponse getUserByWeight(String userId);

    public BaseResponse update(UserRoleRequest ur, String userId);

    public BaseResponse deleteById(UserRoleRequest ur, String userId);
}
