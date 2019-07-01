package protocol.request;

import protocol.Packet;
import protocol.command.Command;

/**
 * Created on 2019/7/1
 *
 * @author qing.huang
 */
public class HeartBeatRequest extends Packet {


    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT_REQUEST;
    }
}
