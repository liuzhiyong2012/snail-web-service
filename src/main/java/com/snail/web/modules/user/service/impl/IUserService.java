package com.snail.web.modules.user.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.snail.web.constants.DtoConstants;
import com.snail.web.constants.UserConstants;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.user.dto.entity.User;
import com.snail.web.modules.user.dto.entity.UserRole;
import com.snail.web.modules.user.dto.request.UserRequest;
import com.snail.web.modules.user.dto.response.UserReponse;
import com.snail.web.modules.user.mapper.UserMapper;
import com.snail.web.modules.user.service.UserRoleService;
import com.snail.web.modules.user.service.UserService;
import com.snail.web.utils.EncryptUtils;
import com.snail.web.utils.RequestUtils;
import com.snail.web.utils.ResponseUtils;
import com.snail.web.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mei on 2019/7/22.
 */
@Service
public class IUserService extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;
    @Override
    public void doHeartBeat() {
        this.baseMapper.selectById("1");
    }

    @Override
    public Integer getRoleWeight(String userId) {
        User user = this.baseMapper.selectById(userId);
        if (user == null) {
            return null;
        }
        UserRole userRole = userRoleService.selectById(user.getRoleId());
        if (userRole == null) {
            return null;
        }
        return userRole.getRoleWeight();
    }

    @Override
    public BaseResponse createAdmin() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User();
        user.setId(IdWorker.getId());
        user.setUsername("admin");
        user.setPassword(EncryptUtils.EncoderByMd5("admin"));
        user.setRoleId("1");
        user.setName("超级管理员");
        user.setCreatedBy(1L);
        user.setCreatedTime(new Date());
        user.setUpdatedBy(1L);
        user.setUpdatedTime(new Date());
        this.baseMapper.insert(user);

        return null;
    }

    @Override
    public BaseResponse update(UserRequest userRequest, HttpServletRequest request)  {
        String err = userRequest.validDate();
        if(!StringUtils.isEmptyStr(err)){
            return ResponseUtils.errorMsg(err);
        }
        /*if(StringUtils.isEmptyStr(userRequest.getId())){
            err="id不能为空";
            return ResponseUtils.errorMsg(err);
        }*/
        UserRequest userQuery = new UserRequest();
        userQuery.setPageNumber(1);
        userQuery.setPageSize(1000);
        userQuery.setUsername(userRequest.getUsername());
        List<UserReponse> userReponses = this.baseMapper.page(userQuery);

        Integer count = userReponses.size();



        if(count ==  1){
            if(userRequest.getId().longValue() != userReponses.get(0).getId().longValue()){
                err = "用户名已存在";
                return ResponseUtils.errorMsg(err);
            }
        }else if(count > 1){
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
        u.setStatus(userRequest.getStatus());
//        u.setRoleId(userRequest.getRoleId());
        u.setUpdatedBy(UserConstants.ADMIN_USER_ID);
        u.setUpdatedTime(new Date());
        this.baseMapper.update(u,wrapper);
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
        u.setId(IdWorker.getId());
        u.setUsername(userRequest.getUsername());
        u.setName(userRequest.getName());
        u.setPassword(userRequest.getPassword());
        u.setStatus(userRequest.getStatus());
        u.setIsDeleted(DtoConstants.IS_DELETE_NO);
//        u.setRoleId(userRequest.getRoleId());
        u.setCreatedBy(UserConstants.ADMIN_USER_ID);
        u.setCreatedTime(new Date());
        u.setUpdatedBy(UserConstants.ADMIN_USER_ID);
        u.setUpdatedTime(new Date());
        this.baseMapper.insert(u);
        return ResponseUtils.success();
    }

    @Override
    public BaseResponse login(User user) {

        if (StringUtils.isEmptyStr(user.getUsername()) || StringUtils.isEmptyStr(user.getPassword())) {
            return ResponseUtils.errorMsg("请输入用户名或密码");
        }

        EntityWrapper ew = new EntityWrapper();
        ew.eq("username", user.getUsername());

        List<User> users = this.baseMapper.selectList(ew);
        if (users == null || users.size() == 0) {
            return ResponseUtils.errorMsg("用户不存在");
        }

        if (users.get(0).getStatus().equals(DtoConstants.STATUS_PAUSE)) {
            return ResponseUtils.errorMsg("改用户已被冻结,请联系管理员!");
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
        String token = RequestUtils.getToken(users.get(0).getId()+"",users.get(0).getPassword());
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("userName",users.get(0).getName());

        redisTemplate.opsForValue().set(UserConstants.ADMIN_TOKEN_PREFIX + token,users.get(0).getId());

//        RedisUtils.saveAdminToken(redisTemplate, token,users.get(0).getId());
        return ResponseUtils.convert(map);
    }

    @Override
    public PageBaseResponse page(UserRequest request, String userId) {

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
        /*if(StringUtils.isEmptyStr(frontUserRequest.getId())){
            errMessage="id不能为空";
            return ResponseUtils.errorMsg(errMessage);
        }*/

        this.baseMapper.deleteRecord(userRequest);
        /*EntityWrapper<FrontUser> wrapper = new EntityWrapper<>();
        wrapper.eq("id",frontUserRequest.getId());
        this.delete(wrapper);*/
        return ResponseUtils.success();
    }

    @Override
    public BaseResponse resetPassword(UserRequest userRequest, HttpServletRequest request) {
        {
            /*String err = userRequest.validDate();
            if(!StringUtils.isEmptyStr(err)){
                return ResponseUtils.errorMsg(err);
            }
*/
            String token =  request.getHeader("token");;
            //校验验证码
            String redisKey = UserConstants.ADMIN_TOKEN_PREFIX + token;
            Long adminUserId = Long.parseLong(redisTemplate.opsForValue().get(redisKey) + "");

            if(null == adminUserId){
                return ResponseUtils.errorMsg("用户登录令牌已失效,即将跳转登录页面！");
            }

            User user = this.baseMapper.selectById(adminUserId);

            String password  = user.getPassword();

            if(!password.equals(userRequest.getPassword())){
                return ResponseUtils.errorMsg("原密码输入错误,请重新输入!");
            }

            if(StringUtils.isEmptyStr(userRequest.getPassword())){
                return ResponseUtils.errorMsg("密码不能为空");
            }

            EntityWrapper<User> wrapper = new EntityWrapper<User>();
            wrapper.eq("id",user.getId());

            User updateUser = new User();
            updateUser.setPassword(userRequest.getNewPassword());
            updateUser.setUpdatedBy(updateUser.getId());
            updateUser.setUpdatedTime(new Date());
            this.baseMapper.update(updateUser,wrapper);

            return ResponseUtils.success();
        }
    }
}
