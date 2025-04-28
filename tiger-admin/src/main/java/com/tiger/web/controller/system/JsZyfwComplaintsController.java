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
import com.tiger.system.domain.JsZyfwComplaints;
import com.tiger.system.service.IJsZyfwComplaintsService;
import com.tiger.common.utils.poi.ExcelUtil;
import com.tiger.common.core.page.TableDataInfo;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 投诉反馈Controller
 * 
 * @author tiger
 * @date 2025-04-28
 */
@RestController
@RequestMapping("/system/complaints")
@Api(tags = " jsZyfwComplaints管理")
public class JsZyfwComplaintsController extends BaseController
{
    @Autowired
    private IJsZyfwComplaintsService jsZyfwComplaintsService;

    /**
     * 查询投诉反馈列表
     */
    @PreAuthorize("@ss.hasPermi('system:complaints:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询投诉反馈列表",notes = "投诉反馈列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "$column.columnComment", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "volunteerId", value = "投诉人", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "complaintContent", value = "投诉内容", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "complaintTime", value = "投诉时间", dataType = "Date", dataTypeClass = Date.class),
    })

    public TableDataInfo list(JsZyfwComplaints jsZyfwComplaints)
    {
        startPage();
        List<JsZyfwComplaints> list = jsZyfwComplaintsService.selectJsZyfwComplaintsList(jsZyfwComplaints);
        return getDataTable(list);
    }

    /**
     * 导出投诉反馈列表
     */
    @PreAuthorize("@ss.hasPermi('system:complaints:export')")
    @Log(title = "投诉反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出投诉反馈列表",notes = "导出投诉反馈列表")
    public void export(HttpServletResponse response, JsZyfwComplaints jsZyfwComplaints)
    {
        List<JsZyfwComplaints> list = jsZyfwComplaintsService.selectJsZyfwComplaintsList(jsZyfwComplaints);
        ExcelUtil<JsZyfwComplaints> util = new ExcelUtil<JsZyfwComplaints>(JsZyfwComplaints.class);
        util.exportExcel(response, list, "投诉反馈数据");
    }

    /**
     * 获取投诉反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:complaints:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取投诉反馈详细信息",notes = "获取投诉反馈详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "$column.javaField", value = "$column.columnComment", dataType = "id", dataTypeClass = Long.class),
    })
    public AjaxResult getInfo(@Valid  @NotNull(message = "id不能为空") @PathVariable("id") Long id)
    {
        return success(jsZyfwComplaintsService.selectJsZyfwComplaintsById(id));
    }

    /**
     * 新增投诉反馈
     */
    @PreAuthorize("@ss.hasPermi('system:complaints:add')")
    @Log(title = "投诉反馈", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增投诉反馈",notes = "新增投诉反馈")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "$column.columnComment", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "volunteerId", value = "投诉人", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "complaintContent", value = "投诉内容", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "complaintTime", value = "投诉时间", dataType = "Date", dataTypeClass = Date.class),
    })
    public AjaxResult add(@Valid @RequestBody JsZyfwComplaints jsZyfwComplaints)
    {
        return toAjax(jsZyfwComplaintsService.insertJsZyfwComplaints(jsZyfwComplaints));
    }

    /**
     * 修改投诉反馈
     */
    @PreAuthorize("@ss.hasPermi('system:complaints:edit')")
    @Log(title = "投诉反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改投诉反馈",notes = "修改投诉反馈")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "$column.columnComment", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "volunteerId", value = "投诉人", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "complaintContent", value = "投诉内容", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "complaintTime", value = "投诉时间", dataType = "Date", dataTypeClass = Date.class),
    })
    public AjaxResult edit(@Valid @RequestBody JsZyfwComplaints jsZyfwComplaints)
    {
        return toAjax(jsZyfwComplaintsService.updateJsZyfwComplaints(jsZyfwComplaints));
    }

    /**
     * 删除投诉反馈
     */
    @PreAuthorize("@ss.hasPermi('system:complaints:remove')")
    @Log(title = "投诉反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除投诉反馈",notes = "删除投诉反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", dataType = "ids", dataTypeClass = Long.class),
    })
    public AjaxResult remove(@Valid @NotEmpty(message = "ids字段不能为空") @PathVariable Long[] ids)
    {
        return toAjax(jsZyfwComplaintsService.deleteJsZyfwComplaintsByIds(ids));
    }
}
