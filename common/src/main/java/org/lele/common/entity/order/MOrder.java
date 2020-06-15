package org.lele.common.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 订单总表(MOrder)表实体类
 *
 * @author lele
 * @since 2020-06-14 23:38:08
 */
@SuppressWarnings("serial")
@ApiModel("订单总表" )
public class MOrder extends Model<MOrder> {
        
    @ApiModelProperty("")
    private Long id;
    /**订单状态，有START开始、WAITING延时等待、TIME_OUT的等待超时、PROCESSING处理中、COMPLETE完成、CANCEL取消、CART在购物车中。*/    
    @ApiModelProperty("订单状态，有START开始、WAITING延时等待、TIME_OUT的等待超时、PROCESSING处理中、COMPLETE完成、CANCEL取消、CART在购物车中。")
    private String status;
    /**申请人*/    
    @ApiModelProperty("申请人")
    private String applyUser;
    /**支付时间*/    
    @ApiModelProperty("支付时间")
    private LocalDateTime payd;
    /**订单原价总金额*/    
    @ApiModelProperty("订单原价总金额")
    private Double price;
    /**应付总金额*/    
    @ApiModelProperty("应付总金额")
    private Double payable;
        
    @ApiModelProperty("")
    private LocalDateTime created;
        
    @ApiModelProperty("")
    private LocalDateTime updated;


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