package com.tiger.system.mapper;

import java.util.List;
import com.tiger.system.domain.SysSignRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 签到记录Mapper接口
 * 
 * @author tiger
 * @date 2024-04-28
 */
public interface SysSignRecordMapper extends BaseMapper<SysSignRecord> {
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
    int updateSysSignRecord(SysSignRecord sysSignRecord);

    /**
     * 删除签到记录
     *
     * @param id 签到记录主键
     * @return 结果
     */
    int deleteSysSignRecordById(Long id);

    /**
     * 批量删除签到记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysSignRecordByIds(Long[] ids);
}
