package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 系统登录用户
 * @author haoguowei
 *
 */
@Data
@ToString
public class SysMember implements Serializable{
   
	private static final long serialVersionUID = -1583799200806690144L;

	private Integer id;

    private String name; //登录名
    
    private String imgs; //头像

    private int valid; //有效，1禁用

    private String pwd;

    private String showName;

    private Integer roleId;//角色id

    private String phone;

    private String email;

    private int projectsId;
    private int areaId;

    
    //当前用户的权限列表
    private List<String> priUrls;
    
    //角色名
    private String roleName;
    //角色描述
    private String roleIntor;


    private String projectsName;


}