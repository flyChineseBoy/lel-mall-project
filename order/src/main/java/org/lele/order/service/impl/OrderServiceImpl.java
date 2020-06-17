package org.lele.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.assertj.core.util.Lists;
import org.lele.common.dto.CommonResult;
import org.lele.common.entity.product.ProductSpecs;
import org.lele.common.security.SessionUtils;
import org.lele.order.constants.OrderConstants;
import org.lele.order.dao.OrderDao;
import org.lele.common.entity.order.MOrder;
import org.lele.common.entity.order.OrderItem;
import org.lele.common.dto.OrderDTO;
import org.lele.order.feign.product.ProductService;
import org.lele.order.mq.producer.CreateOrderProducer;
import org.lele.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 订单总表(MOrder)表服务实现类
 *
 * @author lele
 * @since 2020-06-13 20:45:40
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, MOrder> implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private SessionUtils sessionUtils;
    @Autowired
    private CreateOrderProducer createOrderProducer;

    @Override
    public int cancel(Long orderId) {
        MOrder order = new MOrder();
        order.setId( orderId );
        order.setStatus( OrderConstants.Status.CANCEL.name() );

        // 发送消息到队列，取消库存，TODO 未来引入优惠券返还。

        return this.updateById( order )?1:0;
    }

    @Override
    public int placeOrder(Long productSpecId) {
        CommonResult<ProductSpecs> o = productService.selectOneProductSpecs(productSpecId);
        ProductSpecs productSpecs = o.getData();

        // TODO 后期引入优惠券、满减折算
        MOrder order = MOrder.builder()
                .status(OrderConstants.Status.UNCHECK.name())
                .payable( productSpecs.getPrice() )
                .price( productSpecs.getPrice() )
                .applyUser( sessionUtils.getCurrentUser().getUsername() )
                .build();
        save( order );
        OrderItem item = OrderItem.builder()
                .orderId( order.getId() )
                .productSpecsId( productSpecId )
                .payable( productSpecs.getPrice() )
                .price( productSpecs.getPrice() )
                .build();
        OrderDTO orderDTO = OrderDTO.builder()
                .order( order )
                .orderItemList(Lists.newArrayList( item ) )
                .build();
        // 发送事务消息，需要保证消息一定被消费。
        createOrderProducer.sendSyncMessage( orderDTO,"asd" );
        // TODO 发送延时消息，取消订单

        return 0;
    }
}