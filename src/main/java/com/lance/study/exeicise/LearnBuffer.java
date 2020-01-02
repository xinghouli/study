package com.lance.study.exeicise;

import java.nio.Buffer;
import java.nio.IntBuffer;

public class LearnBuffer {

  public static void main(String[] args) {
      IntBuffer intBuffer = IntBuffer.allocate(2);
      IntBuffer put = intBuffer.put(98);
      IntBuffer put1 = intBuffer.put(2);
      System.out.println("======"+intBuffer);
      Buffer flip = intBuffer.flip();
      System.out.println(intBuffer.get());
    System.out.println("position"+intBuffer.position());
      Buffer mark = intBuffer.mark();
      System.out.println(intBuffer.get());
    System.out.println("position"+intBuffer.position());
    System.out.println("========position======");
      Buffer reset = intBuffer.reset();
    System.out.println(intBuffer.get());
    System.out.println(intBuffer.position());
    System.out.println("reset");
      //    intBuffer.rewind();
    System.out.println(intBuffer.remaining());
    System.out.println(intBuffer.limit());
      Buffer rewind = intBuffer.rewind();
    System.out.println(intBuffer.limit());
      //
  }
}
