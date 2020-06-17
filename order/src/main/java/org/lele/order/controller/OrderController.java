package org.lele.order.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.lele.common.dto.CommonResult;
import org.lele.common.entity.order.MOrder;
import org.lele.order.service.OrderService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 订单总表(MOrder)表控制层
 *
 * @author lele
 * @since 2020-06-13 20:45:40
 */
@RestController
@RequestMapping("order")
public class OrderController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param order 查询实体
     * @return 所有数据
     */
    @GetMapping
    public CommonResult selectAll(@ApiIgnore Page<MOrder> page, MOrder order) {
        return CommonResult.success(this.orderService.page(page, new QueryWrapper<>(order)));
    }

    /**
     * 查看订单详情
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public CommonResult selectOne(@PathVariable Serializable id) {
        return CommonResult.success(this.orderService.getById(id));
    }

    /**
     * 下单操作（单商品下单）
     *
     * @return 新增结果
     */
    @ApiOperation("下单")
    @PostMapping("place/{productId}")
    public CommonResult placeOrder(@RequestBody Long productId) {
        return CommonResult.success(this.orderService.placeOrder(productId));
    }

    /**
     * 支付订单、回调
     * @param order 实体对象
     * @return 修改结果
     */
    @ApiOperation("支付订单、回调")
    @PostMapping
    public CommonResult update(@RequestBody MOrder order) {
        return CommonResult.success(this.orderService.updateById(order));
    }

    /**
     * 取消订单
     * @param orderId 实体对象
     * @return 删除结果
     */
    @ApiOperation("取消订单")
    @PostMapping("cancel/{id}")
    public CommonResult delete(@PathVariable Long orderId) {
        return CommonResult.success(this.orderService.cancel(orderId));
    }
}