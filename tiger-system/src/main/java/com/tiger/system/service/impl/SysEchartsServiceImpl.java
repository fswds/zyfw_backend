package com.tiger.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tiger.system.domain.vo.EchartsActivityDto;
import com.tiger.system.domain.vo.EchartsOrganizationDto;
import com.tiger.system.mapper.SysActivityMapper;
import com.tiger.system.mapper.SysDictDataMapper;
import com.tiger.system.mapper.SysVolunteerOrganizationMapper;
import com.tiger.system.service.ISysEchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图表 业务层处理
 *
 * @author tiger
 */
@Service
public class SysEchartsServiceImpl implements ISysEchartsService {

    @Autowired
    private SysActivityMapper sysActivityMapper;

    @Autowired
    private SysVolunteerOrganizationMapper sysVolunteerOrganizationMapper;


    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询活动人数
     */
    @Override
    public List<EchartsActivityDto> selectActivityDataList()
    {
        return sysActivityMapper.selectActivityDataList();
    }


    @Override
    public List<EchartsOrganizationDto> listOrganization() {
        return sysVolunteerOrganizationMapper.listOrganization();
    }

    @Override
    public List<EchartsOrganizationDto> activityType() {
        return sysDictDataMapper.activityType();
    }
}
