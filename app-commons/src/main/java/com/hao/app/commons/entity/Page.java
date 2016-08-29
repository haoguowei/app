package com.hao.app.commons.entity;

/**
 * 分页
 * 
 * @author bjhaoguowei
 *
 */
public class Page {

	public static int LIMIT = 20;
	//参数名：每页记录数
	public static String PAGELIMIT = "pageLimit";
	//参数名：分页的页数
	public static String PAGEIDX = "pages";

	public Page(int total) {
		this.currentPage = 1;
		init(total, currentPage);
	}

	public Page(int total, int currentPage) {
		init(total, currentPage);
	}

	public Page(int total, int currentPage, int limit) {
		this.limit = limit <= 0 ? LIMIT : limit;
		init(total, currentPage);
	}

	public void init(int total, int currentPage) {
		this.total = total < 0 ? 0 : total;

		this.pageSize = this.total / this.limit + (this.total % this.limit > 0 ? 1 : 0);
		this.pageSize = this.pageSize == 0 ? 1 : this.pageSize;

		this.currentPage = currentPage <= 0 ? 1 : currentPage;
		if (this.currentPage > this.pageSize) {
			this.currentPage = this.pageSize;
		}

		this.start = (this.currentPage - 1) * this.limit;

	}

	/**
	 * 总记录数
	 */
	private int total;

	/**
	 * 总分页数
	 */
	private int pageSize;

	/**
	 * 当前页
	 */
	private int currentPage;

	/**
	 * 每页记录数
	 */
	private int limit = LIMIT;

	/**
	 * 开始记录
	 */
	private int start;

	public int getTotal() {
		return total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getLimit() {
		return limit;
	}

	public int getStart() {
		return start;
	}

	@Override
	public String toString() {
		return "Page [total=" + total + ", pageSize=" + pageSize + ", currentPage=" + currentPage + ", limit=" + limit
				+ ", start=" + start + "]";
	}

	public static void main(String[] args) {
		Page page = new Page(11, 4, 10);
		System.out.println(page);
	}

}
