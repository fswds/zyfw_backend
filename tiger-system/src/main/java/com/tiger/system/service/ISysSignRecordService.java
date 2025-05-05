package com.tiger.system.service;

import java.util.List;
import com.tiger.system.domain.SysSignRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 签到记录Service接口
 * 
 * @author tiger
 * @date 2024-04-28
 */
public interface ISysSignRecordService extends IService<SysSignRecord> {
    /**
     * 查询签到记录
     * 
     * @param id 签到记录主键
     * @return 签到记录
     */
    SysSignRecord selectSysSignRecordById(Long id);

    /**
     * 查询签到记录列表
     * 
     * @param sysSignRecord 签到记录
     * @return 签到记录集合
     */
    List<SysSignRecord> selectSysSignRecordList(SysSignRecord sysSignRecord);

    /**
     * 新增签到记录
     * 
     * @param sysSignRecord 签到记录
     * @return 结果
     */
    int insertSysSignRecord(SysSignRecord sysSignRecord);

    /**
     * 修改签到记录
     * 
     * @param sysSignRecord 签到记录
     * @return 结果
     */
    int updateSysSignRecord(SysSignRecord sysSignRecord) throws Exception;

    /**
     * 批量删除签到记录
     * 
     * @param ids 需要删除的签到记录主键集合
     * @return 结果
     */
    int deleteSysSignRecordByIds(Long[] ids);

    /**
     * 删除签到记录信息
     * 
     * @param id 签到记录主键
     * @return 结果
     */
    int deleteSysSignRecordById(Long id);
}
