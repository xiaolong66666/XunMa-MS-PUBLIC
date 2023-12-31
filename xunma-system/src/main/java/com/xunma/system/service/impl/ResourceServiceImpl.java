package com.xunma.system.service.impl;

import java.util.List;

import com.xunma.common.core.domain.AjaxResult;
import com.xunma.common.utils.DateUtils;
import com.xunma.common.utils.SecurityUtils;
import com.xunma.common.utils.minio.MinioUtils;
import com.xunma.system.domain.XmOrder;
import com.xunma.system.mapper.XmOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xunma.system.mapper.ResourceMapper;
import com.xunma.system.domain.Resource;
import com.xunma.system.service.IResourceService;

/**
 * 资源管理Service业务层处理
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
@Service
public class ResourceServiceImpl implements IResourceService 
{
    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private XmOrderMapper xmOrderMapper;

    /**
     * 查询资源管理
     * 
     * @param id 资源管理主键
     * @return 资源管理
     */
    @Override
    public Resource selectResourceById(Long id)
    {
        return resourceMapper.selectResourceById(id);
    }

    /**
     * 查询资源管理列表
     * 
     * @param resource 资源管理
     * @return 资源管理
     */
    @Override
    public List<Resource> selectResourceList(Resource resource)
    {
        return resourceMapper.selectResourceList(resource);
    }

    /**
     * 新增资源管理
     * 
     * @param resource 资源管理
     * @return 结果
     */
    @Override
    public AjaxResult insertResource(Resource resource)
    {
        //校验订单编号是否存在
        Long id = resource.getOrderId();
        if(id != null){
            XmOrder xmOrder = xmOrderMapper.selectXmOrderById(id);
            if(xmOrder == null){
                return AjaxResult.error("订单编号不存在");
            }
        }
        //填写参数
        String url = resource.getUrl();
        if(url == null || url.equals("")){
            return AjaxResult.error("资源不能为空");
        }
        String type = MinioUtils.getResourceTypeByUrl(url);
        resource.setType(type);
        if (resource.getName()==null || resource.getName().equals("")){
            resource.setName(MinioUtils.getFileNameByUrl(url));
        }
        resource.setCreateBy(SecurityUtils.getUsername());
        resource.setUpdateBy(SecurityUtils.getUsername());
        resource.setCreateTime(DateUtils.getNowDate());
        resource.setUpdateTime(DateUtils.getNowDate());
        return AjaxResult.success(resourceMapper.insertResource(resource));
    }

    /**
     * 修改资源管理
     * 
     * @param resource 资源管理
     * @return 结果
     */
    @Override
    public int updateResource(Resource resource)
    {
        //填写参数
        String url = resource.getUrl();
        if(url == null || url.equals("")){
            return 0;
        }
        String type = MinioUtils.getResourceTypeByUrl(url);
        resource.setType(type);
        if (resource.getName()==null || resource.getName().equals("")){
            resource.setName(MinioUtils.getFileNameByUrl(url));
        }
        resource.setUpdateBy(SecurityUtils.getUsername());
        resource.setUpdateTime(DateUtils.getNowDate());
        return resourceMapper.updateResource(resource);
    }

    /**
     * 批量删除资源管理
     * 
     * @param ids 需要删除的资源管理主键
     * @return 结果
     */
    @Override
    public int deleteResourceByIds(Long[] ids)
    {
        return resourceMapper.deleteResourceByIds(ids);
    }

    /**
     * 删除资源管理信息
     * 
     * @param id 资源管理主键
     * @return 结果
     */
    @Override
    public int deleteResourceById(Long id)
    {
        return resourceMapper.deleteResourceById(id);
    }

    @Override
    public void deleteResourceByOrderId(Long id) {
        resourceMapper.deleteResourceByOrderId(id);
    }
}
