package com.xunma.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xunma.common.annotation.Log;
import com.xunma.common.core.controller.BaseController;
import com.xunma.common.core.domain.AjaxResult;
import com.xunma.common.enums.BusinessType;
import com.xunma.system.domain.Resource;
import com.xunma.system.service.IResourceService;
import com.xunma.common.utils.poi.ExcelUtil;
import com.xunma.common.core.page.TableDataInfo;

/**
 * 资源管理Controller
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
@RestController
@RequestMapping("/system/resource")
public class ResourceController extends BaseController
{
    @Autowired
    private IResourceService resourceService;

    /**
     * 查询资源管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:resource:list')")
    @GetMapping("/list")
    public TableDataInfo list(Resource resource)
    {
        startPage();
        List<Resource> list = resourceService.selectResourceList(resource);
        return getDataTable(list);
    }

    /**
     * 导出资源管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:resource:export')")
    @Log(title = "资源管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Resource resource)
    {
        List<Resource> list = resourceService.selectResourceList(resource);
        ExcelUtil<Resource> util = new ExcelUtil<Resource>(Resource.class);
        util.exportExcel(response, list, "资源管理数据");
    }

    /**
     * 获取资源管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:resource:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(resourceService.selectResourceById(id));
    }

    /**
     * 新增资源管理
     */
    @PreAuthorize("@ss.hasPermi('system:resource:add')")
    @Log(title = "资源管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Resource resource)
    {
        return resourceService.insertResource(resource);
    }

    /**
     * 修改资源管理
     */
    @PreAuthorize("@ss.hasPermi('system:resource:edit')")
    @Log(title = "资源管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Resource resource)
    {
        return toAjax(resourceService.updateResource(resource));
    }

    /**
     * 删除资源管理
     */
    @PreAuthorize("@ss.hasPermi('system:resource:remove')")
    @Log(title = "资源管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(resourceService.deleteResourceByIds(ids));
    }
}
