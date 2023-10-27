package com.zhang.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.zhang.pojo.SocketIO.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @description 消息分发
 * @author zhangmengxin
 * @date 2023/1/10 16:09
 */
@Component
@Slf4j
public class MessageEventHandler {

    public static SocketIOServer socketIoServer;

    static ArrayList<UUID> listClient = new ArrayList<>();

    /**
     * 打开连接
     *
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        try {
            if (!listClient.contains(client.getSessionId())) {
                listClient.add(client.getSessionId());
            }
            log.info("客户端:" + client.getSessionId() + "打开连接");
        } catch (Exception e) {
            log.error("客户端:" + client.getSessionId() + "打开连接异常！" + e.getMessage());
        }
    }

    @OnEvent("joinRoom")
    public void joinRoomEvent(SocketIOClient client, HttpRequest request, ChatRoom room) {
        try {
            log.info("clientSession:" + client.getSessionId() + ".人员id:" + room.getPersonId() + "加入聊天室:" + room.getRoomId());
            client.joinRoom(room.getRoomId());
            String ryid = room.getPersonId();
            client.set("ryid", ryid);
            String jglb;
            int i = 0;
            int j = 0;
            int c = 0;
            int d = 0;
            for (UUID clientId : listClient) {
                SocketIOClient socketIOClient = socketIoServer.getClient(clientId);
                if (socketIOClient != null && socketIOClient.getAllRooms().contains(room.getRoomId())) {
                  i++;
                }
            }
            for (UUID clientId : listClient) {
                SocketIOClient socketIOClient = socketIoServer.getClient(clientId);
                if (socketIOClient != null && socketIOClient.getAllRooms().contains(room.getRoomId())) {
                    socketIOClient.sendEvent("roomEvent", i, 1, j, c, d);
                }
            }
        } catch (Exception e) {
            log.error("clientSession:" + client.getSessionId() + ".人员id:" + room.getPersonId() + "加入聊天室异常！" + e.getMessage());
        }
    }

}
