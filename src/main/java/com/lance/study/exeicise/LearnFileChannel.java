package com.lance.study.exeicise;

import java.io.*;
import java.nio.channels.FileChannel;

//RandomAccessFile
public class LearnFileChannel {
  public static void main(String[] args) throws FileNotFoundException, IOException {
      File file = new File("/Users/admin/Desktop/testFile");
      FileWriter fileWriter = new FileWriter(file);
      RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/admin/Desktop/file","rw");
      FileChannel channel = randomAccessFile.getChannel();
      long filePointer = randomAccessFile.getFilePointer();
    System.out.println("filePointer====="+filePointer);
      String line;
    while ((line = randomAccessFile.readLine()) != null){
      System.out.println(randomAccessFile.getFilePointer());
      System.out.println(line);
    }
        String appendStr = "this is appendLine";
      randomAccessFile.write(appendStr.getBytes());
      randomAccessFile.seek(0);
      while ((line = randomAccessFile.readLine()) != null){
          System.out.println(randomAccessFile.getFilePointer());
          System.out.println(line);
          fileWriter.write(line,0,line.length());
      }
      fileWriter.flush();
      fileWriter.close();








  }
}
