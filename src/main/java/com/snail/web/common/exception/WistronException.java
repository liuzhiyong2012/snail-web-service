package com.snail.web.common.exception;
/**
* @author: guijin
* @remark: 自定义内部业务异常
* @date: 2019/12/10
*/
public class WistronException extends RuntimeException {

    public WistronException(){
        super();
    }

    public WistronException(String msg){
        super(msg);
    }

    public WistronException(Throwable cause){
        super(cause);
    }

}
