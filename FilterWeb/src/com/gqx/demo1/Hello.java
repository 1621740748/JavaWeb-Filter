package com.gqx.demo1;

public class Hello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="\t\taaa\t  bbbb      \t\t\t\n\r";
		String s1=s.replaceAll("\\s*", "");
		System.out.println(s1);
	}

}
