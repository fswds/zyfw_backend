package com.tiger.system.domain.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class EchartsOrganizationDto implements Serializable {

    /** 组织名 */
    @ApiModelProperty("名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    /** 组织名 */
    @ApiModelProperty("数量")
    @NotBlank(message = "数量不能为空")
    private Long value;
}
