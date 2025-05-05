package com.tiger.system.service.impl;

import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiger.common.core.domain.entity.SysUser;
import com.tiger.common.utils.DateUtils;
import com.tiger.system.domain.SysActivity;
import com.tiger.system.domain.SysActivitySignNotice;
import com.tiger.system.mapper.SysActivityMapper;
import com.tiger.system.mapper.SysActivitySignNoticeMapper;
import com.tiger.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiger.system.mapper.SysSignRecordMapper;
import com.tiger.system.domain.SysSignRecord;
import com.tiger.system.service.ISysSignRecordService;

import javax.annotation.Resource;

/**
 * 签到记录Service业务层处理
 * 
 * @author tiger
 * @date 2024-04-28
 */
@Service
public class SysSignRecordServiceImpl extends ServiceImpl<SysSignRecordMapper, SysSignRecord> implements ISysSignRecordService
{
    @Autowired
    private SysSignRecordMapper sysSignRecordMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Resource
    private SysActivityMapper sysActivityMapper;

    @Resource
    private SysActivitySignNoticeMapper sysActivitySignNoticeMapper;

    /**
     * 查询签到记录
     * 
     * @param id 签到记录主键
     * @return 签到记录
     */
    @Override
    public SysSignRecord selectSysSignRecordById(Long id)
    {
        return sysSignRecordMapper.selectSysSignRecordById(id);
    }

    /**
     * 查询签到记录列表
     * 
     * @param sysSignRecord 签到记录
     * @return 签到记录
     */
    @Override
    public List<SysSignRecord> selectSysSignRecordList(SysSignRecord sysSignRecord)
    {
        return sysSignRecordMapper.selectSysSignRecordList(sysSignRecord);
    }

    /**
     * 新增签到记录
     * 
     * @param sysSignRecord 签到记录
     * @return 结果
     */
    @Override
    public int insertSysSignRecord(SysSignRecord sysSignRecord)
    {
        sysSignRecord.setCreateTime(DateUtils.getNowDate());
        return sysSignRecordMapper.insertSysSignRecord(sysSignRecord);
    }

    /**
     * 修改签到记录
     * 
     * @param sysSignRecord 签到记录
     * @return 结果
     */
    @Override
    public int updateSysSignRecord(SysSignRecord sysSignRecord) throws Exception
    {
        sysSignRecord.setUpdateTime(DateUtils.getNowDate());
        int i =  sysSignRecordMapper.updateSysSignRecord(sysSignRecord);
        if (i > 0){
            // 审核通过跟新用户积分
            if ("1".equals(sysSignRecord.getExamineStatus())){
                SysActivitySignNotice sysActivitySignNotice = sysActivitySignNoticeMapper.selectSysActivitySignNoticeByNoticeId(sysSignRecord.getSignNoticeId());
                if (Objects.isNull(sysActivitySignNotice)){
                    throw new Exception("活动通知不存在,activityId="+sysSignRecord.getActivityId());
                }
                SysUser sysUser = sysUserMapper.selectUserById(sysSignRecord.getUserId());
                if (Objects.isNull(sysUser)){
                    throw new Exception("用户不存在,activityId="+sysSignRecord.getActivityId());
                }
                sysUser.setUserId(sysSignRecord.getUserId());
                sysUser.setActivityScore(sysUser.getActivityScore() + sysActivitySignNotice.getSignScore());
                sysUserMapper.updateUser(sysUser);
            }
        }
        return i;
    }

    /**
     * 批量删除签到记录
     * 
     * @param ids 需要删除的签到记录主键
     * @return 结果
     */
    @Override
    public int deleteSysSignRecordByIds(Long[] ids)
    {
        return sysSignRecordMapper.deleteSysSignRecordByIds(ids);
    }

    /**
     * 删除签到记录信息
     * 
     * @param id 签到记录主键
     * @return 结果
     */
    @Override
    public int deleteSysSignRecordById(Long id)
    {
        return sysSignRecordMapper.deleteSysSignRecordById(id);
    }
}
