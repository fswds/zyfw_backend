package com.tiger.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiger.system.mapper.JsZyfwMeetingRecordsMapper;
import com.tiger.system.domain.JsZyfwMeetingRecords;
import com.tiger.system.service.IJsZyfwMeetingRecordsService;
/**
 * 会议记录Service业务层处理
 * 
 * @author wds
 * @date 2025-04-28
 */
@Service
public class JsZyfwMeetingRecordsServiceImpl extends ServiceImpl<JsZyfwMeetingRecordsMapper, JsZyfwMeetingRecords> implements IJsZyfwMeetingRecordsService
{
    @Autowired
    private JsZyfwMeetingRecordsMapper jsZyfwMeetingRecordsMapper;

    /**
     * 查询会议记录
     * 
     * @param id 会议记录主键
     * @return 会议记录
     */
    @Override
    public JsZyfwMeetingRecords selectJsZyfwMeetingRecordsById(Long id)
    {
        return jsZyfwMeetingRecordsMapper.selectJsZyfwMeetingRecordsById(id);
    }

    /**
     * 查询会议记录列表
     * 
     * @param jsZyfwMeetingRecords 会议记录
     * @return 会议记录
     */
    @Override
    public List<JsZyfwMeetingRecords> selectJsZyfwMeetingRecordsList(JsZyfwMeetingRecords jsZyfwMeetingRecords)
    {
        return jsZyfwMeetingRecordsMapper.selectJsZyfwMeetingRecordsList(jsZyfwMeetingRecords);
    }

    /**
     * 新增会议记录
     * 
     * @param jsZyfwMeetingRecords 会议记录
     * @return 结果
     */
    @Override
    public int insertJsZyfwMeetingRecords(JsZyfwMeetingRecords jsZyfwMeetingRecords)
    {
        return jsZyfwMeetingRecordsMapper.insertJsZyfwMeetingRecords(jsZyfwMeetingRecords);
    }

    /**
     * 修改会议记录
     * 
     * @param jsZyfwMeetingRecords 会议记录
     * @return 结果
     */
    @Override
    public int updateJsZyfwMeetingRecords(JsZyfwMeetingRecords jsZyfwMeetingRecords)
    {
        return jsZyfwMeetingRecordsMapper.updateJsZyfwMeetingRecords(jsZyfwMeetingRecords);
    }

    /**
     * 批量删除会议记录
     * 
     * @param ids 需要删除的会议记录主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwMeetingRecordsByIds(Long[] ids)
    {
        return jsZyfwMeetingRecordsMapper.deleteJsZyfwMeetingRecordsByIds(ids);
    }

    /**
     * 删除会议记录信息
     * 
     * @param id 会议记录主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwMeetingRecordsById(Long id)
    {
        return jsZyfwMeetingRecordsMapper.deleteJsZyfwMeetingRecordsById(id);
    }
}
