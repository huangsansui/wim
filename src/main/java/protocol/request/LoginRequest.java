package protocol.request;

import lombok.Data;
import protocol.Packet;
import protocol.command.Command;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
@Data
public class LoginRequest extends Packet {

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
