package com.tiger.web.controller.system;

import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.tiger.common.core.domain.entity.SysRole;
import com.tiger.common.core.domain.entity.SysUser;
import com.tiger.common.core.domain.model.LoginUser;
import com.tiger.common.utils.DateUtils;
import com.tiger.system.domain.SysActivity;
import com.tiger.system.domain.SysActivitySignNotice;
import com.tiger.system.domain.SysVolunteerActivityRelation;
import com.tiger.system.domain.vo.SysActivityRelationDto;
import com.tiger.system.mapper.SysActivityMapper;
import com.tiger.system.mapper.SysActivitySignNoticeMapper;
import com.tiger.system.mapper.SysUserMapper;
import com.tiger.system.service.ISysVolunteerActivityRelationService;
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
import com.tiger.system.domain.SysSignRecord;
import com.tiger.system.service.ISysSignRecordService;
import com.tiger.common.utils.poi.ExcelUtil;
import com.tiger.common.core.page.TableDataInfo;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 签到记录Controller
 * 
 * @author tiger
 * @date 2024-04-28
 */
@RestController
@RequestMapping("/system/record")
@Api(tags = " sysSignRecord管理")
public class SysSignRecordController extends BaseController
{
    @Autowired
    private ISysSignRecordService sysSignRecordService;

    @Autowired
    private ISysVolunteerActivityRelationService sysVolunteerActivityRelationService;

    @Resource
    private SysActivityMapper sysActivityMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysActivitySignNoticeMapper sysActivitySignNoticeMapper;


    /**
     * 查询签到记录列表
     */
    // @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询签到记录列表",notes = "签到记录列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "签到记录id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "activityId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "signDate", value = "签到事件", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "examineStatus", value = "签到审核状态", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "examineImg", value = "签到图片", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "createTime", value = "签到记录创建时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "updateTime", value = "签到记录更新时间", dataType = "Date", dataTypeClass = Date.class),
    })

    public TableDataInfo list(SysSignRecord sysSignRecord)
    {
        LoginUser loginUser = getLoginUser();
        boolean isAdmin = false;
        for (SysRole role : loginUser.getUser().getRoles()) {
            if (role.getRoleKey().contains("admin")){
                isAdmin = true;
                break;
            }
        }
        if (!isAdmin){
            sysSignRecord.setUserId(loginUser.getUserId());
        }
        startPage();
        List<SysSignRecord> list = sysSignRecordService.selectSysSignRecordList(sysSignRecord);
        list.forEach(a -> {
            SysUser sysUser = sysUserMapper.selectUserById(a.getUserId());
            a.setUserName(sysUser.getUserName());
        });
        return getDataTable(list);
    }

    /**
     * 导出签到记录列表
     */
    // @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "签到记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出签到记录列表",notes = "导出签到记录列表")
    public void export(HttpServletResponse response, SysSignRecord sysSignRecord)
    {
        List<SysSignRecord> list = sysSignRecordService.selectSysSignRecordList(sysSignRecord);
        ExcelUtil<SysSignRecord> util = new ExcelUtil<SysSignRecord>(SysSignRecord.class);
        util.exportExcel(response, list, "签到记录数据");
    }

    /**
     * 获取签到记录详细信息
     */
    // @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取签到记录详细信息",notes = "获取签到记录详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "$column.javaField", value = "$column.columnComment", dataType = "id", dataTypeClass = Long.class),
    })
    public AjaxResult getInfo(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id)
    {
        return success(sysSignRecordService.selectSysSignRecordById(id));
    }

    // @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "校验签到记录", businessType = BusinessType.OTHER)
    @PostMapping(path = "check")
    @ApiOperation(value = "校验签到记录",notes = "校验签到记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "签到记录id", dataType = "Long", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "activityId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "signDate", value = "签到事件", dataType = "Date", dataTypeClass = Date.class),
            @ApiImplicitParam(name = "examineStatus", value = "签到审核状态", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "examineImg", value = "签到图片", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "createTime", value = "签到记录创建时间", dataType = "Date", dataTypeClass = Date.class),
            @ApiImplicitParam(name = "updateTime", value = "签到记录更新时间", dataType = "Date", dataTypeClass = Date.class),
    })
    public AjaxResult checkSign(@RequestBody SysSignRecord sysSignRecord) {
        // Map<String,String> result = new HashMap<>();
        SysActivity sysActivity = sysActivityMapper.selectSysActivityById(sysSignRecord.getActivityId());
        if (Objects.isNull(sysActivity)){
            // result.put("1","活动不存在");
            return AjaxResult.error("活动不存在");
        }

        if (sysActivity.getEndTime().compareTo(new Date()) < 0){
            // throw new Exception("活动已结束");
            return AjaxResult.error("活动已结束");
        }

        if (sysActivity.getStartTime().compareTo(new Date()) > 0){
            // throw new Exception("活动未开始");
            return AjaxResult.error("活动未开始");
        }

        if (!sysActivity.getStatus().equals("2")){
            // throw new Exception("活动未审核通过");
            return AjaxResult.error("活动未审核通过");
        }
        SysActivitySignNotice sysActivitySignNotice = sysActivitySignNoticeMapper.selectSysActivitySignNoticeByNoticeId(sysSignRecord.getSignNoticeId());
        if (Objects.isNull(sysActivitySignNotice)){
            // throw new Exception("活动的通知不存在");
            return AjaxResult.error("活动的通知不存在");
        }
        if (sysActivitySignNotice.getEndDate().compareTo(new Date()) < 0){
            // throw new Exception("活动已结束");
            return AjaxResult.error("活动签到已结束");
        }

        if (sysActivitySignNotice.getStartDate().compareTo(new Date()) > 0){
            // throw new Exception("活动未开始");
            return AjaxResult.error("活动签到未开始");
        }
        SysVolunteerActivityRelation activityRelation = new SysVolunteerActivityRelation();
        activityRelation.setActivityId(sysActivity.getId());
        activityRelation.setUserId(sysSignRecord.getUserId());
        List<SysActivityRelationDto> sysActivityRelationDtos = sysVolunteerActivityRelationService.selectSysVolunteerActivityRelationList(activityRelation);
        if (CollectionUtils.isEmpty(sysActivityRelationDtos)){
            // throw new Exception("志愿者未加入该活动");
            return AjaxResult.error("志愿者未加入该活动");
        }

        if (!sysActivityRelationDtos.get(0).getUserApprovalStatus().equals("2")){
            // throw new Exception("志愿者加入该活动未审核通过，不允许进行签到");
            return AjaxResult.error("志愿者加入该活动未审核通过，不允许进行签到");
        }

        if (sysSignRecord.getSignDate() == null){
            String date = DateUtils.getDate();
            Date date1 = DateUtils.parseDate(date);
            sysSignRecord.setSignDate(date1);
        }
        List<SysSignRecord> sysSignRecords = sysSignRecordService.selectSysSignRecordList(sysSignRecord);
        if (!CollectionUtils.isEmpty(sysSignRecords)){
            // throw new Exception("当日已经签到，不能重复签到");
            return AjaxResult.error("当日已经签到，不能重复签到");
        }
        return AjaxResult.success();
    }
    /**
     * 新增签到记录
     */
    // @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "签到记录", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增签到记录",notes = "新增签到记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "签到记录id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "activityId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "signDate", value = "签到事件", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "examineStatus", value = "签到审核状态", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "examineImg", value = "签到图片", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "createTime", value = "签到记录创建时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "updateTime", value = "签到记录更新时间", dataType = "Date", dataTypeClass = Date.class),
    })
    public AjaxResult add(@Valid @RequestBody SysSignRecord sysSignRecord) throws Exception
    {
        SysActivity sysActivity = sysActivityMapper.selectSysActivityById(sysSignRecord.getActivityId());
        if (Objects.isNull(sysActivity)){
            throw new Exception("活动不存在");
        }

        if (sysActivity.getEndTime().compareTo(new Date()) < 0){
            throw new Exception("活动已结束");
        }

        if (sysActivity.getStartTime().compareTo(new Date()) > 0){
            throw new Exception("活动未开始");
        }

        if (!sysActivity.getStatus().equals("2")){
            throw new Exception("活动未审核通过");
         }
        // if (!sysActivity.getSignSwitch().equals("1")){
        //     throw new Exception("活动签到开关未开始");
        // }

        SysActivitySignNotice sysActivitySignNotice = sysActivitySignNoticeMapper.selectSysActivitySignNoticeByNoticeId(sysSignRecord.getSignNoticeId());
        if (Objects.isNull(sysActivitySignNotice)){
            throw new Exception("活动的通知不存在");
        }
        if (sysActivitySignNotice.getEndDate().compareTo(new Date()) < 0){
            throw new Exception("活动签到已结束");
        }

        if (sysActivitySignNotice.getStartDate().compareTo(new Date()) > 0){
            throw new Exception("活动签到未开始");
        }
        SysVolunteerActivityRelation activityRelation = new SysVolunteerActivityRelation();
        activityRelation.setActivityId(sysActivity.getId());
        activityRelation.setUserId(sysSignRecord.getUserId());
        List<SysActivityRelationDto> sysActivityRelationDtos = sysVolunteerActivityRelationService.selectSysVolunteerActivityRelationList(activityRelation);
        if (CollectionUtils.isEmpty(sysActivityRelationDtos)){
            throw new Exception("志愿者未加入该活动");
        }

        if (!sysActivityRelationDtos.get(0).getUserApprovalStatus().equals("2")){
            throw new Exception("志愿者加入该活动未审核通过，不允许进行签到");
        }

        if (sysSignRecord.getSignDate() == null){
            String date = DateUtils.getDate();
            Date date1 = DateUtils.parseDate(date);
            sysSignRecord.setSignDate(date1);
        }
        List<SysSignRecord> sysSignRecords = sysSignRecordService.selectSysSignRecordList(sysSignRecord);
        if (!CollectionUtils.isEmpty(sysSignRecords)){
            throw new Exception("当日已经签到，不能重复签到");
        }
        return toAjax(sysSignRecordService.insertSysSignRecord(sysSignRecord));
    }

    /**
     * 修改签到记录
     */
    // @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "签到记录", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改签到记录",notes = "修改签到记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "签到记录id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "activityId", value = "活动id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", dataTypeClass = Long.class),
        @ApiImplicitParam(name = "signDate", value = "签到事件", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "examineStatus", value = "签到审核状态", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "examineImg", value = "签到图片", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "extra", value = "扩展字段", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "createTime", value = "签到记录创建时间", dataType = "Date", dataTypeClass = Date.class),
        @ApiImplicitParam(name = "updateTime", value = "签到记录更新时间", dataType = "Date", dataTypeClass = Date.class),
    })
    public AjaxResult edit(@Valid @RequestBody SysSignRecord sysSignRecord) throws Exception
    {
        SysSignRecord sysSignRecord1 = sysSignRecordService.selectSysSignRecordById(sysSignRecord.getId());
        if (Objects.isNull(sysSignRecord1)){
            throw new Exception("签到记录不存在，不允许编辑");
        }
        if (sysSignRecord1.getExamineStatus().equals("1")){
            throw new Exception("签到记录已经审核通过，不允许修改");
        }
        return toAjax(sysSignRecordService.updateSysSignRecord(sysSignRecord));
    }

    /**
     * 删除签到记录
     */
    // @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "签到记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除签到记录",notes = "删除签到记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", dataType = "ids", dataTypeClass = Long.class),
    })
    public AjaxResult remove(@Valid @NotEmpty(message = "ids字段不能为空") @PathVariable Long[] ids)
    {
        return toAjax(sysSignRecordService.deleteSysSignRecordByIds(ids));
    }
}
