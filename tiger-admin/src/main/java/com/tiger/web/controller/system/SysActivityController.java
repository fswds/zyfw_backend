package com.tiger.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.tiger.common.core.domain.model.LoginUser;
import com.tiger.system.domain.SysVolunteerOrganization;
import com.tiger.system.domain.SysVolunteerOrganizationRelation;
import com.tiger.system.domain.vo.SysActivityDto;
import com.tiger.system.service.ISysVolunteerOrganizationRelationService;
import com.tiger.system.service.ISysVolunteerOrganizationService;
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
import com.tiger.system.domain.SysActivity;
import com.tiger.system.service.ISysActivityService;
import com.tiger.common.utils.poi.ExcelUtil;
import com.tiger.common.core.page.TableDataInfo;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 活动信息Controller
 * 
 * @author tiger
 * @date 2024-03-24
 */
@RestController
@RequestMapping("/system/activity")
@Api(tags = " sysActivity管理")
public class SysActivityController extends BaseController
{
    @Autowired
    private ISysActivityService sysActivityService;

    @Autowired
    private ISysVolunteerOrganizationRelationService iSysVolunteerOrganizationRelationService;

    /**
     * 查询活动信息列表
     */
    // @PreAuthorize("@ss.hasPermi('system:activity:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询活动信息列表",notes = "活动信息列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "活动ID", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "name", value = "活动名称", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "describe", value = "活动描述", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "location", value = "活动地点", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "organizer", value = "主办方", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "status", value = "活动状态", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "contacts", value = "联系人", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "contactsPhone", value = "联系人电话", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "substance", value = "活动所需物资", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "ask", value = "志愿者要求", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "imgs", value = "活动图片", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "createTime", value = "创建时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "updateTime", value = "更新时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "activityType", value = "活动类型", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "organizationId", value = "志愿组织id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "numberLimit", value = "活动人数限制", dataType = "Integer", dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "attendPeopleCount", value = "活动报名人数", dataType = "Integer", dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "signSwitch", value = "活动签到开关，1开启，0关闭", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "signScore", value = "活动每次签到获取积分", dataType = "String", dataTypeClass = Integer.class),
    })

    public TableDataInfo list(SysActivity sysActivity)
    {
        startPage();
        LoginUser loginUser = getLoginUser();
        if (!loginUser.getUser().getRoles().get(0).getRoleKey().contains("admin") || loginUser.getUser().getRoles().get(0).getRoleKey().contains("organizationadmin")){
            SysVolunteerOrganizationRelation sysVolunteerOrganization = new SysVolunteerOrganizationRelation();
            sysVolunteerOrganization.setUserId(loginUser.getUserId());
            List<SysVolunteerOrganizationRelation> sysVolunteerOrganizations = iSysVolunteerOrganizationRelationService.selectSysVolunteerOrganizationRelationList(sysVolunteerOrganization);
            List<Long> organizationIds = sysVolunteerOrganizations.stream().map(SysVolunteerOrganizationRelation::getOrganizationId).collect(Collectors.toList());
            // sysActivity.setOrganizationIds(organizationIds);
            sysActivity.setExtra(organizationIds.get(0).toString());
        }
        List<SysActivityDto> list = sysActivityService.selectSysActivityList(sysActivity);
        return getDataTable(list);
    }

    /**
     * 导出活动信息列表
     */
    // @PreAuthorize("@ss.hasPermi('system:activity:export')")
    @Log(title = "活动信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出活动信息列表",notes = "导出活动信息列表")
    public void export(HttpServletResponse response, SysActivity sysActivity)
    {
        List<SysActivityDto> list = sysActivityService.selectSysActivityList(sysActivity);
        ExcelUtil<SysActivityDto> util = new ExcelUtil<SysActivityDto>(SysActivityDto.class);
        util.exportExcel(response, list, "活动信息数据");
    }

    /**
     * 获取活动信息详细信息
     */
    // @PreAuthorize("@ss.hasPermi('system:activity:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取活动信息详细信息",notes = "获取活动信息详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "活动id", dataType = "id", dataTypeClass = Long.class),
    })
    public AjaxResult getInfo(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id)
    {
        return success(sysActivityService.selectSysActivityById(id));
    }

    /**
     * 新增活动信息
     */
    // @PreAuthorize("@ss.hasPermi('system:activity:add')")
    @Log(title = "活动信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增活动信息",notes = "新增活动信息")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "活动ID", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "name", value = "活动名称", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "describe", value = "活动描述", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "location", value = "活动地点", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "organizer", value = "主办方", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "status", value = "活动状态", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "contacts", value = "联系人", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "contactsPhone", value = "联系人电话", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "substance", value = "活动所需物资", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "ask", value = "志愿者要求", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "imgs", value = "活动图片", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "createTime", value = "创建时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "updateTime", value = "更新时间", dataType = "Date", dataTypeClass = Date.class),
                @ApiImplicitParam(name = "activityType", value = "活动类型", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "organizationId", value = "志愿组织id", dataType = "Long", dataTypeClass = Long.class),
                @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "numberLimit", value = "活动人数限制", dataType = "Integer", dataTypeClass = Integer.class),
                @ApiImplicitParam(name = "attendPeopleCount", value = "活动报名人数", dataType = "Integer", dataTypeClass = Integer.class),
                @ApiImplicitParam(name = "signSwitch", value = "活动签到开关，1开启，0关闭", dataType = "String", dataTypeClass = String.class),
                @ApiImplicitParam(name = "signScore", value = "活动每次签到获取积分", dataType = "String", dataTypeClass = Integer.class),
    })
    public AjaxResult add(@Valid @RequestBody SysActivity sysActivity)
    {
        LoginUser loginUser = getLoginUser();
        SysVolunteerOrganizationRelation sysVolunteerOrganization = new SysVolunteerOrganizationRelation();
        sysVolunteerOrganization.setUserId(loginUser.getUserId());
        List<SysVolunteerOrganizationRelation> sysVolunteerOrganizations = iSysVolunteerOrganizationRelationService.selectSysVolunteerOrganizationRelationList(sysVolunteerOrganization);
        List<Long> organizationIds = sysVolunteerOrganizations.stream().map(SysVolunteerOrganizationRelation::getOrganizationId).collect(Collectors.toList());
        // 组织id为空，这里是查询人的组织，然后把人的组织id放到活动里
        if (!CollectionUtils.isEmpty(organizationIds)){
            sysActivity.setExtra(organizationIds.get(0).toString());
        }
        if (loginUser.getUsername().contains("admin")){
            sysActivity.setStatus("2");
        }
        return toAjax(sysActivityService.insertSysActivity(sysActivity));
    }

    /**
     * 修改活动信息
     */
    // @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @Log(title = "活动信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改活动信息",notes = "修改活动信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "活动ID", dataType = "Long", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "name", value = "活动名称", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "describe", value = "活动描述", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "location", value = "活动地点", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "Date", dataTypeClass = Date.class),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "Date", dataTypeClass = Date.class),
            @ApiImplicitParam(name = "organizer", value = "主办方", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "status", value = "活动状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "contacts", value = "联系人", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "contactsPhone", value = "联系人电话", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "substance", value = "活动所需物资", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "ask", value = "志愿者要求", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "imgs", value = "活动图片", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "createTime", value = "创建时间", dataType = "Date", dataTypeClass = Date.class),
            @ApiImplicitParam(name = "updateTime", value = "更新时间", dataType = "Date", dataTypeClass = Date.class),
            @ApiImplicitParam(name = "activityType", value = "活动类型", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "organizationId", value = "志愿组织id", dataType = "Long", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "numberLimit", value = "活动人数限制", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "attendPeopleCount", value = "活动报名人数", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "signSwitch", value = "活动签到开关，1开启，0关闭", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "signScore", value = "活动每次签到获取积分", dataType = "String", dataTypeClass = Integer.class),
    })
    public AjaxResult edit(@Valid @RequestBody SysActivity sysActivity)
    {
        return toAjax(sysActivityService.updateSysActivity(sysActivity));
    }

    /**
     * 删除活动信息
     */
    // @PreAuthorize("@ss.hasPermi('system:activity:remove')")
    @Log(title = "活动信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除活动信息",notes = "删除活动信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", dataType = "ids", dataTypeClass = Long.class),
    })
    public AjaxResult remove(@Valid @NotEmpty(message = "ids字段不能为空") @PathVariable Long[] ids) throws Exception
    {
        return toAjax(sysActivityService.deleteSysActivityByIds(ids));
    }
}
