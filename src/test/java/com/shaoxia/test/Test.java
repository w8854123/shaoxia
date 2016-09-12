package com.shaoxia.test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class Test {

	@org.junit.Test
	public void uuid() {
		System.out.println(UUID.randomUUID().toString());
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		System.out.println(str);
		// 1、以逗号分割字符串
		String[] strs = str.split(",");
		System.out.println(Arrays.toString(strs));
		// 2、创建一个int类型的空数组
		int[] arr = new int[strs.length];
		// 3、循环遍历字符串数组strs，
		int max = Integer.parseInt(strs[0]);
		int min = Integer.parseInt(strs[0]);
		for (int i = 0; i < strs.length; i++) {
			int a = Integer.parseInt(strs[i]);
			arr[i]=a;
			if (i != 0) {
				if (a > max) {
					max = a;
				} else if (a < min) {
					min = a;
				}
			}
		}
		System.out.println("排序前："+Arrays.toString(arr));
		Arrays.sort(arr);//排序
		System.out.println("排序后："+Arrays.toString(arr));
		
		max=arr[arr.length-1];
		min=arr[0];
		
		System.out.println("最大值："+max);
		System.out.println("最小值："+min);
		
		// 4、将字符串数组中的每个字符串转换为int类型

		// 5、将转化后的int类型数字存入int类型的空数组中

	}
}
