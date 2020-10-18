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
    private String id;
    private String nickname;
    private String password;
    private String phone;
    private String email;
    private String sex;



    public String   validDate(){
        String err="";
        if(StringUtils.isEmpty(phone)){
            err="手机号不能为空";
        }
        return err;
    }
}
