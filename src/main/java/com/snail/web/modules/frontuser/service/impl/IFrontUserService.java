package com.snail.web.modules.frontuser.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.constants.BaseConstant;
import com.snail.web.constants.DtoConstants;
import com.snail.web.constants.UserConstants;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.frontuser.dto.entity.FrontUser;
import com.snail.web.modules.frontuser.dto.request.FrontUserRequest;
import com.snail.web.modules.frontuser.dto.response.FrontUserReponse;
import com.snail.web.modules.frontuser.mapper.FrontUserMapper;
import com.snail.web.modules.frontuser.service.FrontUserService;
import com.snail.web.utils.RequestUtils;
import com.snail.web.utils.ResponseUtils;
import com.snail.web.utils.SessionUtils;
import com.snail.web.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mei on 2019/7/22.
 */
@Service
public class IFrontUserService extends ServiceImpl<FrontUserMapper, FrontUser> implements FrontUserService {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private FrontUserService frontUserService;

/*
    @Autowired
    private RedisUtils redisUtils;*/





    @Override
    public BaseResponse update(FrontUserRequest frontUserRequest, String userId)  {
        String err = frontUserRequest.validDate();
        if(!StringUtils.isEmptyStr(err)){
            return ResponseUtils.errorMsg(err);
        }
        /*if(StringUtils.isEmptyStr(frontUserRequest.getId())){
            err="id不能为空";
            return ResponseUtils.errorMsg(err);
        }*/
        Integer count = this.baseMapper.count(frontUserRequest);
        if(count>0){
            err = "用户名已存在";
            return ResponseUtils.errorMsg(err);
        }
        EntityWrapper<FrontUser> wrapper = new EntityWrapper<FrontUser>();
        wrapper.eq("id",frontUserRequest.getId());
        FrontUser u = new FrontUser();
        u.setName(frontUserRequest.getName());
        if(!StringUtils.isEmptyStr(frontUserRequest.getPassword())){
            u.setPassword(frontUserRequest.getPassword());
        }
        u.setPhone(frontUserRequest.getPhone());
        u.setEmail(frontUserRequest.getEmail());

        u.setRoleType(frontUserRequest.getRoleType());
        u.setStatus(frontUserRequest.getStatus());

        u.setUpdatedTime(new Date());
        this.baseMapper.update(u,wrapper);
        return ResponseUtils.success();
    }

    @Override
    public BaseResponse resetPassWord(FrontUserRequest frontUserRequest, String userId)
    {
        String err = frontUserRequest.validDate();
        if(!StringUtils.isEmptyStr(err)){
            return ResponseUtils.errorMsg(err);
        }

        //校验验证码
        String redisKey = UserConstants.REDIS_RESET_PASS_PREFIX + frontUserRequest.getPhone();
        String validateCode = (String)redisTemplate.opsForValue().get(redisKey);
        if((null == validateCode) || validateCode.equals("")){
            return ResponseUtils.errorMsg("验证码已经超时,请重新发送");
        }

        if(!validateCode.equals(frontUserRequest.getCode())){
            return ResponseUtils.errorMsg("验证码错误!");
        }

        if(StringUtils.isEmptyStr(frontUserRequest.getPassword())){
            return ResponseUtils.errorMsg("密码不能为空");
        }

        EntityWrapper<FrontUser> wrapper = new EntityWrapper<FrontUser>();
        wrapper.eq("phone",frontUserRequest.getPhone());
        FrontUser u = new FrontUser();
        u.setUsername(frontUserRequest.getUsername());
        if(!StringUtils.isEmptyStr(frontUserRequest.getPassword())){
            u.setPassword(frontUserRequest.getPassword());
        }

        u.setUpdatedBy(UserConstants.ADMIN_USER_ID);
        u.setUpdatedTime(new Date());
        this.baseMapper.update(u,wrapper);
        return ResponseUtils.success();
    }

    @Override
    public BaseResponse getUserInfoByToken(String token) {
        String userIdStr = (String)redisTemplate.opsForValue().get(UserConstants.REDIS_TOKEN_PREFIX + token);
        if(userIdStr == null||userIdStr.equals("")){
            return ResponseUtils.errorMsg("用户登录令牌已过期");
        }
        Long userId = Long.parseLong(userIdStr);

        EntityWrapper<FrontUser>  userWrap =  new EntityWrapper<FrontUser>();
        userWrap.eq("id",userId);
        FrontUser fonrUser = new FrontUser();
        fonrUser.setId(userId);

        FrontUser loginUser = this.baseMapper.selectOne(fonrUser);
        Map<String,Object> map = new HashMap<>();

//        request.setAttribute(BaseConstant.FRONT_USER_KEY,map);
//        SessionUtils.setFrontSession(request,map);

        map.put("token",token);
        map.put("userName",loginUser.getName());
        map.put("userId",loginUser.getId() + "");
        map.put("roleType",loginUser.getRoleType());
        map.put("userInfo",loginUser);
        String redisKey = UserConstants.REDIS_TOKEN_PREFIX + token;
        String code = loginUser.getId() + "";
        redisTemplate.opsForValue().set(redisKey, code, UserConstants.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        return ResponseUtils.convert(map);
    }

    @Override
    public BaseResponse insert(FrontUserRequest userRequest, String userId) {
        String err = userRequest.validDate();
        if(!StringUtils.isEmptyStr(err)){
            return ResponseUtils.errorMsg(err);
        }

        //校验验证码
        String redisKey = UserConstants.REDIS_REGISTER_PREFIX + userRequest.getPhone();
        String validateCode = (String)redisTemplate.opsForValue().get(redisKey);
        if((null == validateCode) || validateCode.equals("")){
            return ResponseUtils.errorMsg("验证码已经超时,请重新发送");
        }

        if(!validateCode.equals(userRequest.getCode())){
            return ResponseUtils.errorMsg("验证码错误!");
        }

        if(StringUtils.isEmptyStr(userRequest.getPassword())){
            return ResponseUtils.errorMsg("密码不能为空");
        }



        FrontUserRequest frontUserRequest = new FrontUserRequest();
        frontUserRequest.setPhone(userRequest.getPhone());
        Integer count = this.baseMapper.count(frontUserRequest);

        if(count>0){
            err = "该手机号已注册";
            return ResponseUtils.errorMsg(err);
        }


        FrontUser  u = new FrontUser();
        u.setId(IdWorker.getId());
        u.setStatus(DtoConstants.STATUS_NORMAL);
        u.setIsDeleted(DtoConstants.IS_DELETE_NO);
        u.setPhone(userRequest.getPhone());
        u.setName(userRequest.getName());
        u.setPassword(userRequest.getPassword());
        u.setCreatedBy(u.getId());
        u.setCreatedTime(new Date());
        u.setRoleType(userRequest.getRoleType());

        this.baseMapper.insert(u);
        return ResponseUtils.success();
    }



    @Override
    public PageBaseResponse page(FrontUserRequest request, String userId) {
        if (request.getPageNumber() == null || request.getPageSize() == null) {
            return ResponseUtils.pageError("参数缺失分页");
        }
        Integer count = this.baseMapper.count(request);
        if (count == null || count.equals(0)) {
            return ResponseUtils.pageSuccess();
        }
        List<FrontUserReponse> userReponses = this.baseMapper.page(request);

        return ResponseUtils.pageConvert(request.getPageNumber(), request.getPageSize(), count, userReponses);
    }

    @Override
    public BaseResponse login(FrontUser frontUser, HttpServletRequest request) {

        if (StringUtils.isEmptyStr(frontUser.getPhone()) || StringUtils.isEmptyStr(frontUser.getPassword())) {
            return ResponseUtils.errorMsg("请输入手机号或密码");
        }

        EntityWrapper ew = new EntityWrapper();
        ew.eq("phone", frontUser.getPhone());

        List<FrontUser> users = this.baseMapper.selectList(ew);
        if (users == null || users.size() == 0) {
            return ResponseUtils.errorMsg("用户不存在");
        }

        if (users.get(0).getStatus().equals("2")) {
            return ResponseUtils.errorMsg("该账号已被冻结,请联系管理员！");
        }

        if (!users.get(0).getPassword().equals(frontUser.getPassword())) {
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

        FrontUser loginUser = users.get(0);
        String token = RequestUtils.getToken(loginUser.getId()+"",loginUser.getPassword());
        Map<String,Object> map = new HashMap<>();

        request.setAttribute(BaseConstant.FRONT_USER_KEY,map);

        SessionUtils.setFrontSession(request,map);

        map.put("token",token);
        map.put("userName",loginUser.getName());
        map.put("userId",loginUser.getId() + "");
        map.put("roleType",loginUser.getRoleType());
        map.put("userInfo",loginUser);
        String redisKey = UserConstants.REDIS_TOKEN_PREFIX + token;
        String code = loginUser.getId() + "";
        redisTemplate.opsForValue().set(redisKey, code, UserConstants.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        return ResponseUtils.convert(map);
    }

    @Override
    public BaseResponse deleteById(FrontUserRequest frontUserRequest, String userId)  {
        String errMessage= "";
        /*if(StringUtils.isEmptyStr(frontUserRequest.getId())){
            errMessage="id不能为空";
            return ResponseUtils.errorMsg(errMessage);
        }*/

        this.baseMapper.deleteRecord(frontUserRequest);
        /*EntityWrapper<FrontUser> wrapper = new EntityWrapper<>();
        wrapper.eq("id",frontUserRequest.getId());
        this.delete(wrapper);*/
        return ResponseUtils.success();
    }
}
