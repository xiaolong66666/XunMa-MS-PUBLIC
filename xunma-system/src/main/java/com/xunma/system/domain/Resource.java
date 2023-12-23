package com.xunma.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xunma.common.annotation.Excel;
import com.xunma.common.core.domain.BaseEntity;

/**
 * 资源管理对象 resource
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
public class Resource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资源编号 */
    @Excel(name = "资源编号")
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private Long orderId;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String name;

    /** 资源类型 */
    @Excel(name = "资源类型")
    private String type;

    /** 资源地址 */
    @Excel(name = "资源地址")
    private String url;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("name", getName())
            .append("type", getType())
            .append("url", getUrl())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
