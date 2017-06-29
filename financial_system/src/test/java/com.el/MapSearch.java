package com.el;

import com.el.maps.MapSearcher;
import com.el.maps.Maps;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ak_Guili on 2017/6/2.
 */
public class MapSearch {
    @org.junit.Test
    public void test() {

        Maps.MapBuilder<String> mapBuilder = new Maps.MapBuilder<String>().create();
        //构建节点
        mapBuilder.addNode(Maps.Node.valueOf("A"));
        mapBuilder.addNode(Maps.Node.valueOf("B"));
        mapBuilder.addNode(Maps.Node.valueOf("C"));
        mapBuilder.addNode(Maps.Node.valueOf("D"));
        mapBuilder.addNode(Maps.Node.valueOf("E"));
        mapBuilder.addNode(Maps.Node.valueOf("F"));
        mapBuilder.addNode(Maps.Node.valueOf("G"));
        mapBuilder.addNode(Maps.Node.valueOf("H"));
        mapBuilder.addNode(Maps.Node.valueOf("I"));
        //构建路径
        mapBuilder.addPath("A", "B", 1);
        mapBuilder.addPath("A", "F", 2);
        mapBuilder.addPath("A", "D", 4);
        mapBuilder.addPath("A", "C", 1);
        mapBuilder.addPath("A", "G", 5);
        mapBuilder.addPath("C", "G", 3);
        mapBuilder.addPath("G", "H", 1);
        mapBuilder.addPath("H", "B", 4);
        mapBuilder.addPath("B", "F", 2);
        mapBuilder.addPath("E", "F", 1);
        mapBuilder.addPath("D", "E", 1);
        mapBuilder.addPath("H", "I", 1);
        mapBuilder.addPath("C", "I", 1);

        //构建全局Map
        Maps<String> map = mapBuilder.build();

        //创建路径搜索器(每次搜索都需要创建新的MapSearcher)
        MapSearcher<String> searcher = new MapSearcher<String>();
        //创建关闭节点集合
        Set<String> closeNodeIdsSet = new HashSet<String>();
        closeNodeIdsSet.add("C");
        //设置初始节点
        searcher.init("A", map, closeNodeIdsSet);
        //获取结果
        MapSearcher.SearchResult<String> result = searcher.getResult("G");
        System.out.println(result);
        //test.printPathInfo();
    }
}
