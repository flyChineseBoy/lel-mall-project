package org.lele.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.lele.common.entity.order.MOrder;

public interface OrderService extends IService<MOrder> {

    /**
     * @param orderId
     * @return 成功为1，失败为0
     */
    public int cancel( Long orderId );

    public int placeOrder( Long productId );
}