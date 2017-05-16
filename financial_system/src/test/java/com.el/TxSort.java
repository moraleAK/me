package com.el;

import com.el.util.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.support.ManagedMap;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static java.lang.System.in;

/**
 * Created by Ak_Guili on 2017/5/5.
 */
public class TxSort {
    @Test
    public void test(){
        /*
        交易流水号	付款方支付账号	收款方支付账号	支付金额	　	A	  B	    C	     D    	E	 F
            1	        A	            B	            10	　	-10   +10	0       0       0	 0
            2	        A	            C	            10	　	-20   +10	+10     0       0     0
            3	        A	            E	            30	　	-50   +10	+10     0       +30   0
            4	        B	            E	            10	　	-50   0	    +10     0       +40   0
            5	        C	            F	            10	　	-50   0	    0       0       +40	  +10
            6	        E	            C	            10	　	-50   0	    +10     0       +30   +10
            7	        D	            A	            10	　	-40   0	    +10     -10     +30   +10
            8	        C	            B	            10	　	-40   +10	0       -10     +30   +10
            9	        E	            C	            10	　	-40   +10	+10     -10     +20   +10
            10	        A	            B	            10	　	-50   +20	+10     -10     +20   +10
            11	        B	            C	            10	　	-50   +10	+20     -10     +20   +10
            12	        E	            B	            10	　	-50   +20	+20     -10     +10   +10
            13	        C	            A	            20	　	-30   +20	0     -10       +10   +10

        */

        /*
        思路：
         1.轧差结束，为负数则为必须补货款，为正数则可能不需要补货款
         2.模拟交易过程中，单账号出现最大负值，为最多须补货款数
         3.由1、2 可知，先对 轧差结果分类 1.正数一类，2.负数一类
         4.尽可能保证 正数一类不需要补货款，负数一类补货款钱最少
         5.模拟时起始数据优先选取 负数最多的那个
         6.订单尽量按照 进出排序
         */
        /**
         * 思路2：
         * 1.轧差结束，得到各自账户所得和所欠金额。最终为正的或者为 0 的记为 A 用户，为负的记为 B 用户
         * 2.通知 B 用户补齐所欠金额。
         * #每个账户有真实账户 X 和轧差账户 Y
         * 3.把补的钱转到轧差账户
         * 4.轧差账户执行轧差操作
         * 5.操作结束把 B 轧差账户钱转到 真实账户
         */
        HashMap<String, Long> map = new HashMap();
        map.put("a",0l);
        map.put("b",0l);
        map.put("c",0l);
        map.put("d",0l);
        map.put("e",0l);
        map.put("f",0l);
        map.put("a",map.get("a") + 10);
        List<TxTO> list= new ArrayList<>();
        TxTO to = new TxTO();

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        a +=  - 10;
        b +=  + 10;

        a +=  - 10;
        c +=  + 10;
    }

    @Test
    public void rankTest(){
        List<Long> list = new ArrayList<>();
        List<Long> sortList = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<=5000; i++) {
            list.add((long)random.nextInt(100000));
        }
        System.out.println("start:"+ System.currentTimeMillis());
        sortList = bubble_sort(list);
        System.out.println("end:"+ System.currentTimeMillis());
        System.out.println("end:"+ System.currentTimeMillis());
        System.out.println(sortList.get(0));
        System.out.println(sortList.get(sortList.size()-1));

    }
    static List<Long> bubble_sort(List<Long> longs)
    {
        long count =0;
       // List<Long> list = new ArrayList<>();
        for (int i = 0; i < longs.size(); i++)
        {
            //System.out.println(i);
            for (int j = i; j < longs.size(); j++)
            {
                if (longs.get(i) > longs.get(j))
                {
                    long temp = longs.get(i);
                    longs.set(i,longs.get(j));
                    longs.set(j,temp);

                    count ++;
                    if(count % 100000 == 0) {
                        System.out.println(count);
                    }
                }
            }
        }
        System.out.println("count = " + count);

        return longs;
    }
}
