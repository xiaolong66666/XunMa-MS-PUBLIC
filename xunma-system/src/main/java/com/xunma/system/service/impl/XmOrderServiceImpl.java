package com.xunma.system.service.impl;

import java.util.List;
import com.xunma.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xunma.system.mapper.XmOrderMapper;
import com.xunma.system.domain.XmOrder;
import com.xunma.system.service.IXmOrderService;

/**
 * 订单Service业务层处理
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
@Service
public class XmOrderServiceImpl implements IXmOrderService 
{
    @Autowired
    private XmOrderMapper xmOrderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public XmOrder selectXmOrderById(Long id)
    {
        return xmOrderMapper.selectXmOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param xmOrder 订单
     * @return 订单
     */
    @Override
    public List<XmOrder> selectXmOrderList(XmOrder xmOrder)
    {
        return xmOrderMapper.selectXmOrderList(xmOrder);
    }

    /**
     * 新增订单
     * 
     * @param xmOrder 订单
     * @return 结果
     */
    @Override
    public int insertXmOrder(XmOrder xmOrder)
    {
        xmOrder.setCreateTime(DateUtils.getNowDate());
        return xmOrderMapper.insertXmOrder(xmOrder);
    }

    /**
     * 修改订单
     * 
     * @param xmOrder 订单
     * @return 结果
     */
    @Override
    public int updateXmOrder(XmOrder xmOrder)
    {
        xmOrder.setUpdateTime(DateUtils.getNowDate());
        return xmOrderMapper.updateXmOrder(xmOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteXmOrderByIds(Long[] ids)
    {
        return xmOrderMapper.deleteXmOrderByIds(ids);
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteXmOrderById(Long id)
    {
        return xmOrderMapper.deleteXmOrderById(id);
    }
}
