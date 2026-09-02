package com.designpattern.singleton;

import java.util.*;

public class Test {

    public static void main(String[] args) {
//        Deque<Integer> deque = new ArrayDeque<>();
//        deque.addLast(12);
//        deque.addLast(4);
//        deque.addFirst(7);
//
//        System.out.println(deque);
//
//        TreeMap<Integer,Integer> tm = new TreeMap<>();

        LinkedList<Integer> list = new LinkedList<>();
        list.add(12);
        list.add(14);
        list.add(20);
        System.out.println(list);
        Map<Integer,Integer> map;


        list.remove();
        System.out.println(list);

        list.add(12);
        System.out.println(list);



    }
}
