package com.tiger.system.mapper;

import java.util.List;
import com.tiger.system.domain.JsZyfwVolunteerServiceBuildings;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 建筑管理Mapper接口
 * 
 * @author wds
 * @date 2025-04-28
 */
public interface JsZyfwVolunteerServiceBuildingsMapper extends BaseMapper<JsZyfwVolunteerServiceBuildings> {
    /**
     * 查询建筑管理
     *
     * @param id 建筑管理主键
     * @return 建筑管理
     */
    JsZyfwVolunteerServiceBuildings selectJsZyfwVolunteerServiceBuildingsById(Long id);

    /**
     * 查询建筑管理列表
     *
     * @param jsZyfwVolunteerServiceBuildings 建筑管理
     * @return 建筑管理集合
     */
    List<JsZyfwVolunteerServiceBuildings> selectJsZyfwVolunteerServiceBuildingsList(JsZyfwVolunteerServiceBuildings jsZyfwVolunteerServiceBuildings);

    /**
     * 新增建筑管理
     *
     * @param jsZyfwVolunteerServiceBuildings 建筑管理
     * @return 结果
     */
    int insertJsZyfwVolunteerServiceBuildings(JsZyfwVolunteerServiceBuildings jsZyfwVolunteerServiceBuildings);

    /**
     * 修改建筑管理
     *
     * @param jsZyfwVolunteerServiceBuildings 建筑管理
     * @return 结果
     */
    int updateJsZyfwVolunteerServiceBuildings(JsZyfwVolunteerServiceBuildings jsZyfwVolunteerServiceBuildings);

    /**
     * 删除建筑管理
     *
     * @param id 建筑管理主键
     * @return 结果
     */
    int deleteJsZyfwVolunteerServiceBuildingsById(Long id);

    /**
     * 批量删除建筑管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteJsZyfwVolunteerServiceBuildingsByIds(Long[] ids);
}
