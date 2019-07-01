package protocol.command.impl;

import io.netty.channel.Channel;
import protocol.command.ConsoleCommand;
import protocol.request.CreateGroupRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class CreateGroupCommand implements ConsoleCommand {

    private static final String USER_SPLIT_MESSAGE = ",";


    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("【拉人群聊】请输入 userId 列表，多个成员ID用英文逗号','隔开：");
        String userString = scanner.nextLine();
        List<String> userList = Arrays.asList(userString.split(USER_SPLIT_MESSAGE));
        if (userList.size() <= 1) {
            System.out.println("请输入正确的成员ID格式");
        }
        CreateGroupRequest request = new CreateGroupRequest();
        request.setUserList(userList);
        channel.writeAndFlush(request);
    }
}
