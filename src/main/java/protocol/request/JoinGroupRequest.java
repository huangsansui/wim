package protocol.request;

import lombok.Data;
import protocol.Packet;
import protocol.command.Command;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
@Data
public class JoinGroupRequest extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
