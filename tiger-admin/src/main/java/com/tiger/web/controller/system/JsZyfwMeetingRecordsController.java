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
import com.tiger.system.domain.JsZyfwMeetingRecords;
import com.tiger.system.service.IJsZyfwMeetingRecordsService;
import com.tiger.common.utils.poi.ExcelUtil;
import com.tiger.common.core.page.TableDataInfo;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 会议记录Controller
 * 
 * @author wds
 * @date 2025-04-28
 */
@RestController
@RequestMapping("/system/records")
@Api(tags = " jsZyfwMeetingRecords管理")
public class JsZyfwMeetingRecordsController extends BaseController
{
    @Autowired
    private IJsZyfwMeetingRecordsService jsZyfwMeetingRecordsService;

    /**
     * 查询会议记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:records:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询会议记录列表",notes = "会议记录列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "meetingId", value = "所属会议", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "content", value = "会议内容", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "result", value = "会议结果", dataType = "String", dataTypeClass = String.class),
    })

    public TableDataInfo list(JsZyfwMeetingRecords jsZyfwMeetingRecords)
    {
        startPage();
        List<JsZyfwMeetingRecords> list = jsZyfwMeetingRecordsService.selectJsZyfwMeetingRecordsList(jsZyfwMeetingRecords);
        return getDataTable(list);
    }

    /**
     * 导出会议记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:records:export')")
    @Log(title = "会议记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出会议记录列表",notes = "导出会议记录列表")
    public void export(HttpServletResponse response, JsZyfwMeetingRecords jsZyfwMeetingRecords)
    {
        List<JsZyfwMeetingRecords> list = jsZyfwMeetingRecordsService.selectJsZyfwMeetingRecordsList(jsZyfwMeetingRecords);
        ExcelUtil<JsZyfwMeetingRecords> util = new ExcelUtil<JsZyfwMeetingRecords>(JsZyfwMeetingRecords.class);
        util.exportExcel(response, list, "会议记录数据");
    }

    /**
     * 获取会议记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:records:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取会议记录详细信息",notes = "获取会议记录详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "$column.javaField", value = "$column.columnComment", dataType = "id", dataTypeClass = Long.class),
    })
    public AjaxResult getInfo(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id)
    {
        return success(jsZyfwMeetingRecordsService.selectJsZyfwMeetingRecordsById(id));
    }

    /**
     * 新增会议记录
     */
    @PreAuthorize("@ss.hasPermi('system:records:add')")
    @Log(title = "会议记录", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增会议记录",notes = "新增会议记录")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "meetingId", value = "所属会议", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "content", value = "会议内容", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "result", value = "会议结果", dataType = "String", dataTypeClass = String.class),
    })
    public AjaxResult add(@Valid @RequestBody JsZyfwMeetingRecords jsZyfwMeetingRecords)
    {
        return toAjax(jsZyfwMeetingRecordsService.insertJsZyfwMeetingRecords(jsZyfwMeetingRecords));
    }

    /**
     * 修改会议记录
     */
    @PreAuthorize("@ss.hasPermi('system:records:edit')")
    @Log(title = "会议记录", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改会议记录",notes = "修改会议记录")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "meetingId", value = "所属会议", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "content", value = "会议内容", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "result", value = "会议结果", dataType = "String", dataTypeClass = String.class),
    })
    public AjaxResult edit(@Valid @RequestBody JsZyfwMeetingRecords jsZyfwMeetingRecords)
    {
        return toAjax(jsZyfwMeetingRecordsService.updateJsZyfwMeetingRecords(jsZyfwMeetingRecords));
    }

    /**
     * 删除会议记录
     */
    @PreAuthorize("@ss.hasPermi('system:records:remove')")
    @Log(title = "会议记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除会议记录",notes = "删除会议记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", dataType = "ids", dataTypeClass = Long.class),
    })
    public AjaxResult remove(@Valid @NotEmpty(message = "ids字段不能为空") @PathVariable Long[] ids)
    {
        return toAjax(jsZyfwMeetingRecordsService.deleteJsZyfwMeetingRecordsByIds(ids));
    }
}
