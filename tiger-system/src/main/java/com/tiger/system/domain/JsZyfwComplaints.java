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
 * 投诉反馈对象 js_zyfw_complaints
 * 
 * @author tiger
 * @date 2025-04-28
 */
@Data
@TableName(value = "js_zyfw_complaints")
@ApiModel(value = "投诉反馈")
public class JsZyfwComplaints extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty("$column.columnComment")
    private Long id;

    /** 投诉人 */
    @ApiModelProperty("投诉人")
    @Excel(name = "投诉人")
    private Long volunteerId;

    /** 投诉内容 */
    @ApiModelProperty("投诉内容")
    @Excel(name = "投诉内容")
    private String complaintContent;

    /** 投诉时间 */
    @ApiModelProperty("投诉时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投诉时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date complaintTime;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("volunteerId", getVolunteerId())
            .append("complaintContent", getComplaintContent())
            .append("complaintTime", getComplaintTime())
            .toString();
    }
}
