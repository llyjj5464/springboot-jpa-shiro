package com.lyj.shiroweb.realm;


import com.lyj.shiroweb.dataobject.RolesPermissionsDO;
import com.lyj.shiroweb.dataobject.UserDO;
import com.lyj.shiroweb.dataobject.UserRolesDO;
import com.lyj.shiroweb.mapper.RolesPermissonsMapper;
import com.lyj.shiroweb.mapper.UserMapper;
import com.lyj.shiroweb.mapper.UserRolesMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    {
        super.setName("CustomRealm");
    }

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RolesPermissonsMapper rolesPermissonsMapper;
	@Autowired
	private UserRolesMapper userRolesMapper;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String username = (String) principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 获取角色
		List<UserRolesDO> userRolesDOList = userRolesMapper.findUserRolesDOByUsername(username);
		Set<String> roleNames = new HashSet<>();
		userRolesDOList.forEach(userRolesDO ->roleNames.add(userRolesDO.getRoleName()));
		authorizationInfo.setRoles(roleNames);
		System.out.println("用户: "+username+",的角色有: "+roleNames);
		// 获取权限
		Set<String> permissions = new HashSet<>();
		roleNames.forEach(role ->{
			Set<RolesPermissionsDO> rolesPermissionsDOSet = rolesPermissonsMapper.findRolesPermissionsDOSByRoleName(role);
			rolesPermissionsDOSet.forEach(rolesPermissionsDO->{
				permissions.add(rolesPermissionsDO.getPermission());
			});
		});
		System.out.println("用户: "+username+",的权限有: "+permissions);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        UserDO userDO = userMapper.findUserDOByUsername(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userDO.getUsername(), userDO.getPassword(),"CustomRealm");
        return simpleAuthenticationInfo;
	}
}
