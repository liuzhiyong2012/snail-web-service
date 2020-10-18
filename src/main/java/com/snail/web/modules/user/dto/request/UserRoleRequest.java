package com.snail.web.modules.user.dto.request;


import com.snail.web.dto.BaseRequest;
import com.snail.web.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleRequest extends BaseRequest implements Serializable {

//	private String cnName;
//	private String enName;
	private String name;
	private String id;

	public String validSave(){
		if (StringUtils.isEmptyStr(name)) {
			return "name can not be null";
		}
		return null;
	}
}
