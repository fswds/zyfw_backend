package com.tiger.system.controller;

import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tiger.common.annotation.Log;
import com.tiger.common.core.controller.BaseController;
import com.tiger.common.core.domain.AjaxResult;
import com.tiger.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.tiger.system.domain.JsZyfwElderlyInstitutions;
import com.tiger.system.service.IJsZyfwElderlyInstitutionsService;
import com.tiger.common.utils.poi.ExcelUtil;
import com.tiger.common.core.page.TableDataInfo;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 养老机构Controller
 * 
 * @author wds
 * @date 2025-04-28
 */
@RestController
@RequestMapping("/system/institutions")
@Api(tags = " jsZyfwElderlyInstitutions管理")
public class JsZyfwElderlyInstitutionsController extends BaseController
{
    @Autowired
    private IJsZyfwElderlyInstitutionsService jsZyfwElderlyInstitutionsService;

    /**
     * 查询养老机构列表
     */
    @PreAuthorize("@ss.hasPermi('system:institutions:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询养老机构列表",notes = "养老机构列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "name", value = "机构名", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "location", value = "地点", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "buildingInfo", value = "建筑信息", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "description", value = "介绍", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "serviceType", value = "机构类型", dataType = "String", dataTypeClass = String.class),
    })

    public TableDataInfo list(JsZyfwElderlyInstitutions jsZyfwElderlyInstitutions)
    {
        startPage();
        List<JsZyfwElderlyInstitutions> list = jsZyfwElderlyInstitutionsService.selectJsZyfwElderlyInstitutionsList(jsZyfwElderlyInstitutions);
        return getDataTable(list);
    }

    /**
     * 导出养老机构列表
     */
    @PreAuthorize("@ss.hasPermi('system:institutions:export')")
    @Log(title = "养老机构", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出养老机构列表",notes = "导出养老机构列表")
    public void export(HttpServletResponse response, JsZyfwElderlyInstitutions jsZyfwElderlyInstitutions)
    {
        List<JsZyfwElderlyInstitutions> list = jsZyfwElderlyInstitutionsService.selectJsZyfwElderlyInstitutionsList(jsZyfwElderlyInstitutions);
        ExcelUtil<JsZyfwElderlyInstitutions> util = new ExcelUtil<JsZyfwElderlyInstitutions>(JsZyfwElderlyInstitutions.class);
        util.exportExcel(response, list, "养老机构数据");
    }

    /**
     * 获取养老机构详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:institutions:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取养老机构详细信息",notes = "获取养老机构详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "$column.javaField", value = "$column.columnComment", dataType = "id", dataTypeClass = Long.class),
    })
    public AjaxResult getInfo(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id)
    {
        return success(jsZyfwElderlyInstitutionsService.selectJsZyfwElderlyInstitutionsById(id));
    }

    /**
     * 新增养老机构
     */
    @PreAuthorize("@ss.hasPermi('system:institutions:add')")
    @Log(title = "养老机构", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增养老机构",notes = "新增养老机构")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "name", value = "机构名", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "location", value = "地点", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "buildingInfo", value = "建筑信息", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "description", value = "介绍", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "serviceType", value = "机构类型", dataType = "String", dataTypeClass = String.class),
    })
    public AjaxResult add(@Valid @RequestBody JsZyfwElderlyInstitutions jsZyfwElderlyInstitutions)
    {
        return toAjax(jsZyfwElderlyInstitutionsService.insertJsZyfwElderlyInstitutions(jsZyfwElderlyInstitutions));
    }

    /**
     * 修改养老机构
     */
    @PreAuthorize("@ss.hasPermi('system:institutions:edit')")
    @Log(title = "养老机构", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改养老机构",notes = "修改养老机构")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "name", value = "机构名", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "location", value = "地点", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "buildingInfo", value = "建筑信息", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "description", value = "介绍", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "serviceType", value = "机构类型", dataType = "String", dataTypeClass = String.class),
    })
    public AjaxResult edit(@Valid @RequestBody JsZyfwElderlyInstitutions jsZyfwElderlyInstitutions)
    {
        return toAjax(jsZyfwElderlyInstitutionsService.updateJsZyfwElderlyInstitutions(jsZyfwElderlyInstitutions));
    }

    /**
     * 删除养老机构
     */
    @PreAuthorize("@ss.hasPermi('system:institutions:remove')")
    @Log(title = "养老机构", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除养老机构",notes = "删除养老机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", dataType = "ids", dataTypeClass = Long.class),
    })
    public AjaxResult remove(@Valid @NotEmpty(message = "ids字段不能为空") @PathVariable Long[] ids)
    {
        return toAjax(jsZyfwElderlyInstitutionsService.deleteJsZyfwElderlyInstitutionsByIds(ids));
    }
}
