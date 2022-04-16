package server;

import handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.concurrent.GenericFutureListener;
import protocol.request.CreateGroupRequest;
import protocol.request.JoinGroupRequest;

import java.util.Date;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public class WimServer {

    private static int port = 7000;

    public static void main(String[] args) {

        ServerBootstrap bootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();

        bootstrap
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new ServerHandler());
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new PacketDecoderHandler());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(new CreateGroupRequestHandler());
                        ch.pipeline().addLast(new JoinGroupRequestHandler());
                        ch.pipeline().addLast(new ListGroupMemberRequestHandler());
                        ch.pipeline().addLast(new QuitGroupRequestHandler());
                        ch.pipeline().addLast(new GroupMessageRequestHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new HeartBeatRequestHandler());
                        ch.pipeline().addLast(new PacketEncoderHandler());
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });

        bind(bootstrap, port);
    }

    private static void bind(ServerBootstrap bootstrap, final int port) {

        bootstrap.bind(port).addListener((GenericFutureListener) future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + " 端口绑定成功，port=" + port);
            } else {
                System.out.println(new Date() + " 端口绑定失败，port=" + port);
                bind(bootstrap, port + 1);
            }
        });
    }
}
