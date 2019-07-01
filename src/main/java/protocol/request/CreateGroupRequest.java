package protocol.request;

import lombok.Data;
import protocol.Packet;
import protocol.command.Command;

import java.util.List;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
@Data
public class CreateGroupRequest extends Packet {

    private List<String> userList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
