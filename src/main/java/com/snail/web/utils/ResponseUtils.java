package com.snail.web.utils;


import com.snail.web.entity.BaseResponse;
import com.snail.web.entity.PageBaseResponse;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * 返回参数公共类
 * @author Administrator
 *
 */
@Slf4j
public class ResponseUtils {

	public static BaseResponse convert(Object data) {
		return convert(1000, "成功", data);
	}

//	public static BaseResponse errorMsg(ErrorCodeEnum errorCodeEnum) {
//		BaseResponse baseResponse = new BaseResponse();
//		baseResponse.setCode(errorCodeEnum.getCode());
//		baseResponse.setMessage(errorCodeEnum.getMessage());
//
//		return baseResponse;
//	}
//
//	public static PageBaseResponse errorPageMsg(ErrorCodeEnum  errorCodeEnum) {
//		PageBaseResponse pageBaseResponse = new PageBaseResponse();
//		pageBaseResponse.setCode(errorCodeEnum.getCode());
//		pageBaseResponse.setMessage(errorCodeEnum.getMessage());
//
//		return pageBaseResponse;
//	}

	public static BaseResponse errorMsg(String message) {
		BaseResponse br = new BaseResponse();
		br.setCode(2000);
		br.setMessage(message);

		return br;
	}

	public static BaseResponse nullMsg(String nullObject) {
		BaseResponse br = new BaseResponse();
		br.setCode(1006);
		br.setMessage(nullObject+" can not be null");

		return br;
	}

	public static BaseResponse convert(Integer code, String message, Object data) {
		BaseResponse entity = new BaseResponse();
		entity.setCode(code);
		entity.setMessage(message);
		entity.setDatas(data);
		return entity;
	}

	public static PageBaseResponse pageSuccess() {
		return pageConvert(1, 10, 0, new ArrayList());
	}

	public static PageBaseResponse pageConvert(Integer total, Object data) {
		return pageConvert(null, null, total, data);
	}
	public static PageBaseResponse pageConvert(Integer page, Integer pageSize, Integer total, Object data) {
		PageBaseResponse pageBaseResponse = new PageBaseResponse();
		pageBaseResponse.setCode(1000);
		pageBaseResponse.setMessage("success");
		pageBaseResponse.setPageNumber(page);
		pageBaseResponse.setPageSize(pageSize);
		pageBaseResponse.setTotal(total);
		pageBaseResponse.setDatas(data);
		if(total != null && pageSize != null)
			pageBaseResponse.setTotalPage(PageUtils.countTotalPage(total, pageSize));
		return pageBaseResponse;
	}

	public static PageBaseResponse pageError(String message) {
		PageBaseResponse pageBaseResponse = new PageBaseResponse();
		pageBaseResponse.setCode(2000);
		pageBaseResponse.setMessage(message);
		pageBaseResponse.setDatas(new ArrayList());
		return pageBaseResponse;
	}

    public static BaseResponse success() {
		return convert(null);
    }

    public static BaseResponse errorParam(String paramName){
		BaseResponse br = new BaseResponse();
		br.setCode(1006);
		br.setMessage(paramName+" can not be null");
		return br;
	}


	public static void response(String  fileName, HttpServletResponse response, HttpServletRequest request) {
		final String userAgent = request.getHeader("USER-AGENT");
		try {

			response.reset();
			String finalFileName = null;
			if (userAgent.indexOf("MSIE")!=-1 || userAgent.indexOf("Trident")!=-1 || userAgent.indexOf("Edge")!=-1 ) {     // ie
				finalFileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			} else if (null != userAgent && -1 != userAgent.indexOf("Mozilla")) {           // 火狐,chrome等
				finalFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			}
			response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFileName + "\"");//这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
			response.setContentType("application/vnd.ms-excel");
		} catch (Exception e) {
//			throw new Exception(e);
		}
	}

}
