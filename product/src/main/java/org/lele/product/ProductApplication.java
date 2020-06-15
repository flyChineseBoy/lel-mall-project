package org.lele.product;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.aspectj.weaver.ast.Or;
import org.lele.common.dto.OrderDTO;
import org.lele.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

/**
 * org.lele.product
 *
 * @author: lele
 * @date: 2020-05-24
 */
@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run( ProductApplication.class );
    }

    @Service
    @RocketMQMessageListener(topic = "CreateOrder",consumerGroup = "CreateOrderConsumerGroup",selectorType = SelectorType.TAG,selectorExpression = "asd")
    public class CreateOrderConsumer implements RocketMQListener<String> {

        @Autowired
        private ProductService productService;
        @Override
        public void onMessage(String message) {
            // TODO 库存减一，要保证幂等性。
            OrderDTO orderDTO = JSONObject.parseObject(message, OrderDTO.class);
            orderDTO.getOrderItemList().forEach(
                    orderItem -> {
                        //orderItem.get
                    }
            );
            System.out.println( message );
            // productService.updateById()
        }
    }
}
