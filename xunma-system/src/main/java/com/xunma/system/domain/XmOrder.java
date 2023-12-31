package com.xunma.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xunma.common.annotation.Excel;
import com.xunma.common.core.domain.BaseEntity;

/**
 * 订单对象 xm_order
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
@Data
public class XmOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private Long id;

    /** 客户称呼 */
    @Excel(name = "客户称呼")
    private String customerName;

    /** 技术称呼 */
    @Excel(name = "技术称呼")
    private String takeName;

    /** 订单类型 */
    @Excel(name = "订单类型")
    private Long orderTypeId;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 文件信息 */
    @Excel(name = "文件信息")
    private String files;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 是否删除 0-否 1-是 */
    private String isDelete;

    /** 截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;
}
