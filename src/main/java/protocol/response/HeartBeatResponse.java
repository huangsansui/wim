package protocol.response;

import protocol.Packet;
import protocol.command.Command;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class HeartBeatResponse extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT_RESPONSE;
    }
}
