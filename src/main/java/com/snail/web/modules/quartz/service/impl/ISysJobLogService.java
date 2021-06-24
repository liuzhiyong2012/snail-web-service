package com.snail.web.modules.quartz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.snail.web.modules.quartz.dto.entity.SysJobLog;
import com.snail.web.modules.quartz.mapper.SysJobLogMapper;
import com.snail.web.modules.quartz.service.SysJobLogService;
import com.snail.web.utils.Convert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISysJobLogService extends ServiceImpl<SysJobLogMapper, SysJobLog> implements SysJobLogService {
    /*@Autowired
    private SysJobLogMapper jobLogMapper;*/

    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    @Override
    public List<SysJobLog> selectJobLogList(SysJobLog jobLog)
    {
        return this.baseMapper.selectJobLogList(jobLog);
    }

    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    @Override
    public SysJobLog selectJobLogById(Long jobLogId)
    {
        return  this.baseMapper.selectJobLogById(jobLogId);
    }

    /**
     * 新增任务日志
     *
     * @param jobLog 调度日志信息
     */
    @Override
    public void addJobLog(SysJobLog jobLog)
    {
        this.baseMapper.insertJobLog(jobLog);
    }

    /**
     * 批量删除调度日志信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJobLogByIds(String ids)
    {
        return  this.baseMapper.deleteJobLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务日志
     *
     * @param jobId 调度日志ID
     */
    @Override
    public int deleteJobLogById(Long jobId)
    {
        return  this.baseMapper.deleteJobLogById(jobId);
    }

    /**
     * 清空任务日志
     */
    @Override
    public void cleanJobLog()
    {
        this.baseMapper.cleanJobLog();
    }
}
