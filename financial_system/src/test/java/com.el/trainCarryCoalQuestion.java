package com.el;

import org.junit.Test;

/**
 * Created by Ak_Guili on 2017/4/20.
 */
public class trainCarryCoalQuestion {
    /**
     * 问题概述：
     * 此类问题应满足两个两个条件
     * 1.煤的总质量为火车载重的整数倍
     * 2.到达终点时，煤的总质量小于等于火车的载重
     */
    @Test
    public void transportCoalTest() {
        double totalCoal = 30000;
        double trainCarrying = 1000;
        double distance = 1800;
        double surplusCoal = totalCoal;
        double count = (int) totalCoal / trainCarrying;
        for (double i = count; i > 1; i--) {
            if (distance > 0 && surplusCoal > trainCarrying) {
                double tempDistance = distance - trainCarrying / (2 * count - 1);
                if (tempDistance > 0) {
                    distance -= trainCarrying / (2 * count - 1);
                    surplusCoal -= 1000;
                    count--;
                } else {
                    System.out.println("滴滴滴，快下车！");
                    break;
                }
                System.out.println("distance=" + distance);
                System.out.println("surplusCoal=" + surplusCoal);
                System.out.println("*********************************");
            }
        }

        if (surplusCoal <= trainCarrying) {
            surplusCoal = surplusCoal - distance;
            System.out.println("最多可拉煤：");
            System.out.println("SurplusCoal = " + surplusCoal);
        }else
            System.out.println("你丫逗我吧，你算一个试试！");
    }

    @Test
    public void castTest() {
        double x = (int) 2.14;
        System.out.println(x);
    }
}
