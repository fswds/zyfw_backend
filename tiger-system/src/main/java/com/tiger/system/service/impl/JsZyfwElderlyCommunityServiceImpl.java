package com.tiger.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiger.system.mapper.JsZyfwElderlyCommunityMapper;
import com.tiger.system.domain.JsZyfwElderlyCommunity;
import com.tiger.system.service.IJsZyfwElderlyCommunityService;
/**
 * 老人信息Service业务层处理
 * 
 * @author wds
 * @date 2025-04-28
 */
@Service
public class JsZyfwElderlyCommunityServiceImpl extends ServiceImpl<JsZyfwElderlyCommunityMapper, JsZyfwElderlyCommunity> implements IJsZyfwElderlyCommunityService
{
    @Autowired
    private JsZyfwElderlyCommunityMapper jsZyfwElderlyCommunityMapper;

    /**
     * 查询老人信息
     * 
     * @param id 老人信息主键
     * @return 老人信息
     */
    @Override
    public JsZyfwElderlyCommunity selectJsZyfwElderlyCommunityById(Long id)
    {
        return jsZyfwElderlyCommunityMapper.selectJsZyfwElderlyCommunityById(id);
    }

    /**
     * 查询老人信息列表
     * 
     * @param jsZyfwElderlyCommunity 老人信息
     * @return 老人信息
     */
    @Override
    public List<JsZyfwElderlyCommunity> selectJsZyfwElderlyCommunityList(JsZyfwElderlyCommunity jsZyfwElderlyCommunity)
    {
        return jsZyfwElderlyCommunityMapper.selectJsZyfwElderlyCommunityList(jsZyfwElderlyCommunity);
    }

    /**
     * 新增老人信息
     * 
     * @param jsZyfwElderlyCommunity 老人信息
     * @return 结果
     */
    @Override
    public int insertJsZyfwElderlyCommunity(JsZyfwElderlyCommunity jsZyfwElderlyCommunity)
    {
        return jsZyfwElderlyCommunityMapper.insertJsZyfwElderlyCommunity(jsZyfwElderlyCommunity);
    }

    /**
     * 修改老人信息
     * 
     * @param jsZyfwElderlyCommunity 老人信息
     * @return 结果
     */
    @Override
    public int updateJsZyfwElderlyCommunity(JsZyfwElderlyCommunity jsZyfwElderlyCommunity)
    {
        return jsZyfwElderlyCommunityMapper.updateJsZyfwElderlyCommunity(jsZyfwElderlyCommunity);
    }

    /**
     * 批量删除老人信息
     * 
     * @param ids 需要删除的老人信息主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwElderlyCommunityByIds(Long[] ids)
    {
        return jsZyfwElderlyCommunityMapper.deleteJsZyfwElderlyCommunityByIds(ids);
    }

    /**
     * 删除老人信息信息
     * 
     * @param id 老人信息主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwElderlyCommunityById(Long id)
    {
        return jsZyfwElderlyCommunityMapper.deleteJsZyfwElderlyCommunityById(id);
    }
}
