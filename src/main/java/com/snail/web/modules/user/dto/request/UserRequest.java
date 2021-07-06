package com.snail.web.modules.user.dto.request;

import com.snail.web.dto.BaseRequest;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Mei on 2019/7/23.
 */
@Data
@ToString
public class UserRequest extends BaseRequest {
    private String username;
    private String password;
    private String newPassword;

    private String status;
//    private String roleId;
//    private String id;
    private String name;

    public String   validDate(){
        String err="";
        if(StringUtils.isEmpty(username)){
            err="用户名不能为空";
        }

        /*if(StringUtils.isEmpty(roleId)){
            err="角色不能为空";
        }*/
        return err;
    }
}
