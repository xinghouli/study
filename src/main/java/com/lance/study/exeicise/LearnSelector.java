package com.lance.study.exeicise;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class LearnSelector {
  public static void main(String[] args) throws IOException {

      ServerSocketChannel serverSocket = ServerSocketChannel.open();
      serverSocket.socket().bind(new InetSocketAddress(8080));
      //必须为异步,同步注册会报错
      serverSocket.configureBlocking(false);

      Selector selector = Selector.open();
      JSONObject jsonObject1 = new JSONObject();
      jsonObject1.put("test","test");
      SelectionKey register = serverSocket.register(selector, SelectionKey.OP_ACCEPT, jsonObject1);
      Object attachment1 = register.attachment();
    System.out.println(attachment1);
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      Set<SelectionKey> keys = selector.keys();
      System.out.println("size=="+selectionKeys.size());
      Iterator<SelectionKey> iterator = selectionKeys.iterator();
      while (iterator.hasNext()){
          SelectionKey next = iterator.next();
          SelectableChannel channel = next.channel();
          Object attachment = next.attachment();
          JSONObject object = (JSONObject)attachment;
      System.out.println("jsonObect="+object);
          iterator.remove();
          //do something
          //不符合处理条件  重新注册
          Selector selector1 = next.selector();
          JSONObject jsonObject = new JSONObject();
          channel.register(selector1,next.interestOps(),jsonObject);

      }
    System.out.println(selector.selectedKeys().size());
//      selector.close(); //使SelectionKey失效  但是不会关闭channel

      //
  }
}
