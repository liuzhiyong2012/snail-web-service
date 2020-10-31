package com.snail.web.modules.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.snail.web.modules.user.dto.entity.User;
import lombok.Data;

import java.util.Date;

/**
 * Created by Mei on 2019/7/23.
 */
@Data
public class UserReponse extends User {

       private  String token;
       private  String time;

       @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
       private Date updatedTime;
}
