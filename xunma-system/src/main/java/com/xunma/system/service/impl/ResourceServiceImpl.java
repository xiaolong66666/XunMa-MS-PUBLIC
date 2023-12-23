package com.xunma.system.service.impl;

import java.util.List;
import com.xunma.common.utils.DateUtils;
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
    public int insertResource(Resource resource)
    {
        resource.setCreateTime(DateUtils.getNowDate());
        return resourceMapper.insertResource(resource);
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
}
