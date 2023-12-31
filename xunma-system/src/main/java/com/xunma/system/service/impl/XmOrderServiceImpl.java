package com.xunma.system.service.impl;

import java.util.List;

import com.xunma.common.core.domain.AjaxResult;
import com.xunma.common.utils.DateUtils;
import com.xunma.common.utils.SecurityUtils;
import com.xunma.common.utils.minio.MinioUtils;
import com.xunma.system.domain.Resource;
import com.xunma.system.domain.dto.XmOrderDto;
import com.xunma.system.service.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xunma.system.mapper.XmOrderMapper;
import com.xunma.system.domain.XmOrder;
import com.xunma.system.service.IXmOrderService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单Service业务层处理
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
@Slf4j
@Service
public class XmOrderServiceImpl implements IXmOrderService 
{
    @Autowired
    private XmOrderMapper xmOrderMapper;
    @Autowired
    private IResourceService resourceService;
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public XmOrderDto selectXmOrderById(Long id)
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
    public List<XmOrderDto> selectXmOrderList(XmOrder xmOrder)
    {
        return xmOrderMapper.selectXmOrderList(xmOrder);
    }

    /**
     * 新增订单
     * 
     * @param xmOrder 订单
     * @return 结果
     */
    @Transactional
    @Override
    public AjaxResult insertXmOrder(XmOrder xmOrder)
    {
        //添加参数
        xmOrder.setCreateBy(SecurityUtils.getUsername());
        xmOrder.setUpdateBy(SecurityUtils.getUsername());
        xmOrder.setUpdateTime(DateUtils.getNowDate());
        xmOrder.setCreateTime(DateUtils.getNowDate());
        int i = xmOrderMapper.insertXmOrder(xmOrder);
        if (i < 1){
            return AjaxResult.error("订单添加失败");
        }
        //保存上传资源
        //获取订单id
        Long orderId = xmOrder.getId();
        String[] urls = xmOrder.getFiles().split(",");
        for (String url : urls) {
            Resource resource = new Resource();
            resource.setUrl(url);
            resource.setName(MinioUtils.getFileNameByUrl(url));
            resource.setOrderId(orderId);
            resource.setType(MinioUtils.getResourceTypeByUrl(url));
            resource.setCreateBy(SecurityUtils.getUsername());
            resource.setUpdateBy(SecurityUtils.getUsername());
            resource.setUpdateTime(DateUtils.getNowDate());
            resource.setCreateTime(DateUtils.getNowDate());
            resourceService.insertResource(resource);
        }
        log.info("订单添加成功，订单信息：{}",xmOrder);
        return AjaxResult.success("订单添加成功");
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
