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
 * 建筑管理对象 js_zyfw_volunteer_service_buildings
 * 
 * @author wds
 * @date 2025-04-28
 */
@Data
@TableName(value = "js_zyfw_volunteer_service_buildings")
@ApiModel(value = "建筑管理")
public class JsZyfwVolunteerServiceBuildings extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 建筑名 */
    @ApiModelProperty("建筑名")
    @NotBlank(message = "建筑名不能为空")
    @Excel(name = "建筑名")
    private String name;

    /** 地址 */
    @ApiModelProperty("地址")
    @Excel(name = "地址")
    private String address;

    /** 是否空闲 */
    @ApiModelProperty("是否空闲")
    @NotNull(message = "是否空闲不能为空")
    @Excel(name = "是否空闲")
    private Long isAvailable;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("address", getAddress())
            .append("isAvailable", getIsAvailable())
            .toString();
    }
}
