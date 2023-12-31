package com.xunma.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
