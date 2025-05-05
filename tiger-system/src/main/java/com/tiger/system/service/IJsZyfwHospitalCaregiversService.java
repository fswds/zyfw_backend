package com.tiger.system.service;

import java.util.List;
import com.tiger.system.domain.JsZyfwHospitalCaregivers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 护理人员信息管理Service接口
 * 
 * @author wds
 * @date 2025-04-28
 */
public interface IJsZyfwHospitalCaregiversService extends IService<JsZyfwHospitalCaregivers> {
    /**
     * 查询护理人员信息管理
     * 
     * @param id 护理人员信息管理主键
     * @return 护理人员信息管理
     */
    JsZyfwHospitalCaregivers selectJsZyfwHospitalCaregiversById(Long id);

    /**
     * 查询护理人员信息管理列表
     * 
     * @param jsZyfwHospitalCaregivers 护理人员信息管理
     * @return 护理人员信息管理集合
     */
    List<JsZyfwHospitalCaregivers> selectJsZyfwHospitalCaregiversList(JsZyfwHospitalCaregivers jsZyfwHospitalCaregivers);

    /**
     * 新增护理人员信息管理
     * 
     * @param jsZyfwHospitalCaregivers 护理人员信息管理
     * @return 结果
     */
    int insertJsZyfwHospitalCaregivers(JsZyfwHospitalCaregivers jsZyfwHospitalCaregivers);

    /**
     * 修改护理人员信息管理
     * 
     * @param jsZyfwHospitalCaregivers 护理人员信息管理
     * @return 结果
     */
    int updateJsZyfwHospitalCaregivers(JsZyfwHospitalCaregivers jsZyfwHospitalCaregivers);

    /**
     * 批量删除护理人员信息管理
     * 
     * @param ids 需要删除的护理人员信息管理主键集合
     * @return 结果
     */
    int deleteJsZyfwHospitalCaregiversByIds(Long[] ids);

    /**
     * 删除护理人员信息管理信息
     * 
     * @param id 护理人员信息管理主键
     * @return 结果
     */
    int deleteJsZyfwHospitalCaregiversById(Long id);
}
