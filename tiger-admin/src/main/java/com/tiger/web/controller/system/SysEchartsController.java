package com.tiger.web.controller.system;

import com.tiger.common.core.controller.BaseController;
import com.tiger.common.core.domain.AjaxResult;
import com.tiger.common.core.domain.entity.SysMenu;
import com.tiger.system.domain.vo.EchartsActivityDto;
import com.tiger.system.domain.vo.EchartsOrganizationDto;
import com.tiger.system.service.ISysEchartsService;
import com.tiger.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 图表信息
 *
 * @author tiger
 */
@RestController
@RequestMapping("/system/echarts")
public class SysEchartsController extends BaseController {

    @Autowired
    private ISysEchartsService sysEchartsService;

    /**
     * 获取各活动实际参加人数
     */
    @PreAuthorize("@ss.hasPermi('system:echarts:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<EchartsActivityDto> echartsActivityDtos = sysEchartsService.selectActivityDataList();
        return success(echartsActivityDtos);
    }


    /**
     *
     */
    @PreAuthorize("@ss.hasPermi('system:echarts:list')")
    @GetMapping("/listOrganization")
    public AjaxResult listOrganization()
    {
        List<EchartsOrganizationDto> echartsOrganizationDtoList = sysEchartsService.listOrganization();
        return success(echartsOrganizationDtoList);
    }


    /**
     * 获取各活动实际参加人数
     */
    @PreAuthorize("@ss.hasPermi('system:echarts:list')")
    @GetMapping("/activityType")
    public AjaxResult activityType()
    {
        List<EchartsOrganizationDto> echartsOrganizationDtoList = sysEchartsService.activityType();
        return success(echartsOrganizationDtoList);
    }


}
