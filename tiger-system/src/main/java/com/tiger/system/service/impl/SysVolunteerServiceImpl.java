package com.tiger.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiger.common.core.domain.entity.SysRole;
import com.tiger.common.utils.DateUtils;
import com.tiger.system.domain.SysActivity;
import com.tiger.system.domain.SysUserRole;
import com.tiger.system.domain.SysVolunteerActivityRelation;
import com.tiger.system.mapper.*;
import com.tiger.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiger.system.domain.SysVolunteer;
import com.tiger.system.service.ISysVolunteerService;
import org.springframework.util.CollectionUtils;

/**
 * 志愿者信息Service业务层处理
 * 
 * @author tiger
 * @date 2024-03-24
 */
@Service
public class SysVolunteerServiceImpl extends ServiceImpl<SysVolunteerMapper, SysVolunteer> implements ISysVolunteerService
{
    @Autowired
    private SysVolunteerMapper sysVolunteerMapper;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysVolunteerActivityRelationMapper sysVolunteerActivityRelationMapper;

    @Autowired
    private SysActivityMapper sysActivityMapper;


    /**
     * 查询志愿者信息
     * 
     * @param id 志愿者信息主键
     * @return 志愿者信息
     */
    @Override
    public SysVolunteer selectSysVolunteerById(Long id)
    {
        return sysVolunteerMapper.selectSysVolunteerById(id);
    }

    /**
     * 查询志愿者信息列表
     * 
     * @param sysVolunteer 志愿者信息
     * @return 志愿者信息
     */
    @Override
    public List<SysVolunteer> selectSysVolunteerList(SysVolunteer sysVolunteer)
    {
        return sysVolunteerMapper.selectSysVolunteerList(sysVolunteer);
    }

    /**
     * 新增志愿者信息
     * 
     * @param sysVolunteer 志愿者信息
     * @return 结果
     */
    @Override
    public int insertSysVolunteer(SysVolunteer sysVolunteer)
    {
        return sysVolunteerMapper.insertSysVolunteer(sysVolunteer);
    }

    /**
     * 修改志愿者信息
     * 
     * @param sysVolunteer 志愿者信息
     * @return 结果
     */
    @Override
    public int updateSysVolunteer(SysVolunteer sysVolunteer)
    {
        sysVolunteer.setUpdateTime(DateUtils.getNowDate());
        List<SysRole> sysRoles = sysRoleService.selectRolesByUserId(sysVolunteer.getUserId());
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(sysVolunteer.getUserId());
        userRole.setRoleId(5L);

        if ("2".equals(sysVolunteer.getStatus())){
            List<SysUserRole> roleList = new ArrayList<>();
            roleList.add(userRole);
           try {
               userRoleMapper.batchUserRole(roleList);
           }catch (Exception e){

           }
        }else {
            userRoleMapper.deleteUserRoleInfo(userRole);
        }
        return sysVolunteerMapper.updateSysVolunteer(sysVolunteer);
    }

    /**
     * 批量删除志愿者信息
     * 
     * @param ids 需要删除的志愿者信息主键
     * @return 结果
     */
    @Override
    public int deleteSysVolunteerByIds(Long[] ids) throws Exception
    {
        LambdaQueryWrapper<SysVolunteerActivityRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysVolunteerActivityRelation::getVolunteerId,ids[0]);
        List<SysVolunteerActivityRelation> activityRelationList = sysVolunteerActivityRelationMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(activityRelationList)){
            return sysVolunteerMapper.deleteSysVolunteerByIds(ids);
        }
        List<Long> activityList = activityRelationList.stream().map(SysVolunteerActivityRelation::getActivityId).collect(Collectors.toList());
        LambdaQueryWrapper<SysActivity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Date date = new Date();
        lambdaQueryWrapper.ge(SysActivity::getEndTime,date);
        lambdaQueryWrapper.in(SysActivity::getId,activityList);
        List<SysActivity> sysActivityList = sysActivityMapper.selectList(lambdaQueryWrapper);
        if (!CollectionUtils.isEmpty(sysActivityList)){
            log.warn("活动未结束，不允许删除参加活动的管理员");
            throw new Exception("活动未结束，不允许删除参加活动的管理员");
        }
        return sysVolunteerMapper.deleteSysVolunteerByIds(ids);
    }

    /**
     * 删除志愿者信息信息
     * 
     * @param id 志愿者信息主键
     * @return 结果
     */
    @Override
    public int deleteSysVolunteerById(Long id)
    {
        return sysVolunteerMapper.deleteSysVolunteerById(id);
    }
}
