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
import com.tiger.system.domain.SysActivityCommentRelation;
import com.tiger.system.service.ISysActivityCommentRelationService;
import com.tiger.common.utils.poi.ExcelUtil;
import com.tiger.common.core.page.TableDataInfo;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 活动评论关系Controller
 * 
 * @author tiger
 * @date 2024-03-24
 */
@RestController
@RequestMapping("/system/activityComment/relation")
@Api(tags = " sysActivityCommentRelation管理")
public class SysActivityCommentRelationController extends BaseController
{
    @Autowired
    private ISysActivityCommentRelationService sysActivityCommentRelationService;

    /**
     * 查询活动评论关系列表
     */
    // @PreAuthorize("@ss.hasPermi('system:relation:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询活动评论关系列表",notes = "活动评论关系列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "活动评论关系id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "activityId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "commentId", value = "评论id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "volunteerId", value = "志愿者id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "createTime", value = "关系创建时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "updateTime", value = "关系更新时间", dataType = "Date", dataTypeClass = Date.class),
    })

    public TableDataInfo list(SysActivityCommentRelation sysActivityCommentRelation)
    {
        startPage();
        List<SysActivityCommentRelation> list = sysActivityCommentRelationService.selectSysActivityCommentRelationList(sysActivityCommentRelation);
        return getDataTable(list);
    }

    /**
     * 导出活动评论关系列表
     */
    // @PreAuthorize("@ss.hasPermi('system:relation:export')")
    @Log(title = "活动评论关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出活动评论关系列表",notes = "导出活动评论关系列表")
    public void export(HttpServletResponse response, SysActivityCommentRelation sysActivityCommentRelation)
    {
        List<SysActivityCommentRelation> list = sysActivityCommentRelationService.selectSysActivityCommentRelationList(sysActivityCommentRelation);
        ExcelUtil<SysActivityCommentRelation> util = new ExcelUtil<SysActivityCommentRelation>(SysActivityCommentRelation.class);
        util.exportExcel(response, list, "活动评论关系数据");
    }

    /**
     * 获取活动评论关系详细信息
     */
    // @PreAuthorize("@ss.hasPermi('system:relation:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取活动评论关系详细信息",notes = "获取活动评论关系详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "$column.javaField", value = "$column.columnComment", dataType = "id", dataTypeClass = Long.class),
    })
    public AjaxResult getInfo(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id)
    {
        return success(sysActivityCommentRelationService.selectSysActivityCommentRelationById(id));
    }

    /**
     * 新增活动评论关系
     */
    // @PreAuthorize("@ss.hasPermi('system:relation:add')")
    @Log(title = "活动评论关系", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增活动评论关系",notes = "新增活动评论关系")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "活动评论关系id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "activityId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "commentId", value = "评论id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "volunteerId", value = "志愿者id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "createTime", value = "关系创建时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "updateTime", value = "关系更新时间", dataType = "Date", dataTypeClass = Date.class),
    })
    public AjaxResult add(@Valid @RequestBody SysActivityCommentRelation sysActivityCommentRelation)
    {
        return toAjax(sysActivityCommentRelationService.insertSysActivityCommentRelation(sysActivityCommentRelation));
    }

    /**
     * 修改活动评论关系
     */
    // @PreAuthorize("@ss.hasPermi('system:relation:edit')")
    @Log(title = "活动评论关系", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改活动评论关系",notes = "修改活动评论关系")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "活动评论关系id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "activityId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "commentId", value = "评论id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "volunteerId", value = "志愿者id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "createTime", value = "关系创建时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "updateTime", value = "关系更新时间", dataType = "Date", dataTypeClass = Date.class),
    })
    public AjaxResult edit(@Valid @RequestBody SysActivityCommentRelation sysActivityCommentRelation)
    {
        return toAjax(sysActivityCommentRelationService.updateSysActivityCommentRelation(sysActivityCommentRelation));
    }

    /**
     * 删除活动评论关系
     */
    // @PreAuthorize("@ss.hasPermi('system:relation:remove')")
    @Log(title = "活动评论关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除活动评论关系",notes = "删除活动评论关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", dataType = "ids", dataTypeClass = Long.class),
    })
    public AjaxResult remove(@Valid @NotEmpty(message = "ids字段不能为空") @PathVariable Long[] ids)
    {
        return toAjax(sysActivityCommentRelationService.deleteSysActivityCommentRelationByIds(ids));
    }
}
