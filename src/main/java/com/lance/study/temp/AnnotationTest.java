package com.lance.study.temp;

import java.lang.reflect.Method;

public class AnnotationTest {
  public static void main(String[] args) throws Exception{
	  Class<?> aClass = Class.forName(args[0]);

	  for (Method m: aClass.getDeclaredMethods()){
      System.out.println("------");
	  }
  }

  public void y(){

  }
}
