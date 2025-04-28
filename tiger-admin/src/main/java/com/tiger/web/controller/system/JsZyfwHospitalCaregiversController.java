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
import com.tiger.system.domain.JsZyfwHospitalCaregivers;
import com.tiger.system.service.IJsZyfwHospitalCaregiversService;
import com.tiger.common.utils.poi.ExcelUtil;
import com.tiger.common.core.page.TableDataInfo;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 护理人员信息管理Controller
 * 
 * @author wds
 * @date 2025-04-28
 */
@RestController
@RequestMapping("/system/caregivers")
@Api(tags = " jsZyfwHospitalCaregivers管理")
public class JsZyfwHospitalCaregiversController extends BaseController
{
    @Autowired
    private IJsZyfwHospitalCaregiversService jsZyfwHospitalCaregiversService;

    /**
     * 查询护理人员信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:caregivers:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询护理人员信息管理列表",notes = "护理人员信息管理列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gender", value = "性别", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "id", value = "$column.columnComment", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "age", value = "年龄", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "contactInfo", value = "个人信息", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "institution", value = "所属机构", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "workExperience", value = "工作经历", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "address", value = "家庭住址", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "phone", value = "联系方式", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "name", value = "姓名", dataType = "String", dataTypeClass = String.class),
    })

    public TableDataInfo list(JsZyfwHospitalCaregivers jsZyfwHospitalCaregivers)
    {
        startPage();
        List<JsZyfwHospitalCaregivers> list = jsZyfwHospitalCaregiversService.selectJsZyfwHospitalCaregiversList(jsZyfwHospitalCaregivers);
        return getDataTable(list);
    }

    /**
     * 导出护理人员信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:caregivers:export')")
    @Log(title = "护理人员信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出护理人员信息管理列表",notes = "导出护理人员信息管理列表")
    public void export(HttpServletResponse response, JsZyfwHospitalCaregivers jsZyfwHospitalCaregivers)
    {
        List<JsZyfwHospitalCaregivers> list = jsZyfwHospitalCaregiversService.selectJsZyfwHospitalCaregiversList(jsZyfwHospitalCaregivers);
        ExcelUtil<JsZyfwHospitalCaregivers> util = new ExcelUtil<JsZyfwHospitalCaregivers>(JsZyfwHospitalCaregivers.class);
        util.exportExcel(response, list, "护理人员信息管理数据");
    }

    /**
     * 获取护理人员信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:caregivers:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取护理人员信息管理详细信息",notes = "获取护理人员信息管理详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "$column.javaField", value = "$column.columnComment", dataType = "id", dataTypeClass = Long.class),
    })
    public AjaxResult getInfo(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id)
    {
        return success(jsZyfwHospitalCaregiversService.selectJsZyfwHospitalCaregiversById(id));
    }

    /**
     * 新增护理人员信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:caregivers:add')")
    @Log(title = "护理人员信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增护理人员信息管理",notes = "新增护理人员信息管理")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "gender", value = "性别", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "id", value = "$column.columnComment", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "age", value = "年龄", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "contactInfo", value = "个人信息", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "institution", value = "所属机构", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "workExperience", value = "工作经历", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "address", value = "家庭住址", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "phone", value = "联系方式", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "name", value = "姓名", dataType = "String", dataTypeClass = String.class),
    })
    public AjaxResult add(@Valid @RequestBody JsZyfwHospitalCaregivers jsZyfwHospitalCaregivers)
    {
        return toAjax(jsZyfwHospitalCaregiversService.insertJsZyfwHospitalCaregivers(jsZyfwHospitalCaregivers));
    }

    /**
     * 修改护理人员信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:caregivers:edit')")
    @Log(title = "护理人员信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改护理人员信息管理",notes = "修改护理人员信息管理")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "gender", value = "性别", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "id", value = "$column.columnComment", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "age", value = "年龄", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "contactInfo", value = "个人信息", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "institution", value = "所属机构", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "workExperience", value = "工作经历", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "address", value = "家庭住址", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "phone", value = "联系方式", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "name", value = "姓名", dataType = "String", dataTypeClass = String.class),
    })
    public AjaxResult edit(@Valid @RequestBody JsZyfwHospitalCaregivers jsZyfwHospitalCaregivers)
    {
        return toAjax(jsZyfwHospitalCaregiversService.updateJsZyfwHospitalCaregivers(jsZyfwHospitalCaregivers));
    }

    /**
     * 删除护理人员信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:caregivers:remove')")
    @Log(title = "护理人员信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除护理人员信息管理",notes = "删除护理人员信息管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", dataType = "ids", dataTypeClass = Long.class),
    })
    public AjaxResult remove(@Valid @NotEmpty(message = "ids字段不能为空") @PathVariable Long[] ids)
    {
        return toAjax(jsZyfwHospitalCaregiversService.deleteJsZyfwHospitalCaregiversByIds(ids));
    }
}
