package org.lele.authorization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lele.authorization.dao.MUserRoleDao;
import org.lele.authorization.service.MUserRoleService;
import org.lele.common.entity.user.MUserRole;
import org.springframework.stereotype.Service;

/**
 * 用户角色表(MUserRole)表服务实现类
 *
 * @author lele
 * @since 2020-05-02 13:23:09
 */
@Service("mUserRoleService")
public class MUserRoleServiceImpl extends ServiceImpl<MUserRoleDao, MUserRole> implements MUserRoleService {

}