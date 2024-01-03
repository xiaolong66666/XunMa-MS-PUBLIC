package com.xunma.system.service.impl;

import java.util.List;

import cn.hutool.json.JSONUtil;
import com.xunma.common.constant.CommonConstants;
import com.xunma.common.core.domain.AjaxResult;
import com.xunma.common.core.domain.entity.SysUser;
import com.xunma.common.enums.TimeType;
import com.xunma.common.utils.DateUtils;
import com.xunma.common.utils.SecurityUtils;
import com.xunma.common.utils.StringUtils;
import com.xunma.common.utils.minio.MinioUtils;
import com.xunma.system.domain.Resource;
import com.xunma.system.domain.dto.XmOrderDto;
import com.xunma.system.service.IResourceService;
import com.xunma.system.service.ISysUserService;
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
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private RabbitmqService rabbitmqService;
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
        saveResources(xmOrder);
        log.info("订单添加成功，订单信息：{}",xmOrder);
        //发送延迟消息,2天后自动回收订单
        rabbitmqService.sendDeLayMessage(CommonConstants.DELAY_CHANGE_ORDER_STATUS_TASK, String.valueOf(xmOrder.getId()),30,TimeType.SECOND);
        //向所有员工异步发送邮件通知
        rabbitmqService.sendDeLayMessage(CommonConstants.SEND_EMAIL_TASK,"",0,TimeType.MILLISECOND);
        return AjaxResult.success("订单添加成功");
    }

    private void saveResources(XmOrder xmOrder) {
        //获取订单id
        Long orderId = xmOrder.getId();
        //校验资源是否为空
        if (StringUtils.isEmpty(xmOrder.getFiles())){
            return;
        }
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
    }

    /**
     * 修改订单
     * 
     * @param xmOrder 订单
     * @return 结果
     */
    @Transactional
    @Override
    public int updateXmOrder(XmOrder xmOrder)
    {
        //对订单相关资源采取
        Long id = xmOrder.getId();
        //删除原有资源
        resourceService.deleteResourceByOrderId(id);
        //保存上传资源
        xmOrder.setUpdateTime(DateUtils.getNowDate());
        xmOrder.setUpdateBy(SecurityUtils.getUsername());
        //保存更新资源
        saveResources(xmOrder);
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
