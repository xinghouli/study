package com.lance.study.exeicise;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class LearnSocketChannel {
  public static void main(String[] args) throws IOException {
      SocketChannel socketChannel = SocketChannel.open();
      //设置非阻塞
      socketChannel.configureBlocking(false);
      //设置连接地址
      socketChannel.connect(new InetSocketAddress("http://example.com", 80));
      while (!socketChannel.finishConnect()){
      System.out.println("connetting");
      }
  }
}
