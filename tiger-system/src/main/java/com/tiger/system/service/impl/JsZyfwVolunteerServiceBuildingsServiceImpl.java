package com.tiger.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiger.system.mapper.JsZyfwVolunteerServiceBuildingsMapper;
import com.tiger.system.domain.JsZyfwVolunteerServiceBuildings;
import com.tiger.system.service.IJsZyfwVolunteerServiceBuildingsService;
/**
 * 建筑管理Service业务层处理
 * 
 * @author wds
 * @date 2025-04-28
 */
@Service
public class JsZyfwVolunteerServiceBuildingsServiceImpl extends ServiceImpl<JsZyfwVolunteerServiceBuildingsMapper, JsZyfwVolunteerServiceBuildings> implements IJsZyfwVolunteerServiceBuildingsService
{
    @Autowired
    private JsZyfwVolunteerServiceBuildingsMapper jsZyfwVolunteerServiceBuildingsMapper;

    /**
     * 查询建筑管理
     * 
     * @param id 建筑管理主键
     * @return 建筑管理
     */
    @Override
    public JsZyfwVolunteerServiceBuildings selectJsZyfwVolunteerServiceBuildingsById(Long id)
    {
        return jsZyfwVolunteerServiceBuildingsMapper.selectJsZyfwVolunteerServiceBuildingsById(id);
    }

    /**
     * 查询建筑管理列表
     * 
     * @param jsZyfwVolunteerServiceBuildings 建筑管理
     * @return 建筑管理
     */
    @Override
    public List<JsZyfwVolunteerServiceBuildings> selectJsZyfwVolunteerServiceBuildingsList(JsZyfwVolunteerServiceBuildings jsZyfwVolunteerServiceBuildings)
    {
        return jsZyfwVolunteerServiceBuildingsMapper.selectJsZyfwVolunteerServiceBuildingsList(jsZyfwVolunteerServiceBuildings);
    }

    /**
     * 新增建筑管理
     * 
     * @param jsZyfwVolunteerServiceBuildings 建筑管理
     * @return 结果
     */
    @Override
    public int insertJsZyfwVolunteerServiceBuildings(JsZyfwVolunteerServiceBuildings jsZyfwVolunteerServiceBuildings)
    {
        return jsZyfwVolunteerServiceBuildingsMapper.insertJsZyfwVolunteerServiceBuildings(jsZyfwVolunteerServiceBuildings);
    }

    /**
     * 修改建筑管理
     * 
     * @param jsZyfwVolunteerServiceBuildings 建筑管理
     * @return 结果
     */
    @Override
    public int updateJsZyfwVolunteerServiceBuildings(JsZyfwVolunteerServiceBuildings jsZyfwVolunteerServiceBuildings)
    {
        return jsZyfwVolunteerServiceBuildingsMapper.updateJsZyfwVolunteerServiceBuildings(jsZyfwVolunteerServiceBuildings);
    }

    /**
     * 批量删除建筑管理
     * 
     * @param ids 需要删除的建筑管理主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwVolunteerServiceBuildingsByIds(Long[] ids)
    {
        return jsZyfwVolunteerServiceBuildingsMapper.deleteJsZyfwVolunteerServiceBuildingsByIds(ids);
    }

    /**
     * 删除建筑管理信息
     * 
     * @param id 建筑管理主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwVolunteerServiceBuildingsById(Long id)
    {
        return jsZyfwVolunteerServiceBuildingsMapper.deleteJsZyfwVolunteerServiceBuildingsById(id);
    }
}
