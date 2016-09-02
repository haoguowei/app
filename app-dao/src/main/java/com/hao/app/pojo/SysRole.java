package com.hao.app.pojo;

import java.io.Serializable;

public class SysRole implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6916722107421779478L;

	private Integer id;

    private String name;

    private String intro;

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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

	@Override
	public String toString() {
		return "SysRole [id=" + id + ", name=" + name + ", intro=" + intro + "]";
	}
    
    
}