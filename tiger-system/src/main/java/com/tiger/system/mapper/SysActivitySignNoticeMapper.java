package com.tiger.system.mapper;

import java.util.List;
import com.tiger.system.domain.SysActivitySignNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 签到通知公告Mapper接口
 * 
 * @author tiger
 * @date 2024-04-30
 */
public interface SysActivitySignNoticeMapper extends BaseMapper<SysActivitySignNotice> {
    /**
     * 查询签到通知公告
     *
     * @param noticeId 签到通知公告主键
     * @return 签到通知公告
     */
    SysActivitySignNotice selectSysActivitySignNoticeByNoticeId(Integer noticeId);

    /**
     * 查询签到通知公告列表
     *
     * @param sysActivitySignNotice 签到通知公告
     * @return 签到通知公告集合
     */
    List<SysActivitySignNotice> selectSysActivitySignNoticeList(SysActivitySignNotice sysActivitySignNotice);

    /**
     * 新增签到通知公告
     *
     * @param sysActivitySignNotice 签到通知公告
     * @return 结果
     */
    int insertSysActivitySignNotice(SysActivitySignNotice sysActivitySignNotice);

    /**
     * 修改签到通知公告
     *
     * @param sysActivitySignNotice 签到通知公告
     * @return 结果
     */
    int updateSysActivitySignNotice(SysActivitySignNotice sysActivitySignNotice);

    /**
     * 删除签到通知公告
     *
     * @param noticeId 签到通知公告主键
     * @return 结果
     */
    int deleteSysActivitySignNoticeByNoticeId(Integer noticeId);

    /**
     * 批量删除签到通知公告
     *
     * @param noticeIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysActivitySignNoticeByNoticeIds(Integer[] noticeIds);
}
