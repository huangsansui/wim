package protocol.command.impl;

import io.netty.channel.Channel;
import protocol.command.ConsoleCommand;
import protocol.request.GroupMessageRequest;

import java.util.Scanner;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class GroupMessageCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入你要发送消息的群组 groupId ：");
        String groupId = scanner.nextLine();
        System.out.println("请输入你要发送的消息: ");
        String message = scanner.nextLine();
        GroupMessageRequest request = new GroupMessageRequest();
        request.setGroupId(groupId);
        request.setMessage(message);
        channel.writeAndFlush(request);
    }
}
