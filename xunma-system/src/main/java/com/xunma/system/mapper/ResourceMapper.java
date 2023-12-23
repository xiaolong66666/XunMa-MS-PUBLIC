package com.xunma.system.mapper;

import java.util.List;
import com.xunma.system.domain.Resource;

/**
 * 资源管理Mapper接口
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
public interface ResourceMapper 
{
    /**
     * 查询资源管理
     * 
     * @param id 资源管理主键
     * @return 资源管理
     */
    public Resource selectResourceById(Long id);

    /**
     * 查询资源管理列表
     * 
     * @param resource 资源管理
     * @return 资源管理集合
     */
    public List<Resource> selectResourceList(Resource resource);

    /**
     * 新增资源管理
     * 
     * @param resource 资源管理
     * @return 结果
     */
    public int insertResource(Resource resource);

    /**
     * 修改资源管理
     * 
     * @param resource 资源管理
     * @return 结果
     */
    public int updateResource(Resource resource);

    /**
     * 删除资源管理
     * 
     * @param id 资源管理主键
     * @return 结果
     */
    public int deleteResourceById(Long id);

    /**
     * 批量删除资源管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResourceByIds(Long[] ids);
}
