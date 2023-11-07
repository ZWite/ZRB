package com.example.demo2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static java.lang.Thread.sleep;

@SpringBootTest
class Demo2ApplicationTests {

    @Test
    void contextLoads() {
        try {
            FileChannel fileChannel = FileChannel.open(Path.of("C:/Users/Mxzy/OneDrive/桌面/123.docx"));
            FileChannel fileChannelOption = FileChannel.open(Path.of("C:/Users/Mxzy/OneDrive/桌面/123.docx"), StandardOpenOption.APPEND);
            RandomAccessFile randomAccessFile = new RandomAccessFile("C:/Users/Mxzy/OneDrive/桌面/新建 文本文档 (2).txt","rw");
            System.out.println(fileChannelOption.size());
//            MappedByteBuffer map = fileChannelOption.map(FileChannel.MapMode.READ_WRITE, fileChannelOption.size(), 1024);
            MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, randomAccessFile.length(), 2);
            map.put(0,  (byte) 'a');
            ByteBuffer buf = ByteBuffer.allocate(5);
//            while(randomAccessFile.getChannel().read(buf)!=-1){
//                buf.flip();
//                System.out.print(new String(buf.array()));
//                buf.clear();
//            }
            FileLock lock = randomAccessFile.getChannel().lock(0, Long.MAX_VALUE, false);
//            System.out.println("Channel locked in exclusive mode.");
//            map.put(0,  (byte) '1');
//            lock.release(); // 释放锁
//            FileLock lock1 = randomAccessFile.getChannel().lock(0, Long.MAX_VALUE, false);
//            System.out.println("Channel locked in exclusive mode.");
//            map.put(1,  (byte) '2');
//            lock.release(); // 释放锁

            for (int i = 0; i < 2; i++) {
                byte b = (byte) i;
                int finalI1 = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Channel locked in exclusive mode.");
                        map.put(finalI1,  b);
                    }
                }).start();
            }


            fileChannelOption.close();
            fileChannel.close();
            randomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    void test1() {
        if(1==1){
            System.out.println(1);
        }else if (2==2){
            System.out.println(2);
        }
    }
}
