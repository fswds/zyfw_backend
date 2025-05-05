package com.tiger.web.controller.system;

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
import com.tiger.system.domain.JsZyfwElderlyCommunity;
import com.tiger.system.service.IJsZyfwElderlyCommunityService;
import com.tiger.common.utils.poi.ExcelUtil;
import com.tiger.common.core.page.TableDataInfo;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 老人信息Controller
 * 
 * @author wds
 * @date 2025-04-28
 */
@RestController
@RequestMapping("/system/community")
@Api(tags = " jsZyfwElderlyCommunity管理")
public class JsZyfwElderlyCommunityController extends BaseController
{
    @Autowired
    private IJsZyfwElderlyCommunityService jsZyfwElderlyCommunityService;

    /**
     * 查询老人信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:community:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询老人信息列表",notes = "老人信息列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "$column.columnComment", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "name", value = "姓名", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "age", value = "年龄", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "gender", value = "性别", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "birthday", value = "生日", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "healthStatus", value = "健康状况", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "medicationHistory", value = "用药史", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "communicationExperience", value = "沟通经历", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "familyStatus", value = "家属状况", dataType = "String", dataTypeClass = String.class),
    })

    public TableDataInfo list(JsZyfwElderlyCommunity jsZyfwElderlyCommunity)
    {
        startPage();
        List<JsZyfwElderlyCommunity> list = jsZyfwElderlyCommunityService.selectJsZyfwElderlyCommunityList(jsZyfwElderlyCommunity);
        return getDataTable(list);
    }

    /**
     * 导出老人信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:community:export')")
    @Log(title = "老人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出老人信息列表",notes = "导出老人信息列表")
    public void export(HttpServletResponse response, JsZyfwElderlyCommunity jsZyfwElderlyCommunity)
    {
        List<JsZyfwElderlyCommunity> list = jsZyfwElderlyCommunityService.selectJsZyfwElderlyCommunityList(jsZyfwElderlyCommunity);
        ExcelUtil<JsZyfwElderlyCommunity> util = new ExcelUtil<JsZyfwElderlyCommunity>(JsZyfwElderlyCommunity.class);
        util.exportExcel(response, list, "老人信息数据");
    }

    /**
     * 获取老人信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:community:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取老人信息详细信息",notes = "获取老人信息详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "$column.javaField", value = "$column.columnComment", dataType = "id", dataTypeClass = Long.class),
    })
    public AjaxResult getInfo(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id)
    {
        return success(jsZyfwElderlyCommunityService.selectJsZyfwElderlyCommunityById(id));
    }

    /**
     * 新增老人信息
     */
    @PreAuthorize("@ss.hasPermi('system:community:add')")
    @Log(title = "老人信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增老人信息",notes = "新增老人信息")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "$column.columnComment", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "name", value = "姓名", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "age", value = "年龄", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "gender", value = "性别", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "birthday", value = "生日", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "healthStatus", value = "健康状况", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "medicationHistory", value = "用药史", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "communicationExperience", value = "沟通经历", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "familyStatus", value = "家属状况", dataType = "String", dataTypeClass = String.class),
    })
    public AjaxResult add(@Valid @RequestBody JsZyfwElderlyCommunity jsZyfwElderlyCommunity)
    {
        return toAjax(jsZyfwElderlyCommunityService.insertJsZyfwElderlyCommunity(jsZyfwElderlyCommunity));
    }

    /**
     * 修改老人信息
     */
    @PreAuthorize("@ss.hasPermi('system:community:edit')")
    @Log(title = "老人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改老人信息",notes = "修改老人信息")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "$column.columnComment", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "name", value = "姓名", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "age", value = "年龄", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "gender", value = "性别", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "birthday", value = "生日", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "healthStatus", value = "健康状况", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "medicationHistory", value = "用药史", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "communicationExperience", value = "沟通经历", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "familyStatus", value = "家属状况", dataType = "String", dataTypeClass = String.class),
    })
    public AjaxResult edit(@Valid @RequestBody JsZyfwElderlyCommunity jsZyfwElderlyCommunity)
    {
        return toAjax(jsZyfwElderlyCommunityService.updateJsZyfwElderlyCommunity(jsZyfwElderlyCommunity));
    }

    /**
     * 删除老人信息
     */
    @PreAuthorize("@ss.hasPermi('system:community:remove')")
    @Log(title = "老人信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除老人信息",notes = "删除老人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", dataType = "ids", dataTypeClass = Long.class),
    })
    public AjaxResult remove(@Valid @NotEmpty(message = "ids字段不能为空") @PathVariable Long[] ids)
    {
        return toAjax(jsZyfwElderlyCommunityService.deleteJsZyfwElderlyCommunityByIds(ids));
    }
}
