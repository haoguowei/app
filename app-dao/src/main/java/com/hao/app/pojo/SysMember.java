package com.hao.app.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 系统登录用户
 * @author haoguowei
 *
 */
public class SysMember implements Serializable{
   
	private static final long serialVersionUID = -1583799200806690144L;

	private Integer id;

    private String name; //登录名

    private int valid; //有效，1禁用

    private String pwd;

    private String showName;

    private Integer roleId;//角色id

    private String phone;

    private String email;
    
    
    //当前用户的权限列表
    private List<String> priUrls;
    
    //角色名
    private String roleName;
    //角色描述
    private String roleIntor;
    
    
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleIntor() {
		return roleIntor;
	}

	public void setRoleIntor(String roleIntor) {
		this.roleIntor = roleIntor;
	}

	public List<String> getPriUrls() {
		return priUrls;
	}

	public void setPriUrls(List<String> priUrls) {
		this.priUrls = priUrls;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName == null ? null : showName.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

	@Override
	public String toString() {
		return "SysMember [id=" + id + ", name=" + name + ", valid=" + valid + ", pwd=" + pwd + ", showName=" + showName
				+ ", roleId=" + roleId + ", phone=" + phone + ", email=" + email + ", priUrls=" + priUrls
				+ ", roleName=" + roleName + ", roleIntor=" + roleIntor + "]";
	}

    
    
}