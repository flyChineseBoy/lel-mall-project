package org.lele.order.mq.producer;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.lele.common.entity.order.MOrder;
import org.lele.common.entity.order.OrderItem;
import org.lele.order.constants.OrderConstants;
import org.lele.common.dto.OrderDTO;
import org.lele.order.service.OrderItemService;
import org.lele.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * org.lele.order.mq.producer
 *
 * @author: lele
 * @date: 2020-06-14
 */
@Component
public class CreateOrderProducer implements InitializingBean {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    private Logger logger = LoggerFactory.getLogger( CreateOrderProducer.class );

    @Override
    public void afterPropertiesSet() throws Exception {
        // 对bean属性赋值之后时的一些操作
    }


    public TransactionSendResult sendSyncMessage(OrderDTO msg, String tag){
        logger.info("【发送消息】：{}", msg);
        //构建消息体
        Message<String> message = MessageBuilder
                .withPayload( JSONObject.toJSONString(msg) )
                .build();
        TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction("tx_create_order", "CreateOrder", message,tag);
        logger.info("【发送状态】：{}", result.getLocalTransactionState());
        return result;
    }


    /**
     * 定义tx_create_order类事务消息的 commit函数、回查函数
     */
    @RocketMQTransactionListener(txProducerGroup = "tx_create_order")
    class CreateOrderTransactionListener implements RocketMQLocalTransactionListener {

        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
            OrderDTO dto = JSONObject.parseObject( new String((byte[])message.getPayload()),OrderDTO.class );
            List<OrderItem> items = dto.getOrderItemList();

            dto.getOrder().setStatus( OrderConstants.Status.START.name() );
            orderService.updateById( dto.getOrder() );

            items.forEach( orderItem -> orderItemService.save(orderItem) );
            //return RocketMQLocalTransactionState.COMMIT;
            return RocketMQLocalTransactionState.COMMIT;
        }

        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
            OrderDTO dto = JSONObject.parseObject( new String((byte[])message.getPayload()),OrderDTO.class );
            MOrder order = orderService.getById( dto.getOrder().getId() );
            if( order!=null && order.getStatus().equals( OrderConstants.Status.START.name() )  ){
                return RocketMQLocalTransactionState.COMMIT;
            }
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
