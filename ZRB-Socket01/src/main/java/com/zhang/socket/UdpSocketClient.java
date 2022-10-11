package com.zhang.socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

import static java.lang.Thread.sleep;

@Component
public class UdpSocketClient {

    @Value("${client.port}")
    private int clientPort;

    @PostConstruct
    public void init() throws Exception{
        Socket socket = new Socket("127.0.0.1",clientPort);
        do {
            String s = "1";
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(s.getBytes(Charset.defaultCharset()));
            System.out.println("休眠开始");
            sleep(2000);
            System.out.println("休眠结束");
//            byte[] bytes = new byte[1024];
//            InputStream inputStream = socket.getInputStream();
//            int read = inputStream.read(bytes);
//            System.out.println(new String(bytes,0,read, Charset.defaultCharset()));
        }while (true);
    }
}
