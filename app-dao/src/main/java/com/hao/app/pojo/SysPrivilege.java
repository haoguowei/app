package com.hao.app.pojo;

import java.io.Serializable;

public class SysPrivilege implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6407803666512759200L;

	private Integer id;
    
    private Integer menuId;

    private String name;

    private String url;

    public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

}