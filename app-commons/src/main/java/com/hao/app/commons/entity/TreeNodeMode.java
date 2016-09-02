package com.hao.app.commons.entity;

import java.io.Serializable;
import java.util.List;

/**
 * ext tree的节点数据模型
 * 
 * @author haoguowei
 *
 */
public class TreeNodeMode implements Serializable{

	private static final long serialVersionUID = -2262433601864965564L;

	private int id; // 唯一

	private int parentId;// 父节点id

	private String text; // 显示文本

	private String href; // 地址

	private boolean leaf; // 是否是叶子节点

	private String hrefTarget = "layout_page_right"; // 链接目标

	private Boolean checked; // 有无checkbox

	private Object attributes; // 存储对象

	private Boolean expanded = false;// 是否展开

	private List<TreeNodeMode> children; // 子节点

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public Boolean getChecked() {
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

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public List<TreeNodeMode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeMode> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "TreeNodeMode [id=" + id + ", parentId=" + parentId + ", text=" + text + ", href=" + href + ", leaf="
				+ leaf + ", hrefTarget=" + hrefTarget + ", checked=" + checked + ", attributes=" + attributes
				+ ", expanded=" + expanded + ", children=" + children + "]";
	}
	
	

}
