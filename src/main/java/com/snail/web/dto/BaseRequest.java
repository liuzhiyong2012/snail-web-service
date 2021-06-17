package com.snail.web.dto;


import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Uvaso on 2019/8/14.
 */
@Data
public class BaseRequest  extends BaseEntity implements Serializable {
    @TableField(exist = false)
    private Integer pageNumber;

    @TableField(exist = false)
    private Integer pageSize;


    @TableField(exist = false)
    private Integer start;


    @TableField(exist = false)
    private String keyword;

    public void setPageNumber(Integer page) {
        this.pageNumber = page;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getStart(){
        return pageNumber==null?0:(pageNumber-1)*pageSize;
    }

    public void initPageParam(){
        pageNumber =pageNumber==null?1:pageNumber;
        pageSize = pageSize==null?10:pageSize;
    }

    public Date getStartDate(Date date){
        long current=date.getTime();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        return new Date(zero);
//        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
//        long yesterday=System.currentTimeMillis()-24*60*60*1000;//昨天的这一时间的毫秒数
//        System.out.println(new Timestamp(current));//当前时间
//        System.out.println(new Timestamp(yesterday));//昨天这一时间点
//        System.out.println(new Timestamp(zero));//今天零点零分零秒
//        System.out.println(new Timestamp(twelve));//今天23点59分59秒
    }
    public Date getEndDate(Date date){
        long current=date.getTime();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
        return new Date(twelve);
    }
    public Date getMonthStart(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return getStartDate(calendar.getTime());
    }
    public Date getMonthEnd(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getEndDate(calendar.getTime());
    }
}
