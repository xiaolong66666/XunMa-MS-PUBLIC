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
import com.xunma.system.domain.OrderType;
import com.xunma.system.service.IOrderTypeService;
import com.xunma.common.utils.poi.ExcelUtil;
import com.xunma.common.core.page.TableDataInfo;

/**
 * 订单类型Controller
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
@RestController
@RequestMapping("/system/type")
public class OrderTypeController extends BaseController
{
    @Autowired
    private IOrderTypeService orderTypeService;

    /**
     * 查询订单类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrderType orderType)
    {
        startPage();
        List<OrderType> list = orderTypeService.selectOrderTypeList(orderType);
        return getDataTable(list);
    }

    /**
     * 导出订单类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:export')")
    @Log(title = "订单类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderType orderType)
    {
        List<OrderType> list = orderTypeService.selectOrderTypeList(orderType);
        ExcelUtil<OrderType> util = new ExcelUtil<OrderType>(OrderType.class);
        util.exportExcel(response, list, "订单类型数据");
    }

    /**
     * 获取订单类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(orderTypeService.selectOrderTypeById(id));
    }

    /**
     * 新增订单类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:add')")
    @Log(title = "订单类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderType orderType)
    {
        return orderTypeService.insertOrderType(orderType);
    }

    /**
     * 修改订单类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:edit')")
    @Log(title = "订单类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderType orderType)
    {
        return orderTypeService.updateOrderType(orderType);
    }

    /**
     * 删除订单类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:remove')")
    @Log(title = "订单类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderTypeService.deleteOrderTypeByIds(ids));
    }
}
