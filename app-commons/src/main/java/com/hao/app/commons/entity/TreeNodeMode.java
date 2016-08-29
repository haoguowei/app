package com.hao.app.commons.entity;

import java.util.List;

/**
 * ext tree的节点数据模型
 * 
 * @author haoguowei
 *
 */
public class TreeNodeMode{
	
	private int id; //唯一
	
	private int parentId;//父节点id
	
	private String text; //显示文本
	
	private String href; //地址
	
	private boolean leaf; //是否是叶子节点
	
	private String hrefTarget = "layout_page_right"; // 链接目标
	
	private Boolean checked; //有无checkbox

	private Object 	attributes; //存储对象
	
	private List<TreeNodeMode> children; //子节点

	public int getId() {
		return id;
	}

	public String getHref() {
		return href;
	}



	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public void setHref(String href) {
		this.href = href;
	}



	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean isChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public List<TreeNodeMode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeMode> children) {
		this.children = children;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "TreeNodeMode [id=" + id + ", parentId=" + parentId + ", text="
				+ text + ", href=" + href + ", leaf=" + leaf + ", hrefTarget="
				+ hrefTarget + ", checked=" + checked + ", attributes="
				+ attributes + ", children=" + children + "]";
	}
	
	
	
}
