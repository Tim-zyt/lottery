package com.sf.lottery.web.sort;

import java.util.LinkedList;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/12/7.
 * <p/>
 * 算法时间复杂度为O(n*log(size))，是一种利用空间换时间的算法,这里考虑情况为size<<n
 * 非线程安全
 */
public class NonThreadSafeDescSortedMostNList {
    private LinkedList<Comparable> linkedList;
    private int size;

    public NonThreadSafeDescSortedMostNList(int size) {
        linkedList = new LinkedList<>();
        this.size = size;
    }

    public void put(Comparable item) {
        boolean isPut = false;
        for (Comparable comparable : linkedList) {
            if (comparable.compareTo(item) < 0) {//如果当前位置小于item
                linkedList.add(linkedList.indexOf(comparable), item);
                isPut = true;
                break;
            }
        }
        if (!isPut) {
            linkedList.add(item);
        }
        if (linkedList.size() > size) {
            linkedList.removeLast();
        }
    }

    public LinkedList<Comparable> getResult() {
        return linkedList;
    }
}
