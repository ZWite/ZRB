package com.zhang.pojo.SocketIO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true) // 链式写法
public class ChatRoom {
    // 聊天室id
    private String roomId;

    // 人员id
    private  String personId;

    // 人员名称
    private String personName;
}
