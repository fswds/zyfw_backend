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
import com.tiger.system.domain.JsZyfwVolunteerServiceBuildings;
import com.tiger.system.service.IJsZyfwVolunteerServiceBuildingsService;
import com.tiger.common.utils.poi.ExcelUtil;
import com.tiger.common.core.page.TableDataInfo;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 建筑管理Controller
 * 
 * @author wds
 * @date 2025-04-28
 */
@RestController
@RequestMapping("/system/buildings")
@Api(tags = " jsZyfwVolunteerServiceBuildings管理")
public class JsZyfwVolunteerServiceBuildingsController extends BaseController
{
    @Autowired
    private IJsZyfwVolunteerServiceBuildingsService jsZyfwVolunteerServiceBuildingsService;

    /**
     * 查询建筑管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:buildings:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询建筑管理列表",notes = "建筑管理列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "name", value = "建筑名", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "address", value = "地址", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "isAvailable", value = "是否空闲", dataType = "Long", dataTypeClass = Long.class),
    })

    public TableDataInfo list(JsZyfwVolunteerServiceBuildings jsZyfwVolunteerServiceBuildings)
    {
        startPage();
        List<JsZyfwVolunteerServiceBuildings> list = jsZyfwVolunteerServiceBuildingsService.selectJsZyfwVolunteerServiceBuildingsList(jsZyfwVolunteerServiceBuildings);
        return getDataTable(list);
    }

    /**
     * 导出建筑管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:buildings:export')")
    @Log(title = "建筑管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出建筑管理列表",notes = "导出建筑管理列表")
    public void export(HttpServletResponse response, JsZyfwVolunteerServiceBuildings jsZyfwVolunteerServiceBuildings)
    {
        List<JsZyfwVolunteerServiceBuildings> list = jsZyfwVolunteerServiceBuildingsService.selectJsZyfwVolunteerServiceBuildingsList(jsZyfwVolunteerServiceBuildings);
        ExcelUtil<JsZyfwVolunteerServiceBuildings> util = new ExcelUtil<JsZyfwVolunteerServiceBuildings>(JsZyfwVolunteerServiceBuildings.class);
        util.exportExcel(response, list, "建筑管理数据");
    }

    /**
     * 获取建筑管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:buildings:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取建筑管理详细信息",notes = "获取建筑管理详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "$column.javaField", value = "$column.columnComment", dataType = "id", dataTypeClass = Long.class),
    })
    public AjaxResult getInfo(@Valid  @NotNull(message = "id不能为空") @PathVariable("id") Long id)
    {
        return success(jsZyfwVolunteerServiceBuildingsService.selectJsZyfwVolunteerServiceBuildingsById(id));
    }

    /**
     * 新增建筑管理
     */
    @PreAuthorize("@ss.hasPermi('system:buildings:add')")
    @Log(title = "建筑管理", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增建筑管理",notes = "新增建筑管理")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "主键", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "name", value = "建筑名", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "address", value = "地址", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "isAvailable", value = "是否空闲", dataType = "Long", dataTypeClass = Long.class),
    })
    public AjaxResult add(@Valid @RequestBody JsZyfwVolunteerServiceBuildings jsZyfwVolunteerServiceBuildings)
    {
        return toAjax(jsZyfwVolunteerServiceBuildingsService.insertJsZyfwVolunteerServiceBuildings(jsZyfwVolunteerServiceBuildings));
    }

    /**
     * 修改建筑管理
     */
    @PreAuthorize("@ss.hasPermi('system:buildings:edit')")
    @Log(title = "建筑管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改建筑管理",notes = "修改建筑管理")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "主键", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "name", value = "建筑名", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "address", value = "地址", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "isAvailable", value = "是否空闲", dataType = "Long", dataTypeClass = Long.class),
    })
    public AjaxResult edit(@Valid @RequestBody JsZyfwVolunteerServiceBuildings jsZyfwVolunteerServiceBuildings)
    {
        return toAjax(jsZyfwVolunteerServiceBuildingsService.updateJsZyfwVolunteerServiceBuildings(jsZyfwVolunteerServiceBuildings));
    }

    /**
     * 删除建筑管理
     */
    @PreAuthorize("@ss.hasPermi('system:buildings:remove')")
    @Log(title = "建筑管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除建筑管理",notes = "删除建筑管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", dataType = "ids", dataTypeClass = Long.class),
    })
    public AjaxResult remove(@Valid @NotEmpty(message = "ids字段不能为空") @PathVariable Long[] ids)
    {
        return toAjax(jsZyfwVolunteerServiceBuildingsService.deleteJsZyfwVolunteerServiceBuildingsByIds(ids));
    }
}
