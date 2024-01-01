package com.xunma.system.service.impl;

import java.util.List;

import com.xunma.common.core.domain.AjaxResult;
import com.xunma.common.utils.DateUtils;
import com.xunma.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xunma.system.mapper.OrderTypeMapper;
import com.xunma.system.domain.OrderType;
import com.xunma.system.service.IOrderTypeService;

/**
 * 订单类型Service业务层处理
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
@Service
public class OrderTypeServiceImpl implements IOrderTypeService 
{
    @Autowired
    private OrderTypeMapper orderTypeMapper;

    /**
     * 查询订单类型
     * 
     * @param id 订单类型主键
     * @return 订单类型
     */
    @Override
    public OrderType selectOrderTypeById(Long id)
    {
        return orderTypeMapper.selectOrderTypeById(id);
    }

    /**
     * 查询订单类型列表
     * 
     * @param orderType 订单类型
     * @return 订单类型
     */
    @Override
    public List<OrderType> selectOrderTypeList(OrderType orderType)
    {
        return orderTypeMapper.selectOrderTypeList(orderType);
    }

    /**
     * 新增订单类型
     * 
     * @param orderType 订单类型
     * @return 结果
     */
    @Override
    public AjaxResult insertOrderType(OrderType orderType)
    {
        OrderType type = new OrderType();
        type.setName(orderType.getName());
        //判断类型是否存在
        List<OrderType> orderTypes = orderTypeMapper.selectOrderTypeList(type);
        if(orderTypes.size()>0){
            return AjaxResult.error("类型已存在,请勿重复添加");
        }
        orderType.setCreateBy(SecurityUtils.getUsername());
        orderType.setUpdateBy(SecurityUtils.getUsername());
        orderType.setCreateTime(DateUtils.getNowDate());
        orderType.setUpdateTime(DateUtils.getNowDate());
        return AjaxResult.success(orderTypeMapper.insertOrderType(orderType));
    }

    /**
     * 修改订单类型
     * 
     * @param orderType 订单类型
     * @return 结果
     */
    @Override
    public AjaxResult updateOrderType(OrderType orderType)
    {
        OrderType type = new OrderType();
        type.setName(orderType.getName());
        //判断类型是否存在
        List<OrderType> orderTypes = orderTypeMapper.selectOrderTypeList(type);
        if(orderTypes.size()>0){
            return AjaxResult.error("类型已存在,无法修改");
        }
        orderType.setUpdateBy(SecurityUtils.getUsername());
        orderType.setUpdateTime(DateUtils.getNowDate());
        orderType.setUpdateTime(DateUtils.getNowDate());
        return AjaxResult.success(orderTypeMapper.updateOrderType(orderType));
    }

    /**
     * 批量删除订单类型
     * 
     * @param ids 需要删除的订单类型主键
     * @return 结果
     */
    @Override
    public int deleteOrderTypeByIds(Long[] ids)
    {
        return orderTypeMapper.deleteOrderTypeByIds(ids);
    }

    /**
     * 删除订单类型信息
     * 
     * @param id 订单类型主键
     * @return 结果
     */
    @Override
    public int deleteOrderTypeById(Long id)
    {
        return orderTypeMapper.deleteOrderTypeById(id);
    }
}
