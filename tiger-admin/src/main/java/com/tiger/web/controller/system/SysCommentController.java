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
import com.tiger.system.domain.SysComment;
import com.tiger.system.service.ISysCommentService;
import com.tiger.common.utils.poi.ExcelUtil;
import com.tiger.common.core.page.TableDataInfo;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 评论信息Controller
 * 
 * @author tiger
 * @date 2024-03-24
 */
@RestController
@RequestMapping("/system/comment")
@Api(tags = " sysComment管理")
public class SysCommentController extends BaseController
{
    @Autowired
    private ISysCommentService sysCommentService;

    /**
     * 查询评论信息列表
     */
    // @PreAuthorize("@ss.hasPermi('system:comment:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询评论信息列表",notes = "评论信息列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "评论id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "activittyId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "commentContent", value = "评论内容", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "createTime", value = "评论时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "updateTime", value = "更新时间", dataType = "Date", dataTypeClass = Date.class),
    })

    public TableDataInfo list(SysComment sysComment)
    {
        startPage();
        List<SysComment> list = sysCommentService.selectSysCommentList(sysComment);
        return getDataTable(list);
    }

    /**
     * 导出评论信息列表
     */
    // @PreAuthorize("@ss.hasPermi('system:comment:export')")
    @Log(title = "评论信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出评论信息列表",notes = "导出评论信息列表")
    public void export(HttpServletResponse response, SysComment sysComment)
    {
        List<SysComment> list = sysCommentService.selectSysCommentList(sysComment);
        ExcelUtil<SysComment> util = new ExcelUtil<SysComment>(SysComment.class);
        util.exportExcel(response, list, "评论信息数据");
    }

    /**
     * 获取评论信息详细信息
     */
    // @PreAuthorize("@ss.hasPermi('system:comment:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取评论信息详细信息",notes = "获取评论信息详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "$column.javaField", value = "$column.columnComment", dataType = "id", dataTypeClass = Long.class),
    })
    public AjaxResult getInfo(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id)
    {
        return success(sysCommentService.selectSysCommentById(id));
    }

    /**
     * 新增评论信息
     */
    // @PreAuthorize("@ss.hasPermi('system:comment:add')")
    @Log(title = "评论信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增评论信息",notes = "新增评论信息")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "评论id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "activittyId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "commentContent", value = "评论内容", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "createTime", value = "评论时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "updateTime", value = "更新时间", dataType = "Date", dataTypeClass = Date.class),
    })
    public AjaxResult add(@Valid @RequestBody SysComment sysComment)
    {
        return toAjax(sysCommentService.insertSysComment(sysComment));
    }

    /**
     * 修改评论信息
     */
    // @PreAuthorize("@ss.hasPermi('system:comment:edit')")
    @Log(title = "评论信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改评论信息",notes = "修改评论信息")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "评论id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "activittyId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "commentContent", value = "评论内容", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "createTime", value = "评论时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "updateTime", value = "更新时间", dataType = "Date", dataTypeClass = Date.class),
    })
    public AjaxResult edit(@Valid @RequestBody SysComment sysComment)
    {
        return toAjax(sysCommentService.updateSysComment(sysComment));
    }

    /**
     * 删除评论信息
     */
    // @PreAuthorize("@ss.hasPermi('system:comment:remove')")
    @Log(title = "评论信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除评论信息",notes = "删除评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", dataType = "ids", dataTypeClass = Long.class),
    })
    public AjaxResult remove(@Valid @NotEmpty(message = "ids字段不能为空") @PathVariable Long[] ids)
    {
        return toAjax(sysCommentService.deleteSysCommentByIds(ids));
    }
}
