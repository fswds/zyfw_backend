package com.tiger.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiger.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import javax.validation.constraints.*;
import com.tiger.common.core.domain.BaseEntity;

/**
 * 养老机构对象 js_zyfw_elderly_institutions
 * 
 * @author wds
 * @date 2025-04-28
 */
@Data
@TableName(value = "js_zyfw_elderly_institutions")
@ApiModel(value = "养老机构")
public class JsZyfwElderlyInstitutions extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("id")
    private Long id;

    /** 机构名 */
    @ApiModelProperty("机构名")
    @Excel(name = "机构名")
    private String name;

    /** 地点 */
    @ApiModelProperty("地点")
    @Excel(name = "地点")
    private String location;

    /** 建筑信息 */
    @ApiModelProperty("建筑信息")
    @Excel(name = "建筑信息")
    private String buildingInfo;

    /** 介绍 */
    @ApiModelProperty("介绍")
    @Excel(name = "介绍")
    private String description;

    /** 机构类型 */
    @ApiModelProperty("机构类型")
    @Excel(name = "机构类型")
    private String serviceType;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("location", getLocation())
            .append("buildingInfo", getBuildingInfo())
            .append("description", getDescription())
            .append("serviceType", getServiceType())
            .toString();
    }
}
