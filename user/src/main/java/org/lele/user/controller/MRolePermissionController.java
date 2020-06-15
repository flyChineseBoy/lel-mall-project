package org.lele.user.controller;



import com.baomidou.mybatisplus.extension.api.ApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lele.common.dto.CommonResult;
import org.lele.common.entity.user.MRolePermission;
import org.lele.user.service.MRolePermissionService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 角色权限表(MRolePermission)表控制层
 *
 * @author lele
 * @since 2020-05-07 20:55:00
 */
@Api(tags = "角色授权资源",position = 4)
@RestController
@RequestMapping("mRolePermission")
public class MRolePermissionController extends ApiController {

    @Resource
    private MRolePermissionService mRolePermissionService;

    /**
     * 新增数据
     *@param mRolePermission 实体对象
     *
     * @return 新增结果
     */
    @ApiOperation("授权资源到某个角色")
    @PostMapping
    public CommonResult insert(@RequestBody MRolePermission mRolePermission) {
        Assert.notNull(mRolePermission,"角色id和资源id不能为空");
        Assert.notNull(mRolePermission.getPermissionId(),"资源id不能为空");
        Assert.notNull(mRolePermission.getRoleId(),"角色id不能为空");
        return CommonResult.success(this.mRolePermissionService.save(mRolePermission));
    }



    /**
     * 取消授权
     *@param mRolePermission 实体对象
     * @return 删除结果
     */
    @ApiOperation("取消授权")
    @PostMapping("/remove")
    public CommonResult delete(@RequestBody MRolePermission mRolePermission) {
        // TODO 是否要将确认角色、资源是否存在的操作放在后端。
        Assert.notNull(mRolePermission,"角色id和资源id不能为空");
        Assert.notNull(mRolePermission.getPermissionId(),"资源id不能为空");
        Assert.notNull(mRolePermission.getRoleId(),"角色id不能为空");

        Map<String,Object> param = new HashMap<>();
        param.put("permission_id",mRolePermission.getPermissionId());
        param.put("role_id",mRolePermission.getRoleId());
        return CommonResult.success(this.mRolePermissionService.removeByMap(param));
    }
}