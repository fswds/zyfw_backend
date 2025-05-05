package com.tiger.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiger.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiger.system.mapper.SysActivitySignNoticeMapper;
import com.tiger.system.domain.SysActivitySignNotice;
import com.tiger.system.service.ISysActivitySignNoticeService;
/**
 * 签到通知公告Service业务层处理
 * 
 * @author tiger
 * @date 2024-04-30
 */
@Service
public class SysActivitySignNoticeServiceImpl extends ServiceImpl<SysActivitySignNoticeMapper, SysActivitySignNotice> implements ISysActivitySignNoticeService
{
    @Autowired
    private SysActivitySignNoticeMapper sysActivitySignNoticeMapper;

    /**
     * 查询签到通知公告
     * 
     * @param noticeId 签到通知公告主键
     * @return 签到通知公告
     */
    @Override
    public SysActivitySignNotice selectSysActivitySignNoticeByNoticeId(Integer noticeId)
    {
        return sysActivitySignNoticeMapper.selectSysActivitySignNoticeByNoticeId(noticeId);
    }

    /**
     * 查询签到通知公告列表
     * 
     * @param sysActivitySignNotice 签到通知公告
     * @return 签到通知公告
     */
    @Override
    public List<SysActivitySignNotice> selectSysActivitySignNoticeList(SysActivitySignNotice sysActivitySignNotice)
    {
        return sysActivitySignNoticeMapper.selectSysActivitySignNoticeList(sysActivitySignNotice);
    }

    /**
     * 新增签到通知公告
     * 
     * @param sysActivitySignNotice 签到通知公告
     * @return 结果
     */
    @Override
    public int insertSysActivitySignNotice(SysActivitySignNotice sysActivitySignNotice)
    {
        sysActivitySignNotice.setCreateTime(DateUtils.getNowDate());
        return sysActivitySignNoticeMapper.insertSysActivitySignNotice(sysActivitySignNotice);
    }

    /**
     * 修改签到通知公告
     * 
     * @param sysActivitySignNotice 签到通知公告
     * @return 结果
     */
    @Override
    public int updateSysActivitySignNotice(SysActivitySignNotice sysActivitySignNotice)
    {
        sysActivitySignNotice.setUpdateTime(DateUtils.getNowDate());
        return sysActivitySignNoticeMapper.updateSysActivitySignNotice(sysActivitySignNotice);
    }

    /**
     * 批量删除签到通知公告
     * 
     * @param noticeIds 需要删除的签到通知公告主键
     * @return 结果
     */
    @Override
    public int deleteSysActivitySignNoticeByNoticeIds(Integer[] noticeIds)
    {
        return sysActivitySignNoticeMapper.deleteSysActivitySignNoticeByNoticeIds(noticeIds);
    }

    /**
     * 删除签到通知公告信息
     * 
     * @param noticeId 签到通知公告主键
     * @return 结果
     */
    @Override
    public int deleteSysActivitySignNoticeByNoticeId(Integer noticeId)
    {
        return sysActivitySignNoticeMapper.deleteSysActivitySignNoticeByNoticeId(noticeId);
    }
}
