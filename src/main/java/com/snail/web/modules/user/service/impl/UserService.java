package com.snail.web.modules.user.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.entity.BaseResponse;
import com.snail.web.entity.PageBaseResponse;
import com.snail.web.modules.user.dto.entity.User;
import com.snail.web.modules.user.dto.request.UserRequest;
import com.snail.web.modules.user.dto.response.UserReponse;
import com.snail.web.modules.user.mapper.UserMapper;
import com.snail.web.modules.user.service.ILoginService;
import com.snail.web.modules.user.service.IUserService;
import com.snail.web.utils.RequestUtils;
import com.snail.web.utils.ResponseUtils;
import com.snail.web.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mei on 2019/7/22.
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {
 /*   @Autowired
    private UserRoleService userRoleService;*/

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ILoginService loginService;

    @Autowired
    private IUserService userService;

    @Override
    public void doHeartBeat() {
        this.baseMapper.selectById("1");
    }

    @Override
    public Integer getRoleWeight(String userId) {
       /* User user = this.baseMapper.selectById(userId);
        if (user == null) {
            return null;
        }
        UserRole userRole = userRoleService.selectById(user.getRoleId());
        if (userRole == null) {
            return null;
        }*/
//        return userRole.getRoleWeight();
        return null;
    }




    @Override
    public BaseResponse update(UserRequest userRequest, String userId)  {
        /*String err = userRequest.validDate();
        if(!StringUtils.isEmptyStr(err)){
            return ResponseUtils.errorMsg(err);
        }
        if(StringUtils.isEmptyStr(userRequest.getId())){
            err="id不能为空";
            return ResponseUtils.errorMsg(err);
        }
        Integer count = this.baseMapper.count(userRequest);
        if(count>0){
            err = "用户名已存在";
            return ResponseUtils.errorMsg(err);
        }
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("id",userRequest.getId());
        User u = new User();
        u.setUsername(userRequest.getUsername());
        if(!StringUtils.isEmptyStr(userRequest.getPassword())){
            u.setPassword(userRequest.getPassword());
        }
        u.setName(userRequest.getName());
        u.setRoleId(userRequest.getRoleId());
        u.setUpdatedBy(Long.parseLong(userId));
        u.setUpdatedTime(new Date());
        this.baseMapper.update(u,wrapper);*/
        return ResponseUtils.success();
    }

    @Override
    public BaseResponse insert(UserRequest userRequest,String userId) {
        String err = userRequest.validDate();
        if(!StringUtils.isEmptyStr(err)){
            return ResponseUtils.errorMsg(err);
        }
        if(StringUtils.isEmptyStr(userRequest.getPassword())){
            return ResponseUtils.errorMsg("密码不能为空");
        }
        Integer count = this.baseMapper.count(userRequest);

        if(count>0){
            err = "用户名已存在";
            return ResponseUtils.errorMsg(err);
        }

        User u = new User();
        u.setId(IdWorker.getIdStr());

        u.setNickname(userRequest.getNickname());
        u.setPassword(userRequest.getPassword());
        u.setPhone(userRequest.getPhone());
        u.setEmail(userRequest.getEmail());
        u.setCreatedTime(new Date());
        u.setSex(userRequest.getSex());
        u.setUpdatedTime(new Date());
        this.baseMapper.insert(u);
        return ResponseUtils.success();
    }

    @Override
    public BaseResponse login(User user) {

        if (StringUtils.isEmptyStr(user.getPhone()) || StringUtils.isEmptyStr(user.getPassword())) {
            return ResponseUtils.errorMsg("请输入用户名或密码");
        }

        EntityWrapper ew = new EntityWrapper();
        ew.eq("phone", user.getPhone());

        List<User> users = this.baseMapper.selectList(ew);
        if (users == null || users.size() == 0) {
            return ResponseUtils.errorMsg("用户不存在");
        }

        if (!users.get(0).getPassword().equals(user.getPassword())) {
            return ResponseUtils.errorMsg("密码错误");
        }


//        HashMap<String, String> map = new HashMap<>();
//        map.put("id",users.get(0).getId());
//        List<String> list = EncryptUtils.genKeyPair();
//        map.put("privateKey",list.get(0));
//        String serialize = JSON.serialize(map);
//        HashMap<Object, Object> returnMap = new HashMap<>();
//        String token = IdWorker.get32UUID();
//        returnMap.put("token",token);
//        returnMap.
        User retUser = users.get(0);
        String token = RequestUtils.getToken(retUser.getId()+"",retUser.getPassword());
        loginService.insert(retUser,token);
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("nickName",retUser.getNickname());
        map.put("userId",retUser.getId());
        map.put("phone",retUser.getPhone());
        /*RedisUtils.saveUserToken(redisTemplate,token,users.get(0).getId());*/
        return ResponseUtils.convert(map);
    }

    @Override
    public PageBaseResponse page(UserRequest request, String userId) {
       /* Integer weight = userService.getRoleWeight(userId);
        if (weight == null) {
            return ResponseUtils.pageError("无权限");
        }*/
        if (request.getPageNumber() == null || request.getPageSize() == null) {
            return ResponseUtils.pageError("参数缺失分页");
        }
        Integer count = this.baseMapper.count(request);
        if (count == null || count.equals(0)) {
            return ResponseUtils.pageSuccess();
        }
        List<UserReponse> userReponses = this.baseMapper.page(request);

        return ResponseUtils.pageConvert(request.getPageNumber(), request.getPageSize(), count, userReponses);
    }

    @Override
    public BaseResponse deleteById(UserRequest userRequest, String userId)  {
        String errMessage= "";
        if(StringUtils.isEmptyStr(userRequest.getId())){
            errMessage="id不能为空";
            return ResponseUtils.errorMsg(errMessage);
        }
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("id",userRequest.getId());
        this.delete(wrapper);
        return ResponseUtils.success();
    }

    @Override
    public BaseResponse autoLogin(UserRequest user) {
        UserReponse  userReponse =  this.baseMapper.autoLogin(user);
        String errMessage;
        if(userReponse == null){
            errMessage="id不能为空";
            return ResponseUtils.errorMsg(errMessage);
        }

        long nowTime = System.currentTimeMillis();
        if(nowTime - userReponse.getUpdatedTime().getTime() >1000 * 60 * 60)
        {
            errMessage="token已过期";
            return ResponseUtils.errorMsg(errMessage);
        }

        return ResponseUtils.convert(userReponse);


    }


}
