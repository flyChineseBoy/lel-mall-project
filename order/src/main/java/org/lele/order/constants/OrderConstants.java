package org.lele.order.constants;

/**
 * org.lele.order.constants
 *
 * @author: lele
 * @date: 2020-06-13
 */
public class OrderConstants {
    public enum Status{
        // UNCHECK 下单后、发送事务消息到队列之前，订单状态位UNCHECK。
        UNCHECK,START,WAITING,TIME_OUT,PROCESSING,COMPLETE,CANCEL,CART
    }
}
