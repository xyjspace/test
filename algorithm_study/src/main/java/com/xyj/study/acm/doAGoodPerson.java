package com.xyj.study.acm;

import java.util.Scanner;

/**
 * Created by banma on 2017/7/12.
 * 做一个正气的杭电人
 */
public class doAGoodPerson {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        String s = "HDU";
        int[] n = new int[m];
        for (int i = 0; i < m; i++) {
            n[i] = input.nextInt();
        }
        for (int j = 0; j < m; j++) {
            StringBuffer ss = new StringBuffer();
            int count = n[j] - 1;
            while (count >= 0) {
                ss.append(s);
                count--;
            }
            for (int k = 0; k < ss.toString().length(); k++) {
                System.out.println(ss);
            }
        }
    }
}
