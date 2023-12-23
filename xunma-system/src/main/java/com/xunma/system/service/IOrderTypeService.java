package com.xunma.system.service;

import java.util.List;

import com.xunma.common.core.domain.AjaxResult;
import com.xunma.system.domain.OrderType;

/**
 * 订单类型Service接口
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
public interface IOrderTypeService 
{
    /**
     * 查询订单类型
     * 
     * @param id 订单类型主键
     * @return 订单类型
     */
    public OrderType selectOrderTypeById(Long id);

    /**
     * 查询订单类型列表
     * 
     * @param orderType 订单类型
     * @return 订单类型集合
     */
    public List<OrderType> selectOrderTypeList(OrderType orderType);

    /**
     * 新增订单类型
     * 
     * @param orderType 订单类型
     * @return 结果
     */
    public AjaxResult insertOrderType(OrderType orderType);

    /**
     * 修改订单类型
     * 
     * @param orderType 订单类型
     * @return 结果
     */
    public AjaxResult updateOrderType(OrderType orderType);

    /**
     * 批量删除订单类型
     * 
     * @param ids 需要删除的订单类型主键集合
     * @return 结果
     */
    public int deleteOrderTypeByIds(Long[] ids);

    /**
     * 删除订单类型信息
     * 
     * @param id 订单类型主键
     * @return 结果
     */
    public int deleteOrderTypeById(Long id);
}
