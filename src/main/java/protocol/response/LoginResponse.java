package protocol.response;

import lombok.Data;
import protocol.Packet;
import protocol.command.Command;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
@Data
public class LoginResponse extends Packet {

    private Boolean isSuccess;

    private String userId;

    private String username;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
