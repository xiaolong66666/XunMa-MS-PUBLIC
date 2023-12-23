package com.xunma.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String orderType;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 图片信息 */
    @Excel(name = "图片信息")
    private String images;

    /** 文件信息 */
    @Excel(name = "文件信息")
    private String files;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 是否过期 */
    @Excel(name = "是否过期")
    private String isOverdue;

    /** 是否删除 0-否 1-是 */
    private String isDelete;

    /** 截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setTakeName(String takeName) 
    {
        this.takeName = takeName;
    }

    public String getTakeName() 
    {
        return takeName;
    }
    public void setOrderType(String orderType) 
    {
        this.orderType = orderType;
    }

    public String getOrderType() 
    {
        return orderType;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setImages(String images) 
    {
        this.images = images;
    }

    public String getImages() 
    {
        return images;
    }
    public void setFiles(String files) 
    {
        this.files = files;
    }

    public String getFiles() 
    {
        return files;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setIsOverdue(String isOverdue) 
    {
        this.isOverdue = isOverdue;
    }

    public String getIsOverdue() 
    {
        return isOverdue;
    }
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
    }
    public void setDeadline(Date deadline) 
    {
        this.deadline = deadline;
    }

    public Date getDeadline() 
    {
        return deadline;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerName", getCustomerName())
            .append("takeName", getTakeName())
            .append("orderType", getOrderType())
            .append("status", getStatus())
            .append("amount", getAmount())
            .append("images", getImages())
            .append("files", getFiles())
            .append("description", getDescription())
            .append("isOverdue", getIsOverdue())
            .append("isDelete", getIsDelete())
            .append("deadline", getDeadline())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
