package com.lyj.shiroweb.dataobject;

import lombok.Data;

import javax.persistence.*;

/**
 * @author lyj
 * @date 2019/9/8 19:28
 */
@Entity
@Table(name = "users")
@Data
public class UserDO {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
}
