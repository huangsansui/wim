package handler;

import common.Session;
import common.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.request.LoginRequest;
import protocol.response.LoginResponse;

import java.util.Date;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponse> {

    private LoginRequest request;

    public LoginResponseHandler(String[] args) {
        request = new LoginRequest();
//        request.setUserId(UUID.randomUUID().toString());
        request.setUsername(args[0]);
        request.setPassword(args[1]);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + " 开始登录");
        ctx.channel().writeAndFlush(request);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponse msg) throws Exception {
        if (msg.getIsSuccess()) {
            System.out.println(new Date() + " 客户端登录成功");
            System.out.println("用户ID：" + msg.getUserId() + " 用户名:【" + msg.getUsername() + "】");
            SessionUtil.bindSession(new Session(msg.getUserId(), msg.getUsername()), ctx.channel());
        } else {
            System.out.println(new Date() + " 客户端登录失败，失败原因:" + msg.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭!");
    }
}
