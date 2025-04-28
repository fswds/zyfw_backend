package com.tiger.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiger.system.mapper.JsZyfwHospitalCaregiversMapper;
import com.tiger.system.domain.JsZyfwHospitalCaregivers;
import com.tiger.system.service.IJsZyfwHospitalCaregiversService;
/**
 * 护理人员信息管理Service业务层处理
 * 
 * @author wds
 * @date 2025-04-28
 */
@Service
public class JsZyfwHospitalCaregiversServiceImpl extends ServiceImpl<JsZyfwHospitalCaregiversMapper, JsZyfwHospitalCaregivers> implements IJsZyfwHospitalCaregiversService
{
    @Autowired
    private JsZyfwHospitalCaregiversMapper jsZyfwHospitalCaregiversMapper;

    /**
     * 查询护理人员信息管理
     * 
     * @param id 护理人员信息管理主键
     * @return 护理人员信息管理
     */
    @Override
    public JsZyfwHospitalCaregivers selectJsZyfwHospitalCaregiversById(Long id)
    {
        return jsZyfwHospitalCaregiversMapper.selectJsZyfwHospitalCaregiversById(id);
    }

    /**
     * 查询护理人员信息管理列表
     * 
     * @param jsZyfwHospitalCaregivers 护理人员信息管理
     * @return 护理人员信息管理
     */
    @Override
    public List<JsZyfwHospitalCaregivers> selectJsZyfwHospitalCaregiversList(JsZyfwHospitalCaregivers jsZyfwHospitalCaregivers)
    {
        return jsZyfwHospitalCaregiversMapper.selectJsZyfwHospitalCaregiversList(jsZyfwHospitalCaregivers);
    }

    /**
     * 新增护理人员信息管理
     * 
     * @param jsZyfwHospitalCaregivers 护理人员信息管理
     * @return 结果
     */
    @Override
    public int insertJsZyfwHospitalCaregivers(JsZyfwHospitalCaregivers jsZyfwHospitalCaregivers)
    {
        return jsZyfwHospitalCaregiversMapper.insertJsZyfwHospitalCaregivers(jsZyfwHospitalCaregivers);
    }

    /**
     * 修改护理人员信息管理
     * 
     * @param jsZyfwHospitalCaregivers 护理人员信息管理
     * @return 结果
     */
    @Override
    public int updateJsZyfwHospitalCaregivers(JsZyfwHospitalCaregivers jsZyfwHospitalCaregivers)
    {
        return jsZyfwHospitalCaregiversMapper.updateJsZyfwHospitalCaregivers(jsZyfwHospitalCaregivers);
    }

    /**
     * 批量删除护理人员信息管理
     * 
     * @param ids 需要删除的护理人员信息管理主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwHospitalCaregiversByIds(Long[] ids)
    {
        return jsZyfwHospitalCaregiversMapper.deleteJsZyfwHospitalCaregiversByIds(ids);
    }

    /**
     * 删除护理人员信息管理信息
     * 
     * @param id 护理人员信息管理主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwHospitalCaregiversById(Long id)
    {
        return jsZyfwHospitalCaregiversMapper.deleteJsZyfwHospitalCaregiversById(id);
    }
}
