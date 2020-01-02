package com.lance.study.designpattern.singleton;

public class Singleton {

    private Singleton() {
    }

    enum SingletonEnum{
        INSTANCE;

        private Singleton singleton;

        SingletonEnum() {
            this.singleton = new Singleton();
        }

        public Singleton getSingleton() {
            return singleton;
        }
    }

  public static void main(String[] args) {
      Singleton singleton = SingletonEnum.INSTANCE.getSingleton();
  }
}
