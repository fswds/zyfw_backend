package com.tiger.system.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiger.system.mapper.JsZyfwMeetingsMapper;
import com.tiger.system.domain.JsZyfwMeetings;
import com.tiger.system.service.IJsZyfwMeetingsService;
/**
 * 会议Service业务层处理
 * 
 * @author wds
 * @date 2025-04-28
 */
@Service
public class JsZyfwMeetingsServiceImpl extends ServiceImpl<JsZyfwMeetingsMapper, JsZyfwMeetings> implements IJsZyfwMeetingsService
{
    @Autowired
    private JsZyfwMeetingsMapper jsZyfwMeetingsMapper;

    /**
     * 查询会议
     * 
     * @param id 会议主键
     * @return 会议
     */
    @Override
    public JsZyfwMeetings selectJsZyfwMeetingsById(Long id)
    {
        return jsZyfwMeetingsMapper.selectJsZyfwMeetingsById(id);
    }

    /**
     * 查询会议列表
     * 
     * @param jsZyfwMeetings 会议
     * @return 会议
     */
    @Override
    public List<JsZyfwMeetings> selectJsZyfwMeetingsList(JsZyfwMeetings jsZyfwMeetings)
    {
        return jsZyfwMeetingsMapper.selectJsZyfwMeetingsList(jsZyfwMeetings);
    }

    /**
     * 新增会议
     * 
     * @param jsZyfwMeetings 会议
     * @return 结果
     */
    @Override
    public int insertJsZyfwMeetings(JsZyfwMeetings jsZyfwMeetings)
    {
        return jsZyfwMeetingsMapper.insertJsZyfwMeetings(jsZyfwMeetings);
    }

    /**
     * 修改会议
     * 
     * @param jsZyfwMeetings 会议
     * @return 结果
     */
    @Override
    public int updateJsZyfwMeetings(JsZyfwMeetings jsZyfwMeetings)
    {
        return jsZyfwMeetingsMapper.updateJsZyfwMeetings(jsZyfwMeetings);
    }

    /**
     * 批量删除会议
     * 
     * @param ids 需要删除的会议主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwMeetingsByIds(Long[] ids)
    {
        return jsZyfwMeetingsMapper.deleteJsZyfwMeetingsByIds(ids);
    }

    /**
     * 删除会议信息
     * 
     * @param id 会议主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwMeetingsById(Long id)
    {
        return jsZyfwMeetingsMapper.deleteJsZyfwMeetingsById(id);
    }
}
