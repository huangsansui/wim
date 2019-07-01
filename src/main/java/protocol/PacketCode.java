package protocol;

import io.netty.buffer.ByteBuf;
import protocol.command.Command;
import protocol.request.*;
import protocol.response.*;
import serialize.Serializer;
import serialize.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2019/6/26
 *
 * @author qing.huang
 */
public class PacketCode {

    private static final int MAGIC_NUMBER = 0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    public static final PacketCode INSTANCE = new PacketCode();

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequest.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponse.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequest.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponse.class);
        packetTypeMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequest.class);
        packetTypeMap.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponse.class);
        packetTypeMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequest.class);
        packetTypeMap.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponse.class);
        packetTypeMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequest.class);
        packetTypeMap.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponse.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBER_REQUEST, ListGroupMemberRequest.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBER_RESPONSE, ListGroupMemberResponse.class);
        packetTypeMap.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequest.class);
        packetTypeMap.put(Command.GROUP_MESSAGE_RESPONSE, GroupMessageResponse.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }


    public ByteBuf encode(ByteBuf  byteBuf, Packet packet) {
        // 1.创建 ByteBuf 对象
//        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        // 2、序列化 Java 对象
        byte[] bytes = Serializer.DEFAULT.serializer(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);
        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserializer(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
