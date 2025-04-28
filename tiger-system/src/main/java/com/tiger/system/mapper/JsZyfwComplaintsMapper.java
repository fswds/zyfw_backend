package com.tiger.system.mapper;

import java.util.List;
import com.tiger.system.domain.JsZyfwComplaints;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 投诉反馈Mapper接口
 * 
 * @author tiger
 * @date 2025-04-28
 */
public interface JsZyfwComplaintsMapper extends BaseMapper<JsZyfwComplaints> {
    /**
     * 查询投诉反馈
     *
     * @param id 投诉反馈主键
     * @return 投诉反馈
     */
    JsZyfwComplaints selectJsZyfwComplaintsById(Long id);

    /**
     * 查询投诉反馈列表
     *
     * @param jsZyfwComplaints 投诉反馈
     * @return 投诉反馈集合
     */
    List<JsZyfwComplaints> selectJsZyfwComplaintsList(JsZyfwComplaints jsZyfwComplaints);

    /**
     * 新增投诉反馈
     *
     * @param jsZyfwComplaints 投诉反馈
     * @return 结果
     */
    int insertJsZyfwComplaints(JsZyfwComplaints jsZyfwComplaints);

    /**
     * 修改投诉反馈
     *
     * @param jsZyfwComplaints 投诉反馈
     * @return 结果
     */
    int updateJsZyfwComplaints(JsZyfwComplaints jsZyfwComplaints);

    /**
     * 删除投诉反馈
     *
     * @param id 投诉反馈主键
     * @return 结果
     */
    int deleteJsZyfwComplaintsById(Long id);

    /**
     * 批量删除投诉反馈
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteJsZyfwComplaintsByIds(Long[] ids);
}
