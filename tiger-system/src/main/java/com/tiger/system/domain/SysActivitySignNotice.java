package com.tiger.system.domain;

import java.util.Date;
import java.util.List;

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
 * 签到通知公告对象 sys_activity_sign_notice
 * 
 * @author tiger
 * @date 2024-04-30
 */
@Data
@TableName(value = "sys_activity_sign_notice")
@ApiModel(value = "签到通知公告")
public class SysActivitySignNotice extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    @ApiModelProperty("公告ID")
    @TableId(type = IdType.AUTO)
    private Integer noticeId;

    /** 公告标题 */
    @ApiModelProperty("公告标题")
    @NotBlank(message = "公告标题不能为空")
    @Excel(name = "公告标题")
    private String noticeTitle;

    /** 公告类型（1通知 2公告） */
    @ApiModelProperty("公告类型（1通知 2公告）")
    @NotBlank(message = "公告类型（1通知 2公告）不能为空")
    @Excel(name = "公告类型", readConverterExp = "1=通知,2=公告")
    private String noticeType;

    /** 公告内容 */
    @ApiModelProperty("公告内容")
    @Excel(name = "公告内容")
    private String noticeContent;

    /** 公告状态（0正常 1关闭） */
    @ApiModelProperty("公告状态（0正常 1关闭）")
    @Excel(name = "公告状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /** 签到开始时间 */
    @ApiModelProperty("签到开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 签到结束时间 */
    @ApiModelProperty("签到结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 每次签到奖励积分 */
    @ApiModelProperty("每次签到奖励积分")
    @Excel(name = "每次签到奖励积分")
    private Integer signScore;

    /** 扩展字段 */
    @ApiModelProperty("扩展字段")
    @Excel(name = "扩展字段")
    private String extra;

    /** 活动id */
    @ApiModelProperty("活动id")
    @NotNull(message = "活动id不能为空")
    @Excel(name = "活动id")
    private Long activityId;

    @TableField(exist = false)
    private String activityName;

    @TableField(exist = false)
    private Long userId;

    @TableField(exist = false)
    private List<Long> activityIdList;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("noticeId", getNoticeId())
            .append("noticeTitle", getNoticeTitle())
            .append("noticeType", getNoticeType())
            .append("noticeContent", getNoticeContent())
            .append("status", getStatus())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("signScore", getSignScore())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .append("extra", getExtra())
            .append("activityId", getActivityId())
            .toString();
    }
}
