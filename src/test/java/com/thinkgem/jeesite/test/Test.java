package com.thinkgem.jeesite.test;

public class Test {

	public static void main(String[] args) {
		String str = "hello 哈哈哈哈 这是 这是";
		String reg = "这是";

		System.out.print(str.contains(reg));
		System.out.print(str.replaceAll(reg,"<b>"+reg+"<b>"));

	}
}
