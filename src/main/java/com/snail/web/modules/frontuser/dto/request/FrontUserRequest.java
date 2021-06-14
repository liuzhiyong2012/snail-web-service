package com.snail.web.modules.frontuser.dto.request;

import com.snail.web.dto.BaseRequest;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Mei on 2019/7/23.
 */
@Data
@ToString
public class FrontUserRequest extends BaseRequest {

//    private Integer pageNumber;
//    private Integer pageSize;
    private String id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String roleType;

    public String   validDate(){
        String err="";
        if(StringUtils.isEmpty(username)){
            err="用户名不能为空";
        }

        return err;
    }
}
