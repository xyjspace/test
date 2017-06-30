package com.xyj.study.springbootstart;


import org.assertj.core.util.Lists;
import org.assertj.core.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by banma on 2017/6/29.
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(10);
       list.stream().filter(a -> a!=null).count();

//        Stream.generate(Math::random).limit(10).forEach(System.out::println);
        Stream.iterate(1, item -> item+1).limit(10).forEach(System.out::println);

        List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);

        System.out.println("sum is:"+nums.stream().filter(num -> num != null).

        distinct().mapToInt(num -> num * 2).

        peek(System.out::println).skip(2).limit(4).sum());

    }

}
