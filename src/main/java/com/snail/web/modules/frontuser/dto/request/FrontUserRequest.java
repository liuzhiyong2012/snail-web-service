package com.snail.web.modules.frontuser.dto.request;

import com.snail.web.dto.BaseRequest;
import lombok.Data;
import lombok.ToString;

/**
 * Created by Mei on 2019/7/23.
 */
@Data
@ToString
public class FrontUserRequest extends BaseRequest {
//    private String id;
    private String username;
    private String password;
    private String token;
    private String name;
    private String phone;
    private String flag;
    private String code;
    private String email;
    private String status;
    private String roleType;

    public String   validDate(){
        String err="";
        /*if(StringUtils.isEmpty(username)){
            err="用户名不能为空";
        }*/

        return err;
    }


}
