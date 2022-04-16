package client;

import common.SessionUtil;
import handler.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import protocol.command.ConsoleCommandManager;
import protocol.command.impl.LoginCommand;
import protocol.request.LoginRequest;
import protocol.request.MessageRequest;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public class WimClient {

    private static String host = "127.0.0.1";

    private static int port = 7000;

    private static final int MAX_RETRY = 3;


    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new ClientHandler());
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new PacketDecoderHandler());
                        ch.pipeline().addLast(new LoginResponseHandler(args));
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        ch.pipeline().addLast(new ListGroupMemberResponseHandler());
                        ch.pipeline().addLast(new QuitGroupResponseHandler());
                        ch.pipeline().addLast(new GroupMessageResposeHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new HeartBeatTimerHandler());
                        ch.pipeline().addLast(new PacketEncoderHandler());

                        ch.pipeline().addLast(new FirstClientHandle());
                    }
                });

        connect(bootstrap, host, port, MAX_RETRY);
    }


    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {

        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + " 连接成功！");
                startConsoleThread(((ChannelFuture) future).channel());
            } else if (retry == 0) {
                System.out.println(new Date() + " 重试次数已用完，放弃连接");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << order;
                System.out.println(new Date() + " 连接失败，第" + order + "次重连！");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {

        LoginCommand loginCommand = new LoginCommand();
        ConsoleCommandManager manager = new ConsoleCommandManager();
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {

            while (!Thread.interrupted()) {
                if (!SessionUtil.hashLogin(channel)) {
//                    loginCommand.exec(scanner, channel);
                    waitForLogin();
                } else {
                    manager.exec(scanner, channel);
                }
            }
        }).start();
    }

    private static void waitForLogin() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
