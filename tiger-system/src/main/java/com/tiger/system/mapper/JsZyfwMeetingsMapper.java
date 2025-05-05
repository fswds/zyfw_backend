package com.tiger.system.mapper;

import java.util.List;
import com.tiger.system.domain.JsZyfwMeetings;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 会议Mapper接口
 * 
 * @author wds
 * @date 2025-04-28
 */
public interface JsZyfwMeetingsMapper extends BaseMapper<JsZyfwMeetings> {
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
     * 删除会议
     *
     * @param id 会议主键
     * @return 结果
     */
    int deleteJsZyfwMeetingsById(Long id);

    /**
     * 批量删除会议
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteJsZyfwMeetingsByIds(Long[] ids);
}
