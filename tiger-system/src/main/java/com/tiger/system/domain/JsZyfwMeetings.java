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
 * 会议对象 js_zyfw_meetings
 * 
 * @author wds
 * @date 2025-04-28
 */
@Data
@TableName(value = "js_zyfw_meetings")
@ApiModel(value = "会议")
public class JsZyfwMeetings extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /**  */
    @ApiModelProperty("")
    private Long id;

    /** 会议名 */
    @ApiModelProperty("会议名")
    @NotBlank(message = "会议名不能为空")
    @Excel(name = "会议名")
    private String name;

    /** 开始时间 */
    @ApiModelProperty("开始时间")
    @NotNull(message = "开始时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @ApiModelProperty("结束时间")
    @NotNull(message = "结束时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 地址 */
    @ApiModelProperty("地址")
    @Excel(name = "地址")
    private String address;

    /** 所属组织 */
    @ApiModelProperty("所属组织")
    @NotNull(message = "所属组织不能为空")
    @Excel(name = "所属组织")
    private Long organizationId;

    /** 主持人姓名 */
    @ApiModelProperty("主持人姓名")
    @NotBlank(message = "主持人姓名不能为空")
    @Excel(name = "主持人姓名")
    private String leaderName;

    /** 参会人数 */
    @ApiModelProperty("参会人数")
    @NotBlank(message = "参会人数不能为空")
    @Excel(name = "参会人数")
    private String leaderNumber;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("address", getAddress())
            .append("organizationId", getOrganizationId())
            .append("leaderName", getLeaderName())
            .append("leaderNumber", getLeaderNumber())
            .toString();
    }
}
