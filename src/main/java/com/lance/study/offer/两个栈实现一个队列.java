package com.lance.study.offer;

import java.util.Stack;

public class 两个栈实现一个队列 {
   Stack<Integer> stack1= new Stack<Integer>();
   Stack<Integer> stack2 =new Stack<Integer>();

    public void push(int node){
        stack1.push(node);//将元素入栈
    }

    class A{
       public int pop(){
           //stack1不为空 将其压入stack2
           while (!stack1.isEmpty()){
               stack2.push(stack1.pop());
           }
           //取出第一个
           int first = stack2.pop();
           //将数据还原
           while (!stack2.isEmpty()){
               stack1.push(stack2.pop());
           }
           return first;
       }
   }

    class B extends Thread{
       public int pop(){
           //stack2不为空 一直取数据  为空则将stack1的数据放入stack2 并取数据
           if(stack2.isEmpty()){
               while (!stack1.isEmpty()){
                   stack2.push(stack1.pop());
               }
           }
           return stack2.pop();
       }
   }

}
