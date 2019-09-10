package com.lyj.shiroweb.mapper;

import com.lyj.shiroweb.dataobject.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lyj
 * @date 2019/9/8 19:25
 */
public interface UserMapper extends JpaRepository<UserDO,String> {
    UserDO findUserDOByUsername(String username);
}
