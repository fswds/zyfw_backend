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
 * 护理人员信息管理对象 js_zyfw_hospital_caregivers
 * 
 * @author wds
 * @date 2025-04-28
 */
@Data
@TableName(value = "js_zyfw_hospital_caregivers")
@ApiModel(value = "护理人员信息管理")
public class JsZyfwHospitalCaregivers extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** 性别 */
    @ApiModelProperty("性别")
    @Excel(name = "性别")
    private String gender;

    /** $column.columnComment */
    @ApiModelProperty("$column.columnComment")
    private Long id;

    /** 年龄 */
    @ApiModelProperty("年龄")
    @Excel(name = "年龄")
    private Long age;

    /** 个人信息 */
    @ApiModelProperty("个人信息")
    @Excel(name = "个人信息")
    private String contactInfo;

    /** 所属机构 */
    @ApiModelProperty("所属机构")
    @Excel(name = "所属机构")
    private String institution;

    /** 工作经历 */
    @ApiModelProperty("工作经历")
    @Excel(name = "工作经历")
    private String workExperience;

    /** 家庭住址 */
    @ApiModelProperty("家庭住址")
    @Excel(name = "家庭住址")
    private String address;

    /** 联系方式 */
    @ApiModelProperty("联系方式")
    @Excel(name = "联系方式")
    private String phone;

    /** 姓名 */
    @ApiModelProperty("姓名")
    @Excel(name = "姓名")
    private String name;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("gender", getGender())
            .append("id", getId())
            .append("age", getAge())
            .append("contactInfo", getContactInfo())
            .append("institution", getInstitution())
            .append("workExperience", getWorkExperience())
            .append("address", getAddress())
            .append("phone", getPhone())
            .append("name", getName())
            .toString();
    }
}
