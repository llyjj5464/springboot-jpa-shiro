package com.lyj.shiroweb.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lyj
 * @date 2019/9/8 23:26
 */
@Entity
@Table(name = "roles_permissions")
@Data
public class RolesPermissionsDO {
    @Id
    @GeneratedValue
    private Integer id;

    private String roleName;
    private String permission;
}
