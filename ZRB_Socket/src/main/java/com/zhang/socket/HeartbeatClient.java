package com.zhang.socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@ConfigurationProperties(prefix="socket")
public class HeartbeatClient {

    Object hashLock = new Object();

    //当前的连接数和工作线程数
    static int workThreadNum = 0;

    static int socketConnect = 0;
    /**
     * 线程池
     */
    private ExecutorService executorService = null;

    private ServerSocket serverSocket;
    /*服务器地址*/
    private String Ip;
    //服务器端口
    @Value("${server.port}")
    private int serverPort;
    //节点ID
    private String nodeID = UUID.randomUUID().toString();
    //是否启动
    private boolean isRunning = true;
    //设置心跳包的结束标记
    String endFlag = "";
    // 最近的心跳时间
    private long lastHeartbeat;
    // 心跳间隔时间
    private long heartBeatInterval = 10 * 1000;
    //扫描间隔
    private int scanTime = 1800;
//    CharSequence csEndFlag = endFlag.subSequence(0, 10);
    /* 客户端列表*/
    private List<Socket> socketList = new ArrayList<>();

    @PostConstruct
    public void  startListenReport(){
        try {
            // 创建服务器 socket
            serverSocket = new ServerSocket(serverPort);
            // 初始化线程池
//            executorService = new ThreadPoolExecutor(20,200,60, TimeUnit.SECONDS,);
            executorService = Executors.newCachedThreadPool();
            System.out.println("服务启动完成，等待客户端连接...");
            Socket client = null;
            while (true) {
                client = serverSocket.accept();
                socketList.add(client);
                executorService.execute(new Service(client));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static class Service implements Runnable {

        /**
         * 20 秒超时服务器主动断开
         */
        private static final long TIMEOUT_MILLISECOND = 20000;

        private Socket socket;

        private boolean isConnect;
        private long lastReceiveTime = System.currentTimeMillis();

        public Service(Socket socket) {
            this.socket = socket;
            this.isConnect = true;
            System.out.println(socket.getInetAddress() + ":" + socket.getPort() + " <连接>");
        }
        @Override
        public void run() {
            while (isConnect) {
                // 超时
                if (System.currentTimeMillis() - lastReceiveTime > TIMEOUT_MILLISECOND) {
                    stopService();
                } else {
                    try {
                        // 默认接收 1024字节
                        byte[] bytes = new byte[1024];
                        InputStream in = socket.getInputStream();
                        if (in.available() > 0) {
                            lastReceiveTime = System.currentTimeMillis();
                            int len = in.read(bytes);
                            String content = new String(bytes);
                            System.out.println(socket.getInetAddress() + ":" + socket.getPort() + "  " + content);
                            // 回响
                            OutputStream os = socket.getOutputStream();
                            os.write(bytes);
                            os.flush();
                        } else {
                            Thread.sleep(10);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        stopService();
                    }
                }

            }
        }

        /**
         * 断开客户端
         */
        private void stopService() {
            isConnect = false;
            System.out.println(socket.getInetAddress() + ":" + socket.getPort() + " <断开>");
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
