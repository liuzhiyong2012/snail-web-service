package com.snail.web.modules.user.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.constants.RoleWeight;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.user.dto.entity.User;
import com.snail.web.modules.user.dto.entity.UserRole;
import com.snail.web.modules.user.dto.request.UserRoleRequest;
import com.snail.web.modules.user.mapper.UserRoleMapper;
import com.snail.web.modules.user.service.UserRoleService;
import com.snail.web.modules.user.service.UserService;
import com.snail.web.utils.ResponseUtils;
import com.snail.web.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Mei on 2019/7/22.
 */
@Service
public class IUserRoleService extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Autowired
    private UserService userService;
    @Override
    public PageBaseResponse getPage(UserRoleRequest ur) {
        Integer total = this.baseMapper.getTotalRecord(ur);
        if(total > 0) {
//            ur.setPage((ur.getPage() - 1) * ur.getPageSize());
            List<UserRole> userRoles = this.baseMapper.page(ur);
            return ResponseUtils.pageConvert(ur.getPageNumber(), ur.getPageSize(), total, userRoles);
        }
            return ResponseUtils.pageSuccess();
//        return pbr;
    }

    @Override
    public PageBaseResponse getAll(UserRoleRequest ur) {
        List<UserRole> userRoles = this.baseMapper.page(ur);
        return ResponseUtils.pageConvert(ur.getPageNumber(), ur.getPageSize(),0, userRoles);
    }

    @Override
    public BaseResponse insertData(UserRole ur, String userId) {
        Integer weight = userService.getRoleWeight(userId);
        if (!RoleWeight.SUPER_ADMIN.equals(weight)) {
            return ResponseUtils.errorMsg("无权限");
        }
        if(StringUtils.isEmptyStr(ur.getName())){

        }
        EntityWrapper ew = new EntityWrapper();
        ew.eq("name", ur.getName());
        Integer c =this.baseMapper.selectCount(ew);

        if (c == null || c.equals(0)) {
            ur.setRoleWeight(6);
            ur.setCreatedBy(Long.parseLong(userId));
            ur.setCreatedTime(new Date());
            ur.setUpdatedBy(Long.parseLong(userId));
            ur.setUpdatedTime(new Date());
//            ur.setUpdatedBy(userId);
//            ur.setUpdatedTime(new Date());
            ur.setId(IdWorker.getId());
            this.baseMapper.insert(ur);
            return ResponseUtils.success();
        }
        return ResponseUtils.errorMsg("角色已存在");
    }

    @Override
    public BaseResponse getUserByWeight(String userId) {
        Integer weight = userService.getRoleWeight(userId);
        if (weight != null) {
            return ResponseUtils.errorMsg("NO_PERMISSON");
        }

        User user = userService.selectById(userId);

        UserRole userRole = this.baseMapper.selectById(user.getRoleId());
        if (userRole == null) {
            return ResponseUtils.errorMsg("no.permission");
        }
        return ResponseUtils.convert(this.baseMapper.getRoleByGreaterWeight(userRole));
    }

    @Override
    public BaseResponse update(UserRoleRequest request,String userId) {
        Integer weight = userService.getRoleWeight(userId);
        if(weight==null){
            return ResponseUtils.errorMsg("无权限");
        }
       /* if(StringUtils.isEmptyStr(request.getId())){
            return ResponseUtils.errorMsg("id不能为空");
        }*/
        String val = request.validSave();
        if (val != null) {
            return ResponseUtils.errorMsg(val);
        }
        EntityWrapper ew = new EntityWrapper();
        ew.eq("id",request.getId());
        int i = this.selectCount(ew);
        if(i==0){
            return ResponseUtils.errorMsg("信息不存在");
        }
        UserRole entity = new UserRole();
        entity.setParam(request);
        entity.setUpdatedBy(Long.parseLong(userId));
        entity.setUpdatedTime(new Date());
        this.update(entity, ew);
//        entity = this.baseMapper.getUserRoleById(Long.parseLong(request.getId()));
//        return ResponseUtils.convert(entity);
        return ResponseUtils.success();
    }

    @Override
    public BaseResponse deleteById(UserRoleRequest request,String userId) {
        Integer weight = userService.getRoleWeight(userId);
        if(weight==null){
            return ResponseUtils.errorMsg("无权限");
        }
        /*if(StringUtils.isEmptyStr(request.getId())){
            return ResponseUtils.errorMsg("id不能为空");
        }*/
        EntityWrapper ew = new EntityWrapper();
        ew.eq("id",request.getId());
        this.baseMapper.delete(ew);
        return ResponseUtils.success();
    }
}
