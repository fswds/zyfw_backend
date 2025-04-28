package com.tiger.system.mapper;

import java.util.List;
import com.tiger.system.domain.JsZyfwElderlyInstitutions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 养老机构Mapper接口
 * 
 * @author wds
 * @date 2025-04-28
 */
public interface JsZyfwElderlyInstitutionsMapper extends BaseMapper<JsZyfwElderlyInstitutions> {
    /**
     * 查询养老机构
     *
     * @param id 养老机构主键
     * @return 养老机构
     */
    JsZyfwElderlyInstitutions selectJsZyfwElderlyInstitutionsById(Long id);

    /**
     * 查询养老机构列表
     *
     * @param jsZyfwElderlyInstitutions 养老机构
     * @return 养老机构集合
     */
    List<JsZyfwElderlyInstitutions> selectJsZyfwElderlyInstitutionsList(JsZyfwElderlyInstitutions jsZyfwElderlyInstitutions);

    /**
     * 新增养老机构
     *
     * @param jsZyfwElderlyInstitutions 养老机构
     * @return 结果
     */
    int insertJsZyfwElderlyInstitutions(JsZyfwElderlyInstitutions jsZyfwElderlyInstitutions);

    /**
     * 修改养老机构
     *
     * @param jsZyfwElderlyInstitutions 养老机构
     * @return 结果
     */
    int updateJsZyfwElderlyInstitutions(JsZyfwElderlyInstitutions jsZyfwElderlyInstitutions);

    /**
     * 删除养老机构
     *
     * @param id 养老机构主键
     * @return 结果
     */
    int deleteJsZyfwElderlyInstitutionsById(Long id);

    /**
     * 批量删除养老机构
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteJsZyfwElderlyInstitutionsByIds(Long[] ids);
}
