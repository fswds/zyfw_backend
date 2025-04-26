package com.tiger.system.service;

import com.tiger.system.domain.vo.EchartsActivityDto;
import com.tiger.system.domain.vo.EchartsOrganizationDto;

import java.util.List;

/**
 * 图表 业务层
 *
 * @author tiger
 */
public interface ISysEchartsService {

    public List<EchartsActivityDto> selectActivityDataList();


    public List<EchartsOrganizationDto>  listOrganization();

    List<EchartsOrganizationDto> activityType();
}
