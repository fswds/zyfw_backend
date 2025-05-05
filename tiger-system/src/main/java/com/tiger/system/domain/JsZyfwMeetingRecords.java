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
 * 会议记录对象 js_zyfw_meeting_records
 * 
 * @author wds
 * @date 2025-04-28
 */
@Data
@TableName(value = "js_zyfw_meeting_records")
@ApiModel(value = "会议记录")
public class JsZyfwMeetingRecords extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("id")
    private Long id;

    /** 所属会议 */
    @ApiModelProperty("所属会议")
    @Excel(name = "所属会议")
    private Long meetingId;

    /** 会议内容 */
    @ApiModelProperty("会议内容")
    @Excel(name = "会议内容")
    private String content;

    /** 会议结果 */
    @ApiModelProperty("会议结果")
    @Excel(name = "会议结果")
    private String result;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("meetingId", getMeetingId())
            .append("content", getContent())
            .append("result", getResult())
            .toString();
    }
}
