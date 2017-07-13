package com.xyj.study.acm;

import java.util.Scanner;

/**
 * Created by banma on 2017/7/12.
 * N皇后问题用 回溯+递归解决
 */
public class NEmpress {

    private static int count;

    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();
        //先全部设为00
        int[][] a = new int[N][N];
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < a[i].length; k++) {
                a[i][k] = 0;
            }
        }
        //开始递归最优解！
        search(0, a);
        System.out.println(N + "皇后的最优解有：" + count + "种");
    }

    //美滋滋
    private static void search(int y, int[][] a) {

        //到达了最后说明这条解可行！ 打印并+1
        if (y == a.length) {
            //输出结果！
//            for (int m = 0; m < a.length; m++) {
//                for (int n = 0; n < a.length; n++) {
//                    System.out.printf(a[m][n] + "");
//                }
//                System.out.println("");
//            }
//            System.out.println("");
            count++;
            return;
        }

        //panduan
        for (int x = 0; x < a.length; x++) {
            if (verify(x, y, a)) {
                a[x][y] = 1;
                //递归search
                search(y + 1, a);
                a[x][y] = 0;
            }
        }

    }

    private static boolean verify(int x, int y, int[][] a) {
        int m, n;
        //判断同一行
        for (n = 0; n < a.length; n++) {
            m = x;
            if (a[m][n] == 1) {
                return false;
            }
        }
        //判断列
        for (m = 0; m < a.length; m++) {
            n = y;
            if (a[m][n] == 1) {
                return false;
            }
        }
        //判断左上
         if(x - 1 >= 0 && y - 1 >= 0 && a[x - 1][y - 1] == 1) {
            return false;
        }
        //判断左下
        if (x <= a.length - 2 && y - 1 >= 0 && a[x + 1][y - 1] == 1) {
            return false;
        }
        //判断右上
        if (x - 1 >= 0 && y <= a.length - 2 && a[x - 1][y + 1] == 1) {
            return false;
        }
        //判断右下
        if (x <= a.length - 2 && y <= a.length - 2 && a[x + 1][y + 1] == 1) {
            return false;
        }
        return true;
    }

}
