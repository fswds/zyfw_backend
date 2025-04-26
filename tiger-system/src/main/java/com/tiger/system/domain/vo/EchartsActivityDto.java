package com.tiger.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class EchartsActivityDto implements Serializable {

    /** 活动名称 */
    @ApiModelProperty("活动名称")
    @NotBlank(message = "活动名称不能为空")
    private String name;

    /** 参加活动人数 **/
    @ApiModelProperty("活动报名人数")
    private Integer attendPeopleCount;


}
