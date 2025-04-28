package com.tiger.system.service;

import java.util.List;
import com.tiger.system.domain.JsZyfwElderlyCommunity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 老人信息Service接口
 * 
 * @author wds
 * @date 2025-04-28
 */
public interface IJsZyfwElderlyCommunityService extends IService<JsZyfwElderlyCommunity> {
    /**
     * 查询老人信息
     * 
     * @param id 老人信息主键
     * @return 老人信息
     */
    JsZyfwElderlyCommunity selectJsZyfwElderlyCommunityById(Long id);

    /**
     * 查询老人信息列表
     * 
     * @param jsZyfwElderlyCommunity 老人信息
     * @return 老人信息集合
     */
    List<JsZyfwElderlyCommunity> selectJsZyfwElderlyCommunityList(JsZyfwElderlyCommunity jsZyfwElderlyCommunity);

    /**
     * 新增老人信息
     * 
     * @param jsZyfwElderlyCommunity 老人信息
     * @return 结果
     */
    int insertJsZyfwElderlyCommunity(JsZyfwElderlyCommunity jsZyfwElderlyCommunity);

    /**
     * 修改老人信息
     * 
     * @param jsZyfwElderlyCommunity 老人信息
     * @return 结果
     */
    int updateJsZyfwElderlyCommunity(JsZyfwElderlyCommunity jsZyfwElderlyCommunity);

    /**
     * 批量删除老人信息
     * 
     * @param ids 需要删除的老人信息主键集合
     * @return 结果
     */
    int deleteJsZyfwElderlyCommunityByIds(Long[] ids);

    /**
     * 删除老人信息信息
     * 
     * @param id 老人信息主键
     * @return 结果
     */
    int deleteJsZyfwElderlyCommunityById(Long id);
}
