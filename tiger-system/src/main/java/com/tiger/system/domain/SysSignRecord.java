package com.tiger.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 签到记录对象 sys_sign_record
 * 
 * @author tiger
 * @date 2024-04-28
 */
@Data
@TableName(value = "sys_sign_record")
@ApiModel(value = "签到记录")
public class SysSignRecord extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** 签到记录id */
    @ApiModelProperty("签到记录id")
    @TableId(type = IdType.AUTO)
    // 或者我数据库改下也可以
    private Long id;

    /** 活动id */
    @ApiModelProperty("活动id")
    @NotNull(message = "活动id不能为空")
    @Excel(name = "活动id")
    private Long activityId;

    /** 用户id */
    @ApiModelProperty("用户id")
    @NotNull(message = "用户id不能为空")
    @Excel(name = "用户id")
    private Long userId;
    // 你不要存了，我来加
    @TableField(exist = false)
    private String userName;

    /** 签到事件 */
    @ApiModelProperty("签到时间")
    // @NotNull(message = "签到事件不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到事件", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signDate;

    /** 签到审核状态 */
    @ApiModelProperty("签到审核状态")
    @NotBlank(message = "签到审核状态不能为空")
    @Excel(name = "签到审核状态")
    private String examineStatus;

    /** 签到图片 */
    @ApiModelProperty("签到图片")
    @NotBlank(message = "签到图片不能为空")
    @Excel(name = "签到图片")
    private String examineImg;

    /** 扩展字段 */
    @ApiModelProperty("扩展字段")
    @Excel(name = "扩展字段")
    private String extra;

    @ApiModelProperty("签到通知id")
    @Excel(name = "签到通知id")
    private Integer signNoticeId;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("activityId", getActivityId())
            .append("userId", getUserId())
            .append("signDate", getSignDate())
            .append("examineStatus", getExamineStatus())
            .append("examineImg", getExamineImg())
            .append("extra", getExtra())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
