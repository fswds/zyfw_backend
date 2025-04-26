package com.tiger.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiger.common.utils.DateUtils;
import com.tiger.system.domain.SysVolunteerActivityRelation;
import com.tiger.system.domain.vo.SysActivityDto;
import com.tiger.system.domain.vo.SysActivityRelationDto;
import com.tiger.system.mapper.SysVolunteerActivityRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiger.system.mapper.SysActivityMapper;
import com.tiger.system.domain.SysActivity;
import com.tiger.system.service.ISysActivityService;
/**
 * 活动信息Service业务层处理
 * 
 * @author tiger
 * @date 2024-03-24
 */
@Service
public class SysActivityServiceImpl extends ServiceImpl<SysActivityMapper, SysActivity> implements ISysActivityService
{
    @Autowired
    private SysActivityMapper sysActivityMapper;

    @Autowired
    private SysVolunteerActivityRelationMapper sysVolunteerActivityRelationMapper;

    /**
     * 查询活动信息
     * 
     * @param id 活动信息主键
     * @return 活动信息
     */
    @Override
    public SysActivity selectSysActivityById(Long id)
    {
        return sysActivityMapper.selectSysActivityById(id);
    }

    /**
     * 查询活动信息列表
     * 
     * @param sysActivity 活动信息
     * @return 活动信息
     */
    @Override
    public List<SysActivityDto> selectSysActivityList(SysActivity sysActivity)
    {
        return sysActivityMapper.selectSysActivityList(sysActivity);
    }

    /**
     * 新增活动信息
     * 
     * @param sysActivity 活动信息
     * @return 结果
     */
    @Override
    public int insertSysActivity(SysActivity sysActivity)
    {
        return sysActivityMapper.insertSysActivity(sysActivity);
    }

    /**
     * 修改活动信息
     * 
     * @param sysActivity 活动信息
     * @return 结果
     */
    @Override
    public int updateSysActivity(SysActivity sysActivity)
    {
        return sysActivityMapper.updateSysActivity(sysActivity);
    }

    /**
     * 批量删除活动信息
     * 
     * @param ids 需要删除的活动信息主键
     * @return 结果
     */
    @Override
    public int deleteSysActivityByIds(Long[] ids) throws Exception
    {
        SysActivity sysActivity = sysActivityMapper.selectSysActivityById(ids[0]);
        Long id = sysActivity.getId();
        SysVolunteerActivityRelation sysVolunteerActivityRelation = new SysVolunteerActivityRelation();
        sysVolunteerActivityRelation.setActivityId(id);
        List<SysActivityRelationDto> sysActivityRelationDtos = sysVolunteerActivityRelationMapper.selectSysVolunteerActivityRelationList(sysVolunteerActivityRelation);
        Optional<SysActivityRelationDto> first = sysActivityRelationDtos.stream().filter(a -> a.getUserId() != null).findFirst();
        if (first.isPresent()){
            if (sysActivity.getStartTime().compareTo(new Date()) > 0){
                log.warn("活动已经有志愿者已经加入，不允许删除活动");
                throw new Exception("活动已经有志愿者已经加入，不允许删除活动");
            }
        }
        if (first.isPresent()){
            if (sysActivity.getStartTime().compareTo(new Date()) < 0 && sysActivity.getEndTime().compareTo(new Date()) > 0){
                log.warn("活动正在进行中，且志愿者已经加入，不允许删除活动");
                throw new Exception("活动正在进行中，，且志愿者已经加入，不允许删除活动");
            }
        }

        return sysActivityMapper.deleteSysActivityByIds(ids);
    }

    /**
     * 删除活动信息信息
     * 
     * @param id 活动信息主键
     * @return 结果
     */
    @Override
    public int deleteSysActivityById(Long id) throws Exception
    {
        SysActivity sysActivity = sysActivityMapper.selectSysActivityById(id);
        Long id2 = sysActivity.getId();
        SysVolunteerActivityRelation sysVolunteerActivityRelation = new SysVolunteerActivityRelation();
        sysVolunteerActivityRelation.setActivityId(id2);
        List<SysActivityRelationDto> sysActivityRelationDtos = sysVolunteerActivityRelationMapper.selectSysVolunteerActivityRelationList(sysVolunteerActivityRelation);
        Optional<SysActivityRelationDto> first = sysActivityRelationDtos.stream().filter(a -> a.getUserId() != null).findFirst();
        if (first.isPresent()){
            if (sysActivity.getStartTime().compareTo(new Date()) < 0 && sysActivity.getEndTime().compareTo(new Date()) > 0){
                log.warn("活动正在进行中，且志愿者已经加入，不允许删除活动");
                throw new Exception("活动正在进行中，，且志愿者已经加入，不允许删除活动");
            }
        }
        return sysActivityMapper.deleteSysActivityById(id);
    }
}
