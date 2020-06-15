package org.lele.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lele.common.entity.order.MOrder;
import org.lele.common.entity.order.OrderItem;

import java.util.List;

/**
 * org.lele.order.dto
 *
 * @author: lele
 * @date: 2020-06-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("一个订单的完整数据")
public class OrderDTO {

    @ApiModelProperty("order主体")
    private MOrder order;

    @ApiModelProperty("order所有的子项")
    private List<OrderItem> orderItemList;

}
