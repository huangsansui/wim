package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.Packet;
import protocol.PacketCode;
import protocol.request.LoginRequest;
import protocol.response.LoginResponse;
import protocol.response.MessageResponse;

import java.util.Date;
import java.util.UUID;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(new Date() + " 开始登录");
        LoginRequest request = new LoginRequest();

        request.setUserId(UUID.randomUUID().toString());
        request.setUsername("sansui");
        request.setPassword("316hq8239880");

        ByteBuf encode = PacketCode.INSTANCE.encode(ctx.alloc().ioBuffer(), request);

        ctx.channel().writeAndFlush(encode);


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        Packet decode = PacketCode.INSTANCE.decode(byteBuf);

        if (decode instanceof LoginResponse) {

            LoginResponse response = (LoginResponse) decode;

            if (response.getIsSuccess()) {
                System.out.println(new Date() + " 登录成功");
            } else {
                System.out.println(new Date() + " 登录失败");
            }
        } else if (decode instanceof MessageResponse) {

            MessageResponse response = (MessageResponse) decode;
            System.out.println(new Date() + " 收到来自服务器发来的消息：【" + response.getMessage() + "】");
        }
    }
}
