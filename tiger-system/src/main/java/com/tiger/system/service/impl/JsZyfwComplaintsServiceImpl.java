package com.tiger.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiger.system.mapper.JsZyfwComplaintsMapper;
import com.tiger.system.domain.JsZyfwComplaints;
import com.tiger.system.service.IJsZyfwComplaintsService;
/**
 * 投诉反馈Service业务层处理
 * 
 * @author tiger
 * @date 2025-04-28
 */
@Service
public class JsZyfwComplaintsServiceImpl extends ServiceImpl<JsZyfwComplaintsMapper, JsZyfwComplaints> implements IJsZyfwComplaintsService
{
    @Autowired
    private JsZyfwComplaintsMapper jsZyfwComplaintsMapper;

    /**
     * 查询投诉反馈
     * 
     * @param id 投诉反馈主键
     * @return 投诉反馈
     */
    @Override
    public JsZyfwComplaints selectJsZyfwComplaintsById(Long id)
    {
        return jsZyfwComplaintsMapper.selectJsZyfwComplaintsById(id);
    }

    /**
     * 查询投诉反馈列表
     * 
     * @param jsZyfwComplaints 投诉反馈
     * @return 投诉反馈
     */
    @Override
    public List<JsZyfwComplaints> selectJsZyfwComplaintsList(JsZyfwComplaints jsZyfwComplaints)
    {
        return jsZyfwComplaintsMapper.selectJsZyfwComplaintsList(jsZyfwComplaints);
    }

    /**
     * 新增投诉反馈
     * 
     * @param jsZyfwComplaints 投诉反馈
     * @return 结果
     */
    @Override
    public int insertJsZyfwComplaints(JsZyfwComplaints jsZyfwComplaints)
    {
        return jsZyfwComplaintsMapper.insertJsZyfwComplaints(jsZyfwComplaints);
    }

    /**
     * 修改投诉反馈
     * 
     * @param jsZyfwComplaints 投诉反馈
     * @return 结果
     */
    @Override
    public int updateJsZyfwComplaints(JsZyfwComplaints jsZyfwComplaints)
    {
        return jsZyfwComplaintsMapper.updateJsZyfwComplaints(jsZyfwComplaints);
    }

    /**
     * 批量删除投诉反馈
     * 
     * @param ids 需要删除的投诉反馈主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwComplaintsByIds(Long[] ids)
    {
        return jsZyfwComplaintsMapper.deleteJsZyfwComplaintsByIds(ids);
    }

    /**
     * 删除投诉反馈信息
     * 
     * @param id 投诉反馈主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwComplaintsById(Long id)
    {
        return jsZyfwComplaintsMapper.deleteJsZyfwComplaintsById(id);
    }
}
