package com.hao.app.commons.utils;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.hao.app.commons.entity.Page;

/**
 * 分页工具类
 * 
 * @author bjhaoguowei
 *
 */
public class PageUtil {

	/**
	 * 生成分页的html
	 * 
	 * @param page
	 * @param url
	 * @return
	 */
	public static String genPageHtml(Page page, String url) {
		url = joinUrlPre(url, page.getLimit());

		StringBuilder sbr = new StringBuilder();
		sbr.append("<div class='widget-foot'>");
		sbr.append("<ul class='pagination pull-right'>");

		// 得到分页下标
		Set<Integer> set = getPageIndex(page);

		if (page.getCurrentPage() > 1) {
			sbr.append("<li><a href='").append(joinUrl(url, page.getCurrentPage() - 1)).append("'>").append("上一页")
					.append("</a></li>");
		}

		int tmp = 1;
		for (Integer index : set) {
			if (index - tmp > 1) {
				sbr.append("<li><a>...</a></li>");
			}
			String currentCss = index == page.getCurrentPage() ? " style='background-color:#ddd;'" : "";
			sbr.append("<li><a href='").append(joinUrl(url, index)).append("'").append(currentCss).append(">")
					.append(index).append("</a></li>");
			tmp = index;
		}

		if (page.getCurrentPage() < page.getPageSize()) {
			sbr.append("<li><a href='").append(joinUrl(url, page.getCurrentPage() + 1)).append("'>").append("下一页")
					.append("</a></li>");
		}

		sbr.append("</ul><div class='clearfix'></div></div>");
		return sbr.toString();
	}
	
	public static String joinUrlPre(String url, int limit) {
		StringBuilder sbr = new StringBuilder();
		sbr.append(url)
			.append("?").append(Page.PAGELIMIT).append("=").append(limit)
			.append("&").append(Page.PAGEIDX).append("=");
		return sbr.toString();
	}

	public static String joinUrl(String urlPre, int pageIndex) {
		return urlPre + pageIndex;
	}

	/**
	 * 得到分页下标
	 * 
	 * @param page
	 * @return set
	 */
	public static Set<Integer> getPageIndex(Page page) {
		Set<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});

		if (page.getPageSize() < 10) {
			for (int i = 1; i <= page.getPageSize(); i++) {
				set.add(i);
			}
		} else {
			int pre = page.getCurrentPage() - 1;
			int next = page.getCurrentPage() + 3;
			pre = pre < 1 ? 1 : pre;
			next = next > page.getPageSize() ? page.getPageSize() : next;

			if (page.getCurrentPage() < 4) { // 偏左边
				set.add(1);
				set.add(page.getPageSize());
				for (int i = pre; i <= next; i++) {
					set.add(i);
				}

				int mid = (page.getPageSize() - 1 - next) / 2 + next;
				int i = 0;
				int j = 1;
				while (set.size() < 9) {
					int tmp = 1 * i;
					if (j % 2 == 0) {
						tmp = -1 * i;
						i++;
					}
					j++;
					set.add(mid + tmp);
				}
			} else {
				// 中间
				if (page.getCurrentPage() < (page.getPageSize() - 4)) {
					set.add(1);
					set.add(2);
					set.add(page.getPageSize() - 1);
					set.add(page.getPageSize());

					for (int i = pre; i <= next; i++) {
						set.add(i);
					}
				} else {
					// 偏右
					set.add(1);
					set.add(page.getPageSize());

					if (next - page.getCurrentPage() < 2) {
						pre = pre - (2 - (next - page.getCurrentPage()));
					}

					for (int i = pre; i <= next; i++) {
						set.add(i);
					}

					int mid = (pre - 2) / 2 + 2;
					int i = 0;
					int j = 0;
					while (set.size() < 9) {
						int tmp = 1 * i;
						if (j % 2 == 0) {
							tmp = -1 * i;
							i++;
						}
						j++;
						set.add(mid + tmp);
					}
				}
			}
		}
		return set;
	}

	/**
	 * 测试分页效果
	 */
	public static void test() {
		for (int j = 0; j < 40; j++) {
			Page page = new Page(341, j, 10);
			Set<Integer> set = getPageIndex(page);

			int tmp = 1;
			for (Integer i : set) {
				if (i - tmp > 1) {
					System.out.print("... ");
				}
				String pg = String.valueOf(i);
				if (page.getCurrentPage() == i) {
					pg = "(" + i + ")";
				}
				System.out.print(pg + " ");
				tmp = i;
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		for (int j = 0; j < 40; j++) {
			Page page = new Page(341, j, 10);
			Set<Integer> set = getPageIndex(page);

			int tmp = 1;
			for (Integer i : set) {
				if (i - tmp > 1) {
					System.out.print("... ");
				}
				String pg = String.valueOf(i);
				if (page.getCurrentPage() == i) {
					pg = "(" + i + ")";
				}
				System.out.print(pg + " ");
				tmp = i;
			}
			System.out.println();
		}
	}

}
