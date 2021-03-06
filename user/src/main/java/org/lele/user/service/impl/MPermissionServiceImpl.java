package org.lele.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lele.common.entity.user.MPermission;
import org.lele.user.dao.MPermissionDao;
import org.lele.user.service.MPermissionService;
import org.springframework.stereotype.Service;

/**
 * 权限表(MPermission)表服务实现类
 *
 * @author lele
 * @since 2020-05-02 13:23:09
 */
@Service("mPermissionService")
public class MPermissionServiceImpl extends ServiceImpl<MPermissionDao, MPermission> implements MPermissionService {
}