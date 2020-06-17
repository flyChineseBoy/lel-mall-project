package org.lele.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lele.order.dao.OrderItemDao;
import org.lele.common.entity.order.OrderItem;
import org.lele.order.service.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * (OrderItem)表服务实现类
 *
 * @author lele
 * @since 2020-06-13 20:45:40
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItem> implements OrderItemService {

}