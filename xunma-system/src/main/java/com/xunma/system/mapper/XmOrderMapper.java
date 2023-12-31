package com.xunma.system.mapper;

import java.util.List;
import com.xunma.system.domain.XmOrder;
import com.xunma.system.domain.dto.XmOrderDto;

/**
 * 订单Mapper接口
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
public interface XmOrderMapper 
{
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    public XmOrderDto selectXmOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param xmOrder 订单
     * @return 订单集合
     */
    public List<XmOrderDto> selectXmOrderList(XmOrder xmOrder);

    /**
     * 新增订单
     * 
     * @param xmOrder 订单
     * @return 结果
     */
    public int insertXmOrder(XmOrder xmOrder);

    /**
     * 修改订单
     * 
     * @param xmOrder 订单
     * @return 结果
     */
    public int updateXmOrder(XmOrder xmOrder);

    /**
     * 删除订单
     * 
     * @param id 订单主键
     * @return 结果
     */
    public int deleteXmOrderById(Long id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXmOrderByIds(Long[] ids);
}
