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
import com.xunma.system.domain.XmOrder;
import com.xunma.system.service.IXmOrderService;
import com.xunma.common.utils.poi.ExcelUtil;
import com.xunma.common.core.page.TableDataInfo;

/**
 * 订单Controller
 * 
 * @author xiaolong
 * @date 2023-12-23
 */
@RestController
@RequestMapping("/system/order")
public class XmOrderController extends BaseController
{
    @Autowired
    private IXmOrderService xmOrderService;

    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(XmOrder xmOrder)
    {
        startPage();
        List<XmOrder> list = xmOrderService.selectXmOrderList(xmOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XmOrder xmOrder)
    {
        List<XmOrder> list = xmOrderService.selectXmOrderList(xmOrder);
        ExcelUtil<XmOrder> util = new ExcelUtil<XmOrder>(XmOrder.class);
        util.exportExcel(response, list, "订单数据");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(xmOrderService.selectXmOrderById(id));
    }

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XmOrder xmOrder)
    {
        return toAjax(xmOrderService.insertXmOrder(xmOrder));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XmOrder xmOrder)
    {
        return toAjax(xmOrderService.updateXmOrder(xmOrder));
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xmOrderService.deleteXmOrderByIds(ids));
    }
}
