package protocol.response;

import lombok.Data;
import protocol.Packet;
import protocol.command.Command;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
@Data
public class JoinGroupResponse extends Packet {

    private String groupId;

    private String username;

    private Boolean isSuccess;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
