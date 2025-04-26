package com.tiger.web.controller.system;

import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.tiger.system.domain.SysActivity;
import com.tiger.system.domain.SysVolunteerActivityRelation;
import com.tiger.system.domain.vo.SysActivityRelationDto;
import com.tiger.system.mapper.SysActivityMapper;
import com.tiger.system.mapper.SysVolunteerActivityRelationMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
import com.tiger.system.domain.SysActivitySignNotice;
import com.tiger.system.service.ISysActivitySignNoticeService;
import com.tiger.common.utils.poi.ExcelUtil;
import com.tiger.common.core.page.TableDataInfo;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 签到通知公告Controller
 * 
 * @author tiger
 * @date 2024-04-30
 */
@RestController
@RequestMapping("/system/signNotice")
@Api(tags = " sysActivitySignNotice管理")
public class SysActivitySignNoticeController extends BaseController
{
    @Autowired
    private ISysActivitySignNoticeService sysActivitySignNoticeService;

    @Resource
    private SysVolunteerActivityRelationMapper sysVolunteerActivityRelationMapper;


    @Resource
    private SysActivityMapper sysActivityMapper;

    /**
     * 查询签到通知公告列表
     */
    // @PreAuthorize("@ss.hasPermi('system:notice:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询签到通知公告列表",notes = "签到通知公告列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "noticeId", value = "公告ID", dataType = "Integer", dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "noticeTitle", value = "公告标题", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "noticeType", value = "公告类型（1通知 2公告）", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "noticeContent", value = "公告内容", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "status", value = "公告状态（0正常 1关闭）", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "startDate", value = "签到开始时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "endDate", value = "签到结束时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "signScore", value = "每次签到奖励积分", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "updateTime", value = "更新时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "createTime", value = "通知创建时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "activityId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
    })

    public TableDataInfo list(SysActivitySignNotice sysActivitySignNotice)
    {
        SysVolunteerActivityRelation sysVolunteerActivityRelation = new SysVolunteerActivityRelation();
        sysVolunteerActivityRelation.setUserId(sysActivitySignNotice.getUserId());
        sysVolunteerActivityRelation.setIsPerson("1");
        List<SysActivityRelationDto> sysActivityRelationDtos = sysVolunteerActivityRelationMapper.selectSysVolunteerActivityRelationList(sysVolunteerActivityRelation);
        if (CollectionUtils.isEmpty(sysActivityRelationDtos)){
            return getDataTable(Collections.emptyList());
        }
        List<Long> activityIdList = sysActivityRelationDtos.stream().map(SysActivityRelationDto::getActivityId).collect(Collectors.toList());
        sysActivitySignNotice.setActivityIdList(activityIdList);
        startPage();
        List<SysActivitySignNotice> list = sysActivitySignNoticeService.selectSysActivitySignNoticeList(sysActivitySignNotice);
        return getDataTable(list);
    }

    /**
     * 导出签到通知公告列表
     */
    // @PreAuthorize("@ss.hasPermi('system:notice:export')")
    @Log(title = "签到通知公告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出签到通知公告列表",notes = "导出签到通知公告列表")
    public void export(HttpServletResponse response, SysActivitySignNotice sysActivitySignNotice)
    {
        List<SysActivitySignNotice> list = sysActivitySignNoticeService.selectSysActivitySignNoticeList(sysActivitySignNotice);
        ExcelUtil<SysActivitySignNotice> util = new ExcelUtil<SysActivitySignNotice>(SysActivitySignNotice.class);
        util.exportExcel(response, list, "签到通知公告数据");
    }

    /**
     * 获取签到通知公告详细信息
     */
    // @PreAuthorize("@ss.hasPermi('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    @ApiOperation(value = "获取签到通知公告详细信息",notes = "获取签到通知公告详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "$column.javaField", value = "$column.columnComment", dataType = "noticeId", dataTypeClass = Integer.class),
    })
    public AjaxResult getInfo(@Valid @NotNull(message = "noticeId不能为空") @PathVariable("noticeId") Integer noticeId)
    {
        return success(sysActivitySignNoticeService.selectSysActivitySignNoticeByNoticeId(noticeId));
    }

    /**
     * 新增签到通知公告
     */
    // @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @Log(title = "签到通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增签到通知公告",notes = "新增签到通知公告")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "noticeId", value = "公告ID", dataType = "Integer", dataTypeClass = Integer.class),
                @ApiImplicitParam(name = "noticeTitle", value = "公告标题", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "noticeType", value = "公告类型（1通知 2公告）", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "noticeContent", value = "公告内容", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "status", value = "公告状态（0正常 1关闭）", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "startDate", value = "签到开始时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "endDate", value = "签到结束时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "signScore", value = "每次签到奖励积分", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "updateTime", value = "更新时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "createTime", value = "通知创建时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "activityId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
    })
    public AjaxResult add(@Valid @RequestBody SysActivitySignNotice sysActivitySignNotice) throws Exception
    {
        SysActivity sysActivity = sysActivityMapper.selectSysActivityById(sysActivitySignNotice.getActivityId());
        if (Objects.isNull(sysActivity)){
            throw new Exception("活动不存在");
        }
        // 活动结束时间小于当前时间，则活动过期
        if (sysActivitySignNotice.getEndDate().compareTo(sysActivity.getEndTime()) > 0){
            throw new Exception("请确保签到时间在活动有效期内");
        }
        // 活动开始时间小于当前时间表示活动已经开始也不让加入
        if (sysActivitySignNotice.getStartDate().compareTo(sysActivity.getStartTime()) < 0){
            throw new Exception("请确保签到时间在活动有效期内");
        }
        return toAjax(sysActivitySignNoticeService.insertSysActivitySignNotice(sysActivitySignNotice));
    }

    /**
     * 修改签到通知公告
     */
    // @PreAuthorize("@ss.hasPermi('system:notice:edit')")
    @Log(title = "签到通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改签到通知公告",notes = "修改签到通知公告")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "noticeId", value = "公告ID", dataType = "Integer", dataTypeClass = Integer.class),
                @ApiImplicitParam(name = "noticeTitle", value = "公告标题", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "noticeType", value = "公告类型（1通知 2公告）", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "noticeContent", value = "公告内容", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "status", value = "公告状态（0正常 1关闭）", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "startDate", value = "签到开始时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "endDate", value = "签到结束时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "signScore", value = "每次签到奖励积分", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "updateTime", value = "更新时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "createTime", value = "通知创建时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "activityId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
    })
    public AjaxResult edit(@Valid @RequestBody SysActivitySignNotice sysActivitySignNotice) throws Exception
    {
        SysActivity sysActivity = sysActivityMapper.selectSysActivityById(sysActivitySignNotice.getActivityId());
        if (Objects.isNull(sysActivity)){
            throw new Exception("活动不存在");
        }
        // 活动结束时间小于当前时间，则活动过期
        if (sysActivitySignNotice.getEndDate().compareTo(sysActivity.getEndTime()) > 0){
            throw new Exception("请确保签到时间在活动有效期内");
        }
        // 活动开始时间小于当前时间表示活动已经开始也不让加入
        if (sysActivitySignNotice.getStartDate().compareTo(sysActivity.getStartTime()) < 0){
            throw new Exception("请确保签到时间在活动有效期内");
        }
        return toAjax(sysActivitySignNoticeService.updateSysActivitySignNotice(sysActivitySignNotice));
    }

    /**
     * 删除签到通知公告
     */
    // @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @Log(title = "签到通知公告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{noticeIds}")
    @ApiOperation(value = "删除签到通知公告",notes = "删除签到通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "noticeIds", value = "noticeIds", dataType = "noticeIds", dataTypeClass = Integer.class),
    })
    public AjaxResult remove(@Valid @NotEmpty(message = "noticeIds字段不能为空") @PathVariable Integer[] noticeIds)
    {
        return toAjax(sysActivitySignNoticeService.deleteSysActivitySignNoticeByNoticeIds(noticeIds));
    }
}
