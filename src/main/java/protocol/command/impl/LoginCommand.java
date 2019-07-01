package protocol.command.impl;

import io.netty.channel.Channel;
import protocol.command.ConsoleCommand;
import protocol.request.LoginRequest;

import java.util.Scanner;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class LoginCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {

        // 用户名
        System.out.println("请输入用户名");
        String username = scanner.nextLine();

        // 密码
        System.out.println("请输入密码");
        String password = scanner.nextLine();

        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        channel.writeAndFlush(request);
    }
}
