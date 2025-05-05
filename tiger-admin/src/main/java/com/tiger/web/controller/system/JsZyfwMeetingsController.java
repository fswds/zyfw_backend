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
import com.tiger.system.domain.JsZyfwMeetings;
import com.tiger.system.service.IJsZyfwMeetingsService;
import com.tiger.common.utils.poi.ExcelUtil;
import com.tiger.common.core.page.TableDataInfo;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 会议Controller
 * 
 * @author wds
 * @date 2025-04-28
 */
@RestController
@RequestMapping("/system/meetings")
@Api(tags = " jsZyfwMeetings管理")
public class JsZyfwMeetingsController extends BaseController
{
    @Autowired
    private IJsZyfwMeetingsService jsZyfwMeetingsService;

    /**
     * 查询会议列表
     */
    @PreAuthorize("@ss.hasPermi('system:meetings:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询会议列表",notes = "会议列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "name", value = "会议名", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "address", value = "地址", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "organizationId", value = "所属组织", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "leaderName", value = "主持人姓名", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "leaderNumber", value = "参会人数", dataType = "String", dataTypeClass = String.class),
    })

    public TableDataInfo list(JsZyfwMeetings jsZyfwMeetings)
    {
        startPage();
        List<JsZyfwMeetings> list = jsZyfwMeetingsService.selectJsZyfwMeetingsList(jsZyfwMeetings);
        return getDataTable(list);
    }

    /**
     * 导出会议列表
     */
    @PreAuthorize("@ss.hasPermi('system:meetings:export')")
    @Log(title = "会议", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出会议列表",notes = "导出会议列表")
    public void export(HttpServletResponse response, JsZyfwMeetings jsZyfwMeetings)
    {
        List<JsZyfwMeetings> list = jsZyfwMeetingsService.selectJsZyfwMeetingsList(jsZyfwMeetings);
        ExcelUtil<JsZyfwMeetings> util = new ExcelUtil<JsZyfwMeetings>(JsZyfwMeetings.class);
        util.exportExcel(response, list, "会议数据");
    }

    /**
     * 获取会议详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:meetings:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取会议详细信息",notes = "获取会议详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "$column.javaField", value = "$column.columnComment", dataType = "id", dataTypeClass = Long.class),
    })
    public AjaxResult getInfo(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id)
    {
        return success(jsZyfwMeetingsService.selectJsZyfwMeetingsById(id));
    }

    /**
     * 新增会议
     */
    @PreAuthorize("@ss.hasPermi('system:meetings:add')")
    @Log(title = "会议", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增会议",notes = "新增会议")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "name", value = "会议名", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "address", value = "地址", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "organizationId", value = "所属组织", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "leaderName", value = "主持人姓名", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "leaderNumber", value = "参会人数", dataType = "String", dataTypeClass = String.class),
    })
    public AjaxResult add(@Valid @RequestBody JsZyfwMeetings jsZyfwMeetings)
    {
        return toAjax(jsZyfwMeetingsService.insertJsZyfwMeetings(jsZyfwMeetings));
    }

    /**
     * 修改会议
     */
    @PreAuthorize("@ss.hasPermi('system:meetings:edit')")
    @Log(title = "会议", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改会议",notes = "修改会议")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "name", value = "会议名", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "address", value = "地址", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "organizationId", value = "所属组织", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "leaderName", value = "主持人姓名", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "leaderNumber", value = "参会人数", dataType = "String", dataTypeClass = String.class),
    })
    public AjaxResult edit(@Valid @RequestBody JsZyfwMeetings jsZyfwMeetings)
    {
        return toAjax(jsZyfwMeetingsService.updateJsZyfwMeetings(jsZyfwMeetings));
    }

    /**
     * 删除会议
     */
    @PreAuthorize("@ss.hasPermi('system:meetings:remove')")
    @Log(title = "会议", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除会议",notes = "删除会议")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", dataType = "ids", dataTypeClass = Long.class),
    })
    public AjaxResult remove(@Valid @NotEmpty(message = "ids字段不能为空") @PathVariable Long[] ids)
    {
        return toAjax(jsZyfwMeetingsService.deleteJsZyfwMeetingsByIds(ids));
    }
}
