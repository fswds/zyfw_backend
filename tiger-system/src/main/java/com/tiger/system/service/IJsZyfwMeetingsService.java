package com.tiger.system.service;

import java.util.List;
import com.tiger.system.domain.JsZyfwMeetings;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 会议Service接口
 * 
 * @author wds
 * @date 2025-04-28
 */
public interface IJsZyfwMeetingsService extends IService<JsZyfwMeetings> {
    /**
     * 查询会议
     * 
     * @param id 会议主键
     * @return 会议
     */
    JsZyfwMeetings selectJsZyfwMeetingsById(Long id);

    /**
     * 查询会议列表
     * 
     * @param jsZyfwMeetings 会议
     * @return 会议集合
     */
    List<JsZyfwMeetings> selectJsZyfwMeetingsList(JsZyfwMeetings jsZyfwMeetings);

    /**
     * 新增会议
     * 
     * @param jsZyfwMeetings 会议
     * @return 结果
     */
    int insertJsZyfwMeetings(JsZyfwMeetings jsZyfwMeetings);

    /**
     * 修改会议
     * 
     * @param jsZyfwMeetings 会议
     * @return 结果
     */
    int updateJsZyfwMeetings(JsZyfwMeetings jsZyfwMeetings);

    /**
     * 批量删除会议
     * 
     * @param ids 需要删除的会议主键集合
     * @return 结果
     */
    int deleteJsZyfwMeetingsByIds(Long[] ids);

    /**
     * 删除会议信息
     * 
     * @param id 会议主键
     * @return 结果
     */
    int deleteJsZyfwMeetingsById(Long id);
}
