package com.lance.study.exeicise;

import java.util.concurrent.Callable;

public class T extends T1{

    @Override
    public void s1() {
        System.out.println("t==1");
        super.s1();
    }

    @Override
    public void s2() {
        System.out.println("t===2");
        super.s2();
    }

    public static void main(String[] args) {
        T t = new T();
        t.s1();
        t.s2();
//        A a = new A();
//        a.setA(0);
//        System.out.println(a.getA() == B.B.getValue());

    }
}

class A implements Callable {
    private Integer a ;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}

enum B{
    B(0,"");
    private Integer value;
    private String name;

    public Integer getValue() {
        return value;
    }

    B(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}