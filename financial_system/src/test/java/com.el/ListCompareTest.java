package com.el;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ak_Guili on 2017/6/23.
 */
public class ListCompareTest {
    @Test
    public void test() {

        String s = "old";
        String s1 = "new";
        //取差集合
        List<String> resultList = new ArrayList<>();
        //数据库集合
        List<String> oldList = new ArrayList<>();
        //新增数据集合
        List<String> newList = new ArrayList<>();

        for(int i = 0; i< 50000; i ++){
            oldList.add(s+i);
            newList.add(s+i);
            newList.add(s1+i);
        }

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < oldList.size(); i++) {
            map.put(oldList.get(i), oldList.get(i));
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < newList.size(); i++) {
            if (map.get(newList.get(i)) != null) {
                resultList.add(newList.get(i));
            }
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("差集数据条数：" + resultList.size());

        long time = System.currentTimeMillis();
        newList.removeAll(oldList);
        System.out.println("官方耗时：" + (System.currentTimeMillis() - time) + "ms");
        System.out.println("差集数据条数：" +newList.size());
    }
}
