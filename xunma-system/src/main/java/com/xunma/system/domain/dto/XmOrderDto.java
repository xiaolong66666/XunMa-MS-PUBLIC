package com.xunma.system.domain.dto;
import com.xunma.system.domain.Resource;
import com.xunma.system.domain.XmOrder;
import lombok.Data;
import java.util.List;

@Data
public class XmOrderDto extends XmOrder {
    private List<Resource> resourceList;
}
