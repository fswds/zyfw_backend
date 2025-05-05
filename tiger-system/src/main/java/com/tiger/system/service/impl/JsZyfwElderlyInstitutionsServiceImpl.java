package com.tiger.system.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiger.system.mapper.JsZyfwElderlyInstitutionsMapper;
import com.tiger.system.domain.JsZyfwElderlyInstitutions;
import com.tiger.system.service.IJsZyfwElderlyInstitutionsService;
/**
 * 养老机构Service业务层处理
 * 
 * @author wds
 * @date 2025-04-28
 */
@Service
public class JsZyfwElderlyInstitutionsServiceImpl extends ServiceImpl<JsZyfwElderlyInstitutionsMapper, JsZyfwElderlyInstitutions> implements IJsZyfwElderlyInstitutionsService
{
    @Autowired
    private JsZyfwElderlyInstitutionsMapper jsZyfwElderlyInstitutionsMapper;

    /**
     * 查询养老机构
     * 
     * @param id 养老机构主键
     * @return 养老机构
     */
    @Override
    public JsZyfwElderlyInstitutions selectJsZyfwElderlyInstitutionsById(Long id)
    {
        return jsZyfwElderlyInstitutionsMapper.selectJsZyfwElderlyInstitutionsById(id);
    }

    /**
     * 查询养老机构列表
     * 
     * @param jsZyfwElderlyInstitutions 养老机构
     * @return 养老机构
     */
    @Override
    public List<JsZyfwElderlyInstitutions> selectJsZyfwElderlyInstitutionsList(JsZyfwElderlyInstitutions jsZyfwElderlyInstitutions)
    {
        return jsZyfwElderlyInstitutionsMapper.selectJsZyfwElderlyInstitutionsList(jsZyfwElderlyInstitutions);
    }

    /**
     * 新增养老机构
     * 
     * @param jsZyfwElderlyInstitutions 养老机构
     * @return 结果
     */
    @Override
    public int insertJsZyfwElderlyInstitutions(JsZyfwElderlyInstitutions jsZyfwElderlyInstitutions)
    {
        return jsZyfwElderlyInstitutionsMapper.insertJsZyfwElderlyInstitutions(jsZyfwElderlyInstitutions);
    }

    /**
     * 修改养老机构
     * 
     * @param jsZyfwElderlyInstitutions 养老机构
     * @return 结果
     */
    @Override
    public int updateJsZyfwElderlyInstitutions(JsZyfwElderlyInstitutions jsZyfwElderlyInstitutions)
    {
        return jsZyfwElderlyInstitutionsMapper.updateJsZyfwElderlyInstitutions(jsZyfwElderlyInstitutions);
    }

    /**
     * 批量删除养老机构
     * 
     * @param ids 需要删除的养老机构主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwElderlyInstitutionsByIds(Long[] ids)
    {
        return jsZyfwElderlyInstitutionsMapper.deleteJsZyfwElderlyInstitutionsByIds(ids);
    }

    /**
     * 删除养老机构信息
     * 
     * @param id 养老机构主键
     * @return 结果
     */
    @Override
    public int deleteJsZyfwElderlyInstitutionsById(Long id)
    {
        return jsZyfwElderlyInstitutionsMapper.deleteJsZyfwElderlyInstitutionsById(id);
    }
}
