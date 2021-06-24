package com.snail.web.modules.quartz.dto.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.snail.web.constants.ScheduleConstants;
import com.snail.web.dto.BaseRequest;
import com.snail.web.modules.quartz.utils.CronUtils;
import com.snail.web.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;



/**
 * 定时任务调度表 sys_job
 *
 * @author hsq
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_job")
public class SysJob extends BaseRequest
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    @TableField("job_id")
    private Long jobId;

    /** 任务名称 */
    @TableField("job_name")
    private String jobName;

    /** 任务组名 */
    @TableField("job_group")
    private String jobGroup;

    /** 调用目标字符串 */
    @TableField("invoke_target")
    private String invokeTarget;

    /** cron执行表达式 */
    @TableField("cron_expression")
    private String cronExpression;

    /** cron计划策略 */
    //@Excel(name = "计划策略 ", readConverterExp = "0=默认,1=立即触发执行,2=触发一次执行,3=不触发立即执行")
    @TableField("misfire_policy")
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

    /** 是否并发执行（0允许 1禁止） */
    //@Excel(name = "并发执行", readConverterExp = "0=允许,1=禁止")
    private String concurrent;

    /** 任务状态（0正常 1暂停） */
    //@Excel(name = "任务状态", readConverterExp = "0=正常,1=暂停")
    private String status;

    private String remark;



    public Date getNextValidTime()
    {
        if (StringUtils.isNotEmpty(cronExpression))
        {
            return CronUtils.getNextExecution(cronExpression);
        }
        return null;
    }




    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("jobId", getId())
            .append("jobName", getJobName())
            .append("jobGroup", getJobGroup())
            .append("cronExpression", getCronExpression())
            .append("nextValidTime", getNextValidTime())
            .append("misfirePolicy", getMisfirePolicy())
            .append("concurrent", getConcurrent())
            .append("status", getStatus())
            .append("createBy", getCreatedBy())
            .append("createTime", getCreatedTime())
            .append("updateBy", getUpdatedBy())
            .append("updateTime", getUpdatedTime())
            .append("remark", getRemark())
            .toString();
    }
}
