package com.lyj.shiroweb.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lyj
 * @date 2019/9/8 23:22
 */
@Data
@Entity
@Table(name = "user_roles")
public class UserRolesDO {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String roleName;
}
