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
     PageBaseResponse getPage(UserRoleRequest ur);

     PageBaseResponse getAll(UserRoleRequest ur);

     BaseResponse insertData(UserRole ur, String userId);

     BaseResponse getUserByWeight(String userId);

     BaseResponse update(UserRoleRequest ur, String userId);

     BaseResponse deleteById(UserRoleRequest ur, String userId);
}
