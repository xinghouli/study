package com.lance.study.offer;

import java.util.ArrayList;
import java.util.List;

public class 从头到尾打印链表 {
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        printList(list,listNode);
        return list;
    }

    private static  void printList(List<Integer> list,ListNode listNode){
        if(listNode.next != null){
            printList(list,listNode.next);
        }
        list.add(listNode.val);
    }
    public static void main(String[] args) {
        ListNode listNode = new ListNode(67);
        ListNode listNode1 = new ListNode(0);
        listNode.next = listNode1;
        ListNode listNode2 = new ListNode(24);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(58);
        listNode2.next = listNode3;
        ArrayList<Integer> integers = printListFromTailToHead(listNode);
        System.out.println(integers);
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}
