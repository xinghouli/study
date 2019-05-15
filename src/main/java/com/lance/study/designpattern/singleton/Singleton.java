package com.lance.study.designpattern.singleton;

public class Singleton {

    private Singleton() {
    }

    public enum SingletonEnum{
        /**
         *
         */
        INSTANCE;

        private Singleton singleton = null;

        SingletonEnum() {
            singleton = new Singleton();
        }

        public Singleton getSingleton(){
            return singleton;
        }
    }

}
