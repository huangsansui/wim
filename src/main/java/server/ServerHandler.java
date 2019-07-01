package server;

import common.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.Packet;
import protocol.PacketCode;
import protocol.command.Command;
import protocol.request.LoginRequest;
import protocol.request.MessageRequest;
import protocol.response.LoginResponse;
import protocol.response.MessageResponse;

import java.util.Date;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        Packet decode = PacketCode.INSTANCE.decode(byteBuf);

        if (decode instanceof LoginRequest) {
            LoginRequest request = (LoginRequest) decode;
            LoginResponse response = new LoginResponse();
            if (isValid(request)) {
                System.out.println(new Date() + " " + request.getUsername() + "连接成功！");
                response.setIsSuccess(true);
            } else {
                System.out.println(new Date() + " 连接失败，用户名或密码不正确");
                response.setIsSuccess(false);
            }

            // 返回数据给客户端
            ByteBuf responseBuf = PacketCode.INSTANCE.encode(ctx.alloc().ioBuffer(), response);

            ctx.channel().writeAndFlush(responseBuf);

            // 标记登录状态
//            SessionUtil.markLogin(ctx.channel());
        } else if (decode instanceof MessageRequest) {

            MessageRequest request = (MessageRequest) decode;
            System.out.println(new Date() + " 收到客户端发来的消息:【" + request.getMessage() + "】");

            if (!SessionUtil.hashLogin(ctx.channel())) {

                MessageResponse response = new MessageResponse();
                response.setMessage("请先进行登录！");
                ByteBuf responseBuf = PacketCode.INSTANCE.encode(ctx.alloc().ioBuffer(), response);
                ctx.channel().writeAndFlush(responseBuf);
                return;
            }

            MessageResponse response = new MessageResponse();
            response.setMessage(request.getMessage());
            ByteBuf responseBuf = PacketCode.INSTANCE.encode(ctx.alloc().ioBuffer(), response);

            ctx.channel().writeAndFlush(responseBuf);
        }
    }

    private boolean isValid(LoginRequest request) {
        if (request.getVersion() != 1) {
            return false;
        }
        if (!request.getCommand().equals(Command.LOGIN_REQUEST)) {
            return false;
        }
        if (!request.getUsername().equals("sansui") || !request.getPassword().equals("316hq8239880")) {
            return false;
        }
        return true;
    }
}
