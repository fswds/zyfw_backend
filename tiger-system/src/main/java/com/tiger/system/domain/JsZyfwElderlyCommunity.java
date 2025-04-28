package com.tiger.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 老人信息对象 js_zyfw_elderly_community
 * 
 * @author wds
 * @date 2025-04-28
 */
@Data
@TableName(value = "js_zyfw_elderly_community")
@ApiModel(value = "老人信息")
public class JsZyfwElderlyCommunity extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty("$column.columnComment")
    private Long id;

    /** 姓名 */
    @ApiModelProperty("姓名")
    @Excel(name = "姓名")
    private String name;

    /** 年龄 */
    @ApiModelProperty("年龄")
    @Excel(name = "年龄")
    private Long age;

    /** 性别 */
    @ApiModelProperty("性别")
    @Excel(name = "性别")
    private String gender;

    /** 生日 */
    @ApiModelProperty("生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 健康状况 */
    @ApiModelProperty("健康状况")
    @Excel(name = "健康状况")
    private String healthStatus;

    /** 用药史 */
    @ApiModelProperty("用药史")
    @Excel(name = "用药史")
    private String medicationHistory;

    /** 沟通经历 */
    @ApiModelProperty("沟通经历")
    @Excel(name = "沟通经历")
    private String communicationExperience;

    /** 家属状况 */
    @ApiModelProperty("家属状况")
    @Excel(name = "家属状况")
    private String familyStatus;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("age", getAge())
            .append("gender", getGender())
            .append("birthday", getBirthday())
            .append("healthStatus", getHealthStatus())
            .append("medicationHistory", getMedicationHistory())
            .append("communicationExperience", getCommunicationExperience())
            .append("familyStatus", getFamilyStatus())
            .toString();
    }
}
