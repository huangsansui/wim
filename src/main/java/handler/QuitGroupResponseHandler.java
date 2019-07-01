package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.request.QuitGroupRequest;
import protocol.response.QuitGroupResponse;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponse msg) throws Exception {
        System.out.println("用户 " + msg.getUsername() + " 已经退出了群聊");
    }
}
