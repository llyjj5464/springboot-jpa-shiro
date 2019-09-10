package com.lyj.shiroweb.mapper;


import com.lyj.shiroweb.dataobject.UserRolesDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lyj
 * @date 2019/9/8 23:23
 */
public interface UserRolesMapper extends JpaRepository<UserRolesDO,Integer> {
    List<UserRolesDO> findUserRolesDOByUsername(String username);
}
