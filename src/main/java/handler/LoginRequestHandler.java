package handler;

import common.Session;
import common.SessionUtil;
import common.UserInfoUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.command.Command;
import protocol.request.LoginRequest;
import protocol.response.LoginResponse;

import java.util.Date;
import java.util.UUID;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest msg) throws Exception {
        // 登录逻辑
        LoginResponse response = new LoginResponse();
        if (isValid(msg)) {
            System.out.println(new Date() + " " + msg.getUsername() + "客户端连接成功！");
            msg.setUserId(UUID.randomUUID().toString().substring(0, 8));
            SessionUtil.bindSession(new Session(msg.getUserId(), msg.getUsername()), ctx.channel());
            response.setIsSuccess(true);
            response.setUserId(msg.getUserId());
            response.setUsername(msg.getUsername());
        } else {
            System.out.println(new Date() + " 客户端连接失败，用户名或密码不正确");
            response.setIsSuccess(false);
            response.setReason("用户名或密码不正确");
        }
        ctx.channel().writeAndFlush(response);

    }

    private boolean isValid(LoginRequest request) {
        if (request.getVersion() != 1) {
            return false;
        }
        if (!request.getCommand().equals(Command.LOGIN_REQUEST)) {
            return false;
        }
        if (UserInfoUtil.getPassword(request.getUsername()) == null || !UserInfoUtil.getPassword(request.getUsername()).equals(request.getPassword())) {
            return false;
        }
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
    }
}
