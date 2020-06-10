package org.lele.user.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lele.common.dto.CommonResult;
import org.lele.common.entity.MUser;
import org.lele.user.service.MUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表(MUser)表控制层
 *
 * @author lele
 * @since 2020-05-07 20:55:11
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("mUser")
public class MUserController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private MUserService mUserService;

    private Logger logger = LoggerFactory.getLogger( MUserController.class );

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param mUser 查询实体
     * @return 所有数据
     */
    @GetMapping
    @ApiOperation(value = "查询用户")
    public CommonResult selectAll(@ApiIgnore Page<MUser> page, MUser mUser) {
        return CommonResult.success(this.mUserService.page(page, new QueryWrapper<>(mUser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public CommonResult selectOne(@PathVariable Serializable id) {
        return CommonResult.success(this.mUserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param mUser 实体对象
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation(value = "新增一个用户")
    public CommonResult insert(@RequestBody MUser mUser) {
        return CommonResult.success(this.mUserService.save(mUser));
    }

    /**
     * 修改数据
     *
     * @param mUser 实体对象
     * @return 修改结果
     */
    @PutMapping
    public CommonResult update(@RequestBody MUser mUser) {
        return CommonResult.success(this.mUserService.updateById(mUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @ApiOperation(value = "删除一个用户")
    public CommonResult delete(@RequestParam("idList") List<Long> idList) {
        return CommonResult.success(this.mUserService.removeByIds(idList));
    }
}