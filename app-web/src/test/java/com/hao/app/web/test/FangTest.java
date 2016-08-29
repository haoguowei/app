package com.hao.app.web.test;

import java.text.DecimalFormat;

public class FangTest {

	public static String format(double money) {
		return new DecimalFormat("###0.00").format(money) + "元";
	}


	public static void main(String[] args) {
		double fang = 4000;
		double wuye = 145;
		double dian = 0;
		double ranqi = 0;
		
		double hao = 1500;
		double li = 1250;
		double chen = 1250;
		
		double total = fang + wuye + dian + ranqi;
		System.out.println("2016-06-15,交给房东总计" + format(total));
		
		System.out.println("\n其中：");
		double oneWuye = wuye/3;
		double oneDian = dian/5;
		double oneRanqi = ranqi/3;
		System.out.println("物业共"+wuye+"元, 分3份(郝：李：陈 = 1:1:1)， 每份" + format(oneWuye));
		System.out.println("电费共"+dian+"元, 分5份(郝：李：陈 = 2:2:1)， 每份" + format(oneDian));
		System.out.println("燃气共"+ranqi+"元, 分3份(郝：李：陈 = 2:1:0)， 每份" + format(oneRanqi));
		
		double totalHao = hao + oneWuye + oneDian * 2 + oneRanqi * 2;
		System.out.println("\n郝缴纳" + hao + " + " + format(oneWuye) + " + " + format(oneDian) + " * 2 + " + format(oneRanqi) + " * 2 = " + format(totalHao));
//		System.out.println("\n李需给我" + format(total - totalHao));
		
		double totalLi = li + oneWuye + oneDian * 2 + oneRanqi;
		System.out.println("\n李缴纳" + li + " + " + format(oneWuye) + " + " + format(oneDian) + " * 2 + " + format(oneRanqi) + " * 1 = " + format(totalLi));
		
		double totalChen = li + oneWuye + oneDian;
		System.out.println("\n陈缴纳" + chen + " + " + format(oneWuye) + " + " + format(oneDian) + " = " + format(totalChen));
		
		System.out.println("\n校验各家金额和：" + (totalHao + totalLi + totalChen));
	}
}
