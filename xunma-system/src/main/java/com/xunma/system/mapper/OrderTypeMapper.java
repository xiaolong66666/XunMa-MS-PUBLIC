package com.xunma.system.mapper;

import java.util.List;
import com.xunma.system.domain.OrderType;

/**
 * 订单类型Mapper接口
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
public interface OrderTypeMapper 
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
    public int insertOrderType(OrderType orderType);

    /**
     * 修改订单类型
     * 
     * @param orderType 订单类型
     * @return 结果
     */
    public int updateOrderType(OrderType orderType);

    /**
     * 删除订单类型
     * 
     * @param id 订单类型主键
     * @return 结果
     */
    public int deleteOrderTypeById(Long id);

    /**
     * 批量删除订单类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderTypeByIds(Long[] ids);
}
