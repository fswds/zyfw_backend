package com.tiger.system.service;

import java.util.List;
import com.tiger.system.domain.JsZyfwMeetingRecords;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 会议记录Service接口
 * 
 * @author wds
 * @date 2025-04-28
 */
public interface IJsZyfwMeetingRecordsService extends IService<JsZyfwMeetingRecords> {
    /**
     * 查询会议记录
     * 
     * @param id 会议记录主键
     * @return 会议记录
     */
    JsZyfwMeetingRecords selectJsZyfwMeetingRecordsById(Long id);

    /**
     * 查询会议记录列表
     * 
     * @param jsZyfwMeetingRecords 会议记录
     * @return 会议记录集合
     */
    List<JsZyfwMeetingRecords> selectJsZyfwMeetingRecordsList(JsZyfwMeetingRecords jsZyfwMeetingRecords);

    /**
     * 新增会议记录
     * 
     * @param jsZyfwMeetingRecords 会议记录
     * @return 结果
     */
    int insertJsZyfwMeetingRecords(JsZyfwMeetingRecords jsZyfwMeetingRecords);

    /**
     * 修改会议记录
     * 
     * @param jsZyfwMeetingRecords 会议记录
     * @return 结果
     */
    int updateJsZyfwMeetingRecords(JsZyfwMeetingRecords jsZyfwMeetingRecords);

    /**
     * 批量删除会议记录
     * 
     * @param ids 需要删除的会议记录主键集合
     * @return 结果
     */
    int deleteJsZyfwMeetingRecordsByIds(Long[] ids);

    /**
     * 删除会议记录信息
     * 
     * @param id 会议记录主键
     * @return 结果
     */
    int deleteJsZyfwMeetingRecordsById(Long id);
}
