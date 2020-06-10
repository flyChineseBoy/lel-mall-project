package org.lele.user.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lele.common.dto.CommonResult;
import org.lele.common.entity.MPermission;
import org.lele.user.service.MPermissionService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 权限表(MPermission)表控制层
 *
 * @author lele
 * @since 2020-05-07 20:53:46
 */
@Api(tags = "权限资源")
@RestController
@RequestMapping("mPermission")
public class MPermissionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private MPermissionService mPermissionService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param mPermission 查询实体
     * @return 所有数据
     */
    @GetMapping
    @ApiOperation(value = "查询所有权限资源")
    public CommonResult selectAll(@ApiIgnore Page<MPermission> page, MPermission mPermission) {
        return CommonResult.success(this.mPermissionService.page(page, new QueryWrapper<>(mPermission)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "根据id删除权限资源")
    public CommonResult selectOne(@PathVariable Serializable id) {
        return CommonResult.success(this.mPermissionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param mPermission 实体对象
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation(value = "新增一条权限资源")
    public CommonResult insert(@RequestBody MPermission mPermission) {
        return CommonResult.success(this.mPermissionService.save(mPermission));
    }

    /**
     * 修改数据
     *
     * @param mPermission 实体对象
     * @return 修改结果
     */
    @PutMapping
    public CommonResult update(@RequestBody MPermission mPermission) {
        return CommonResult.success(this.mPermissionService.updateById(mPermission));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public CommonResult delete(@RequestParam("idList") List<Long> idList) {
        return CommonResult.success(this.mPermissionService.removeByIds(idList));
    }
}