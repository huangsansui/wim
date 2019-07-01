package handler;

import common.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created on 2019/6/28
 *
 * @author qing.huang
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hashLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (!SessionUtil.hashLogin(ctx.channel())) {
            System.out.println("无登录验证，强制关闭连接!");
        } else {
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        }
    }
}
