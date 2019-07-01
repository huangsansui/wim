package protocol.command;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public interface Command {

    /**
     * 登录请求
     */
    byte LOGIN_REQUEST = 1;

    /**
     * 登录响应
     */
    byte LOGIN_RESPONSE = 2;

    /**
     * 消息请求
     */
    byte MESSAGE_REQUEST = 3;

    /**
     * 消息响应
     */
    byte MESSAGE_RESPONSE = 4;

    /**
     * 创建群组请求
     */
    byte CREATE_GROUP_REQUEST = 5;

    /**
     * 创建群组响应
     */
    byte CREATE_GROUP_RESPONSE = 6;

    /**
     * 加入群组请求
     */
    byte JOIN_GROUP_REQUEST = 7;

    /**
     * 加入群组响应
     */
    byte JOIN_GROUP_RESPONSE = 8;

    /**
     * 群成员请求
     */
    byte LIST_GROUP_MEMBER_REQUEST = 9;

    /**
     * 群成员响应
     */
    byte LIST_GROUP_MEMBER_RESPONSE = 10;

    /**
     * 退出群聊请求
     */
    byte QUIT_GROUP_REQUEST = 11;

    /**
     * 退出群聊响应
     */
    byte QUIT_GROUP_RESPONSE = 12;

    /**
     * 群消息请求
     */
    byte GROUP_MESSAGE_REQUEST = 13;

    /**
     * 群消息响应
     */
    byte GROUP_MESSAGE_RESPONSE = 14;

    /**
     * 心跳检测请求
     */
    byte HEART_BEAT_REQUEST = 15;

    /**
     * 心跳检测响应
     */
    byte HEART_BEAT_RESPONSE = 16;
}
