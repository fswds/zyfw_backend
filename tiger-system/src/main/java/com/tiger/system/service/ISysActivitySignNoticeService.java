package com.tiger.system.service;

import java.util.List;
import com.tiger.system.domain.SysActivitySignNotice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 签到通知公告Service接口
 * 
 * @author tiger
 * @date 2024-04-30
 */
public interface ISysActivitySignNoticeService extends IService<SysActivitySignNotice> {
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
     * 批量删除签到通知公告
     * 
     * @param noticeIds 需要删除的签到通知公告主键集合
     * @return 结果
     */
    int deleteSysActivitySignNoticeByNoticeIds(Integer[] noticeIds);

    /**
     * 删除签到通知公告信息
     * 
     * @param noticeId 签到通知公告主键
     * @return 结果
     */
    int deleteSysActivitySignNoticeByNoticeId(Integer noticeId);
}
