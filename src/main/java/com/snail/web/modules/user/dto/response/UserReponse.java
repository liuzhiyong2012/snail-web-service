package com.snail.web.modules.user.dto.response;

import com.snail.web.modules.user.dto.entity.User;
import lombok.Data;

/**
 * Created by Mei on 2019/7/23.
 */
@Data
public class UserReponse extends User {
//    private String id;
//    private String username;
//    private String roleId;
    private String roleName;
//    private Date createdTime;
    private String createdUser;
//    private Date updatedTime;
    private String updatedUser;
}
