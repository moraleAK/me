package com.el;

import com.el.to.SubAccountTO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Ak_Guili on 2017/5/5.
 */
public class TxSort {
    @Test
    public void test() {
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
         7.订单按照金额由小到大排序
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
        /**
         * 思路3
         * 1.先轧差 结果为正记为A账户 为负记为B账户
         * 2.B账户补齐账
         * 3.模拟交易由B账户开始（此时B账户金额都大于0）
         * 4.交易时按照金额由小到大开始交易
         * 5.遇到为负时 停止补钱 进行下一个B账户
         * 6.当B账户都为负或者0时 进行A账户排序按照金额开始排序
         * 7.选取A账户金额大于0的账户按照由小到大开始补差 直至补完或者为负 （对于补完自身的账户剔除）
         * 8.循环 3-7
         * 9.结束后理想状况为刚好都为0 ，实际可能会出现 须多补或者少补状况 具体分析可能出现补不平的状况
         * 1）a b 。。循环 此时选取 B账户 若都为A账户 则随机选取或选取补差金额小的账户
         * 2）
         */
        HashMap<String, Long> map = new HashMap();
        map.put("a", 0l);
        map.put("b", 0l);
        map.put("c", 0l);
        map.put("d", 0l);
        map.put("e", 0l);
        map.put("f", 0l);
        map.put("a", map.get("a") + 10);
        List<TxTO> list = new ArrayList<>();
        TxTO to = new TxTO();

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        a += -10;
        b += +10;

        a += -10;
        c += +10;
    }

    @Test
    public void rankTest() {
        List<Long> list = new ArrayList<>();
        List<Long> sortList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i <= 5000; i++) {
            list.add((long) random.nextInt(100000));
        }
        System.out.println("start:" + System.currentTimeMillis());
        sortList = bubble_sort(list);
        System.out.println("end:" + System.currentTimeMillis());
        System.out.println("end:" + System.currentTimeMillis());
        System.out.println(sortList.get(0));
        System.out.println(sortList.get(sortList.size() - 1));

    }

    static List<Long> bubble_sort(List<Long> longs) {
        long count = 0;
        // List<Long> list = new ArrayList<>();
        for (int i = 0; i < longs.size(); i++) {
            //System.out.println(i);
            for (int j = i; j < longs.size(); j++) {
                if (longs.get(i) > longs.get(j)) {
                    long temp = longs.get(i);
                    longs.set(i, longs.get(j));
                    longs.set(j, temp);

                    count++;
                    if (count % 100000 == 0) {
                        System.out.println(count);
                    }
                }
            }
        }
        System.out.println("count = " + count);

        return longs;
    }

    @Test
    public void txSortTest() {
        // 账户初始赋值
        List<SubAccountTO> subList = new ArrayList<>();
        //String[] accName = {"A", "B", "C", "D", "E", "F"};
        String[] accName = {"A", "B", "C", "D"};
        for (int i = 0; i < accName.length; i++) {
            SubAccountTO sb = new SubAccountTO();
            sb.setAccount(accName[i]);
            sb.setAmount(0);
            sb.setRecordAmount(0);
            subList.add(sb);
        }

        //初始化订单
        //String[] fAccName = {"A", "A", "A", "B", "C", "E", "D", "C", "E", "A", "B", "E", "C"};
        // String[] tAccName = {"B", "C", "E", "E", "F", "C", "A", "B", "C", "B", "C", "B", "A"};

        String[] fAccName = {"A", "B", "C", "D", "B", "B", "C"};
        String[] tAccName = {"B", "C", "D", "A", "A", "C", "B"};

        //long[] ll = {10, 10, 30, 10, 10, 10, 10, 10, 10, 10, 10, 10, 20};

        long[] ll = {10, 10, 20, 10, 5, 50, 50};
        List<TxTO> txList = new ArrayList<>();
        for (int i = 0; i < fAccName.length; i++) {
            TxTO to = new TxTO();
            to.setAmount(ll[i]);
            to.setId(i);
            to.setFrom(fAccName[i]);
            to.setTo(tAccName[i]);
            txList.add(to);
        }

        System.out.println("初始订单：");

        for (TxTO txTO : txList) {
            System.out.println("id = " + txTO.getId() + ", amount = " + txTO.getAmount() + ", from = " + txTO.getFrom() + ", to = " + txTO.getTo());
        }

        //轧差
        offsetBalance(txList, subList);
        System.out.println("***************************");
        System.out.println("轧差各账户金额：");
        for (SubAccountTO s : subList) {
            System.out.println(s.getAccount() + " = " + s.getAmount());
        }

        //补差
        for (SubAccountTO sb : subList) {
            if (sb.getAmount() > 0) {
                sb.setAmount(0);
            }
            if (sb.getAmount() < 0) {
                sb.setRecordAmount(sb.getRecordAmount() - sb.getAmount());
                sb.setAmount(sb.getAmount() - 2 * sb.getAmount());
            }
        }

        System.out.println("***************************");
        System.out.println("补差后各账户：");

        for (SubAccountTO s : subList) {
            System.out.println(s.getAccount() + " : amount = " + s.getAmount() + ", recordAmount = " + s.getRecordAmount());
        }

        System.out.println("***************************");
        System.out.println("模拟交易开始：");
        while (txList.size() > 0) {
            //判断是否出现死循环
            long count = simulateTx(subList, txList);
            if (count == 0) {
                System.out.println("出现死循环，剩余订单数为：" + txList.size());
                SubAccountTO minSub = getMinSubAccount(subList, txList);
                System.out.println("最小补差：account = " + minSub.getAccount() + ", amount = " + minSub.getMinAmount());
                for (SubAccountTO sb : subList) {
                    if (sb.getAccount().equals(minSub.getAccount())) {
                        sb.setAmount(sb.getAmount() + minSub.getMinAmount());
                        sb.setRecordAmount(sb.getRecordAmount() + minSub.getMinAmount());
                    }
                }
            }
        }
        /*System.out.println("交易顺序为：");
        for (TxTO tx : sortTxList) {
            System.out.println(tx.getId());
        }*/
        System.out.println("***************************");
        System.out.println("模拟交易结束后金额：");
        for (SubAccountTO s : subList) {
            System.out.println(s.getAccount() + " : amount = " + s.getAmount() + ", recordAmount = " + s.getRecordAmount());
        }
    }

    //todo 测试值是否会更新
    public List<SubAccountTO> updateAmount(List<SubAccountTO> list, TxTO tx) {
        for (SubAccountTO sb : list) {
            if (sb.getAccount().equals(tx.getFrom())) {
                sb.setAmount(sb.getAmount() - tx.getAmount());
            }
            if (sb.getAccount().equals(tx.getTo())) {
                sb.setAmount(sb.getAmount() + tx.getAmount());
            }
        }
        return list;
    }

    //轧差
    public void offsetBalance(List<TxTO> list, List<SubAccountTO> subList) {
        for (TxTO to : list) {
            for (SubAccountTO sb : subList) {
                if (to.getFrom().equals(sb.getAccount())) {
                    sb.setAmount(sb.getAmount() - to.getAmount());
                }
                if (to.getTo().equals(sb.getAccount())) {
                    sb.setAmount(sb.getAmount() + to.getAmount());
                }
            }
        }
    }

    //账户从大到小排序
    public List<SubAccountTO> accountSort(List<SubAccountTO> list) {
        List<SubAccountTO> newList = new ArrayList<>();
        int len = list.size();
        if (len > 0)   //查看数组是否为空
        {
            for (int i = 0; i < len; i++) {
                SubAccountTO sba = list.get(0);
                for (SubAccountTO sa : list) {
                    if (sba.getAmount() < sa.getAmount()) {
                        sba = sa;
                    }
                }
                newList.add(sba);
                list.remove(sba);
            }
        }

        return newList;
    }

    //单个用户相关订单（from） 从大到小排序
    public List<TxTO> txDesc(List<TxTO> list) {
        List<TxTO> newList = new ArrayList<>();
        int len = list.size();
        if (len > 0)   //查看数组是否为空
        {
            for (int i = 0; i < len; i++) {
                TxTO to = list.get(0);
                for (TxTO tx : list) {
                    if (to.getAmount() < tx.getAmount()) {
                        to = tx;
                    }
                }
                newList.add(to);
                list.remove(to);
            }
        }

        return newList;
    }

    //获取最小订单
    public TxTO getMinTx(List<TxTO> list) {
        if (list.size() > 0)   //查看数组是否为空
        {
            TxTO to = list.get(0);
            for (TxTO tx : list) {
                if (to.getAmount() > tx.getAmount()) {
                    to = tx;
                }
            }
            return to;
        }
        return new TxTO();
    }

    //某个用户订单相关排序(降序)
    public List<TxTO> txDescSort(List<TxTO> txList, SubAccountTO sb) {
        List<TxTO> newList = new ArrayList<>();
        for (TxTO tx : txList) {
            if (sb.getAccount().equals(tx.getFrom())) {
                newList.add(tx);
                //txList.remove(tx);
            }
        }
        return txDesc(newList);
    }

    //出现死循环时获取单用户最少补的钱数
    public long getMinAmount(List<TxTO> txList, SubAccountTO sb) {
        List<TxTO> newList = new ArrayList<>();
        for (TxTO tx : txList) {
            if (sb.getAccount().equals(tx.getFrom())) {
                newList.add(tx);
            }
        }
        if (newList.size() == 0) {
            return 0;
        }
        return getMinTx(newList).getAmount() - sb.getAmount();
    }

    //获取最小补差账户
    public SubAccountTO getMinSubAccount(List<SubAccountTO> subList, List<TxTO> txList) {
        List<SubAccountTO> tempSbList = listCopy(subList);
        for (SubAccountTO sb : tempSbList) {
            sb.setMinAmount(getMinAmount(txList, sb));
            //System.out.println(getMinAmount(txList,sb));
        }
        return getMinSub(tempSbList);
    }

    //获取死循环补钱最少账户
    public SubAccountTO getMinSub(List<SubAccountTO> list) {
        SubAccountTO sb = new SubAccountTO();
        for (SubAccountTO s : list) {
            if (s.getMinAmount() > 0) {
                sb = s;
            }
        }
        for (SubAccountTO s : list) {
            if (s.getMinAmount() > 0 && s.getMinAmount() < sb.getMinAmount()) {
                sb = s;
            }
        }
        //System.out.println(sb.getMinAmount());
        return sb;
    }

    //数组复制
    public <T> List<T> listCopy(List<T> oldList) {
        List<T> newList = new ArrayList<>();
        for (T t : oldList) {
            newList.add(t);
        }
        return newList;
    }

    //移除某个ID Tx
    public static List<TxTO> removeTxTOItem(List<TxTO> list, TxTO tx) {
        for (TxTO txTO : list) {
            if (txTO.getId() == tx.getId()) {
                list.remove(txTO);
                break;
            }
        }
        return list;
    }

    //模拟交易逻辑
    public long simulateTx(List<SubAccountTO> subList, List<TxTO> txList) {
        //判断是否出现死循环
        long count = 0;
        List<TxTO> sortTxList = new ArrayList<>();
        List<SubAccountTO> sList = listCopy(subList);
        List<SubAccountTO> tempSbList = accountSort(sList);
        for (SubAccountTO sb : tempSbList) {
            if (sb.getAmount() <= 0) {
                continue;
            }
            List<TxTO> tempTxList = txDescSort(txList, sb);
            while (tempTxList.size() > 0) {
                for (TxTO tempTx : tempTxList) {
                    if (tempTx.getAmount() > sb.getAmount()) {
                        tempTxList.remove(tempTx);
                        break;
                    }
                    //添加 排序订单
                    sortTxList.add(tempTx);
                    subList = updateAmount(subList, tempTx);
                    System.out.println("from = " + tempTx.getFrom() + ", to = " + tempTx.getTo()
                            + ", amount = " + tempTx.getAmount() + ", id = " + tempTx.getId());
                    String allAmount = "";
                    for (SubAccountTO sba : subList) {
                        allAmount = allAmount + sba.getAccount() + " = " + sba.getAmount() + ",";
                    }

                    //System.out.println(allAmount);
                    //移除已排序订单
                    txList = removeTxTOItem(txList, tempTx);
                    tempTxList.remove(tempTx);

                    /*System.out.println(txList.size());
                    System.out.println("交易后订单：");
                    for (TxTO txTO : txList) {
                        System.out.println("id=" + txTO.getId() + ",amount" + txTO.getAmount() + ",from=" + txTO.getFrom() + ",to=" + txTO.getTo());
                    }
                    System.out.println("***************************");*/

                    count++;
                    break;
                }
            }
        }
        return count;
    }

}
