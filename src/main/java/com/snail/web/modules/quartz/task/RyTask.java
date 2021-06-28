package com.snail.web.modules.quartz.task;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.snail.web.constants.DtoConstants;
import com.snail.web.modules.advertise.dto.entity.Advertise;
import com.snail.web.modules.advertise.mapper.AdvertiseMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 定时任务调度测试
 *
 * @author hsq
 */
@Component("ryTask")
public class RyTask
{
    /**
     * 自动为商家任务分配用户
     */
    @Resource//(name="springPipelineFactory")
    private PipelineFactory springPipelineFactory;

    private AdvertiseMapper advertiseMapper;

    public void allocateTask(){

        System.out.println("======================开始自动分配任务========================");

    }

    /**
     * 处理双方已确认的任务
     */
    public void confirmTask(){
        System.out.println("======================confirmTask========================");

    }



    /**
     * 定时扫描任务是否需要支付
     * 5种状态需要支付，其中2种是定时任务触发：
     *  1、直接支付
     *      ①上传交付件24小时内，企业确认完成，直接支付给个体户
     *      ②上传交付件24小时后，企业未确认，再过72小时，自动支付给个体户✅
     *  2、保证金
     *      ①任务派发后，直接支付给平台
     *      ②任务派发24小时内，企业确认完成，则从平台支付给个体户
     *      ③任务派发24小时后，企业未确认，则自动从平台支付给个体户✅
     */
    public void settlementTask(){
        System.out.println("======================settlementTask========================");


    }

    public void  taskTimeoutWarning(){
        System.out.println("======================taskTimeoutWarning========================");
    }

    public void  spider(){
        System.out.println("======================test========================");

        GeccoEngine.create()
                .pipelineFactory(springPipelineFactory)
                .classpath("com.snail.web.modules.crawler.spider")
                .seed("http://www.weishangshijie.cn/news/")
                .seed("http://www.weishangshijie.cn/duanzi/")
                .seed("http://www.weishangshijie.cn/xueyuan/")
                .seed("http://weishangshijie.204m.com/index/")
                .thread(3)
                //* .interval(300000000)*//*
                .debug(false)
                .loop(false)
                //	.stop()
                .start();


    }
    //校验用户的广告是否过期。
    public void  checkAdvertiseExpire(){

        System.out.println("======================用户日期校验器开始工作========================");
        EntityWrapper<Advertise> wrapper = new EntityWrapper<Advertise>();
        wrapper.eq("status", DtoConstants.STATUS_NORMAL);
        wrapper.eq("is_deleted", DtoConstants.IS_DELETE_NO);

        List<Advertise> advertiseList =  advertiseMapper.selectList(wrapper);
        Date nowTime = new Date();
        for(Advertise advertise:advertiseList){

            Date endTime = advertise.getEndTime();
            if(null!=endTime&&("".equals(endTime))&&nowTime.compareTo(endTime) > 0){
                advertise.setStatus(DtoConstants.STATUS_PAUSE);
                advertiseMapper.updateAllColumnById(advertise);
                System.out.println("=================更新了一条过期记录====================");
            }

        }



    }
}
