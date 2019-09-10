package com.lyj.shiroweb.mapper;

import com.lyj.shiroweb.dataobject.RolesPermissionsDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * @author lyj
 * @date 2019/9/8 23:28
 */
public interface RolesPermissonsMapper extends JpaRepository<RolesPermissionsDO,Integer> {
    Set<RolesPermissionsDO> findRolesPermissionsDOSByRoleName(String roleName);
}
