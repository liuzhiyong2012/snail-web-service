package com.snail.web.modules.frontuser.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.frontuser.dto.entity.FrontUser;
import com.snail.web.modules.frontuser.dto.request.FrontUserRequest;
import com.snail.web.modules.frontuser.dto.response.FrontUserReponse;
import com.snail.web.modules.frontuser.mapper.FrontUserMapper;
import com.snail.web.modules.frontuser.service.FrontUserService;
import com.snail.web.utils.RequestUtils;
import com.snail.web.utils.ResponseUtils;
import com.snail.web.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mei on 2019/7/22.
 */
@Service
public class IFrontUserService extends ServiceImpl<FrontUserMapper, FrontUser> implements FrontUserService {


//    @Autowired
//    private RedisTemplate redisTemplate;
    @Autowired
    private FrontUserService frontUserService;





   /* @Override
    public BaseResponse update(UserRequest userRequest, String userId)  {
        String err = userRequest.validDate();
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
        this.baseMapper.update(u,wrapper);
        return ResponseUtils.success();
    }*/

    @Override
    public BaseResponse insert(FrontUserRequest userRequest, String userId) {
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
        FrontUser  u = new FrontUser();
        u.setId(IdWorker.getId());
        u.setUsername(userRequest.getUsername());
        u.setName(userRequest.getName());
        u.setPassword(userRequest.getPassword());
        //u.setCreatedBy(Long.parseLong(userId));
        u.setCreatedTime(new Date());
        u.setRoleType(userRequest.getRoleType());
        //u.setUpdatedBy(Long.parseLong(userId));
        u.setUpdatedTime(new Date());
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
    public BaseResponse login(FrontUser frontUser) {

        if (StringUtils.isEmptyStr(frontUser.getUsername()) || StringUtils.isEmptyStr(frontUser.getPassword())) {
            return ResponseUtils.errorMsg("请输入用户名或密码");
        }

        EntityWrapper ew = new EntityWrapper();
        ew.eq("username", frontUser.getUsername());

        List<FrontUser> users = this.baseMapper.selectList(ew);
        if (users == null || users.size() == 0) {
            return ResponseUtils.errorMsg("用户不存在");
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
        String token = RequestUtils.getToken(users.get(0).getId()+"",users.get(0).getPassword());
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("userName",users.get(0).getName());
//        RedisUtils.saveAdminToken(redisTemplate, token,users.get(0).getId());
        return ResponseUtils.convert(map);
    }

    /*@Override
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
    }*/
}
