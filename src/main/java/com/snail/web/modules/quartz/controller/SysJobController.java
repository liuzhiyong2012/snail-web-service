package com.snail.web.modules.quartz.controller;

import com.snail.web.constants.BaseConstant;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.exception.job.TaskException;
import com.snail.web.modules.quartz.dto.entity.SysJob;
import com.snail.web.modules.quartz.service.SysJobService;
import com.snail.web.modules.quartz.utils.CronUtils;
import com.snail.web.utils.ResponseUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/jobs")
public class SysJobController {


    @Autowired
    private SysJobService jobService;



    @PostMapping("/page")
    @ResponseBody
    public PageBaseResponse page(@RequestBody SysJob job, HttpServletRequest request)
    {

        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        return jobService.page(job,userId);

        /*List<SysJob> list = jobService.selectJobList(job);
        return getDataTable(list);*/
    }


    @PostMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestBody SysJob job, HttpServletRequest request)  throws SchedulerException, TaskException
    {


        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        jobService.deleteJob(job);
        return ResponseUtils.success();

        /*List<SysJob> list = jobService.selectJobList(job);
        return getDataTable(list);*/
    }

   /* @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) throws SchedulerException
    {
        jobService.deleteJobByIds(ids);
        return success();
    }*/




    /**
     * 任务调度状态修改
     */

  /*
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysJob job) throws SchedulerException
    {
        SysJob newJob = jobService.selectJobById(job.getId());
        newJob.setStatus(job.getStatus());
        return toAjax(jobService.changeStatus(newJob));
    }*/

    /**
     * 任务调度立即执行一次
     */

   /* @PostMapping("/run")
    @ResponseBody
    public AjaxResult run(SysJob job) throws SchedulerException
    {
        jobService.run(job);
        return success();
    }*/


    /**
     * 新增保存调度
     */

/*    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysJob job) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(job.getCronExpression()))
        {
            return AjaxResult.error("cron表达式不正确");
        }
        return toAjax(jobService.insertJob(job));
    }*/

    @PostMapping("/insert")
    @ResponseBody
    public BaseResponse insert(@RequestBody SysJob job, HttpServletRequest request)  throws SchedulerException, TaskException
    {

        if (!CronUtils.isValid(job.getCronExpression()))
        {
            return ResponseUtils.errorMsg("cron表达式不正确");
        }
        String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
        jobService.insertJob(job);
        return ResponseUtils.success();

        /*List<SysJob> list = jobService.selectJobList(job);
        return getDataTable(list);*/
    }




   /* @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysJob job) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(job.getCronExpression()))
        {
            return AjaxResult.error("cron表达式不正确");
        }
        return toAjax(jobService.updateJob(job));
    }*/
   @PostMapping("/update")
   @ResponseBody
   public BaseResponse update(@RequestBody SysJob job, HttpServletRequest request)  throws SchedulerException, TaskException
   {

       if (!CronUtils.isValid(job.getCronExpression()))
       {
           return ResponseUtils.errorMsg("cron表达式不正确");
       }
       String userId = request.getParameter(BaseConstant.FRONT_USER_KEY);
       jobService.updateJob(job);
       return ResponseUtils.success();

        /*List<SysJob> list = jobService.selectJobList(job);
        return getDataTable(list);*/
   }

    /**
     * 校验cron表达式是否有效
     */
    /*@PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public boolean checkCronExpressionIsValid(SysJob job)
    {
        return jobService.checkCronExpressionIsValid(job.getCronExpression());
    }*/
}
