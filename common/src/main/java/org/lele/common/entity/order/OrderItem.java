package org.lele.common.entity.order;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * (OrderItem)表实体类
 *
 * @author lele
 * @since 2020-06-14 23:38:08
 */
@SuppressWarnings("serial")
@ApiModel("" )
public class OrderItem extends Model<OrderItem> {
        
    @ApiModelProperty("")
    private Long id;
    /**订单ID*/    
    @ApiModelProperty("订单ID")
    private Long orderId;
    /**商品原价金额*/    
    @ApiModelProperty("商品原价金额")
    private Object price;
    /**该商品金额*/    
    @ApiModelProperty("该商品金额")
    private Object payable;
        
    @ApiModelProperty("")
    private LocalDateTime created;
        
    @ApiModelProperty("")
    private LocalDateTime updated;
    /**优惠券、暂留空*/    
    @ApiModelProperty("优惠券、暂留空")
    private Long coupon;
    /**商品-规格的id*/    
    @ApiModelProperty("商品-规格的id")
    private Long productSpecsId;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }